package com.ganonalabs.munir.electrtech;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiService;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiUtils;
import com.ganonalabs.munir.electrtech.interfaces.ItemClickListner;
import com.ganonalabs.munir.electrtech.model.AppUser;
import com.ganonalabs.munir.electrtech.model.News;
import com.ganonalabs.munir.electrtech.model.Services;
import com.ganonalabs.munir.electrtech.ui.JobPostingActivity;

import com.ganonalabs.munir.electrtech.ui.OrderHistoryListActivity;
import com.ganonalabs.munir.electrtech.ui.SearchActivity;
import com.ganonalabs.munir.electrtech.ui.UserProfileActivity;
import com.ganonalabs.munir.electrtech.utils.RecyclerTouchListener;
import com.ganonalabs.munir.electrtech.viewholders.OfferHolder;
import com.ganonalabs.munir.electrtech.viewholders.ServicesHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;




public class Main2Activity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    public static String USERDATA = "";
    private TextView mTextMessage;
    public ImageView imguser;
    private FirebaseUser user;// = FirebaseAuth.getInstance().getCurrentUser();
    private ArrayList<Object> userData, userProviders;
    //private Query query;
    //private FirebaseDatabase firebaseDatabase;
    public RecyclerView mainlayoutrecycler, recycler_carousel;
    public RecyclerView.LayoutManager lm;
    public LinearLayoutManager lm1;
    public AppUser appUser;
    public TextView txtusername,txtuseremail,txtuserphone;
    public Button btneditprofile;
    public static CoordinatorLayout main2_coord;
    private Intent intent;
    public Services services = new Services();
    private String name, phone,email,address;
    private LinearLayout main2_image_wrap;
    private NavigationView navigationView;
   public int[] androidColors ;
   public CardView bankcardId;
   public EditText mSearchField;
    public ImageButton mSearchBtn;
    public SnapHelper snapHelper;
    public LinearLayout linear_carousel_area;
    public HashMap<String, String> imageURLs = new HashMap<String, String>();
    public List<String> imageURLList = new ArrayList<String>();
    //public SliderLayout sliderLayout ;
    public News news;

    private android.support.v4.view.ViewPager mViewPager;

    private com.ganonalabs.munir.electrtech.slider.CardPagerAdapter mCardAdapter;
    private com.ganonalabs.munir.electrtech.slider.ShadowTransformer mCardShadowTransformer;
    private com.ganonalabs.munir.electrtech.slider.CardFragmentPagerAdapter mFragmentCardAdapter;
    private com.ganonalabs.munir.electrtech.slider.ShadowTransformer mFragmentCardShadowTransformer;
    private boolean mShowingFragments = false;

    private com.synnapps.carouselview.CarouselView carouselView ;
    private String[] ImageUrlArr;




    public DatabaseReference serviceDB = FirebaseDatabase.getInstance()
            .getReference()
            .child("services");
    private DatabaseReference dbUserRef = FirebaseDatabase.getInstance()
            .getReference()
            .child("users");
    private DatabaseReference dbNewsRef = FirebaseDatabase.getInstance()
            .getReference()
            .child("news");

    private TokenDataApiService tokenDataAPIService = TokenDataApiUtils.getUserDataAPIServices();



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
                case R.id.nav_logout:
                    logout();
            }
            return false;
        }
    };

    public Query query = serviceDB
            .orderByChild("service_id")
            .limitToLast(50);
    public Query query1 = dbNewsRef
            .orderByChild("revtimestamp")
            //.orderByChild("top_card").equalTo("yes")
            .limitToFirst(7);



    FirebaseRecyclerOptions<Services> options = new FirebaseRecyclerOptions.Builder<Services>()
            .setQuery(query,Services.class).build();

    FirebaseRecyclerOptions<News> options1 = new FirebaseRecyclerOptions.Builder<News>()
            .setQuery(query1,News.class).build();


    public FirebaseRecyclerAdapter main_adapter = new FirebaseRecyclerAdapter<Services,ServicesHolder>(options) {
        @Override
        protected void onBindViewHolder(@NonNull ServicesHolder holder, int position, @NonNull Services model) {
            holder.service_text.setText(model.getService_text());
           // holder.service_tex.setText(model.getService_tex());
            holder.service_hidden_id.setText(model.getService_id());
            int color = androidColors[new Random().nextInt(androidColors.length)];
            holder.main2_image_wrap.setBackgroundColor(color);
            Picasso.with(getApplicationContext()).load(model.getImageUrl()).into(holder.service_image);
            Log.d("MainHolder" , "bound");
            //activateSlider();

        }

        @Override
        public ServicesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_items,parent,false);
            Log.d("MainHolder" , "created");
           // activateSlider();
            return new ServicesHolder(view);

        }

        @Override
        public void onDataChanged() {
            super.onDataChanged();
            //hideProgressDialog();
        }

        @Override
        public void onError(@NonNull DatabaseError error) {
            super.onError(error);
            Toast.makeText(getApplicationContext(),"Error Data Loading..",Toast.LENGTH_SHORT).show();
        }
    };


    public FirebaseRecyclerAdapter news_adapter = new FirebaseRecyclerAdapter<News,OfferHolder>(options1) {

        @Override
        protected void onBindViewHolder(@NonNull OfferHolder holder, int position, @NonNull News model) {
            Log.d("OfferHolder" , "bound");
            Picasso.with(getApplicationContext()).load(model.getImageURL()).into(holder.offer_card_image);
            //activateSlider();

        }

        @Override
        public OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carousel_card,parent,false);
            Log.d("OfferHolder" , "created");
            //activateSlider();
            return new OfferHolder(view);

        }

        @Override
        public void onDataChanged() {
            super.onDataChanged();
            //hideProgressDialog();

        }

        @Override
        public void onError(@NonNull DatabaseError error) {
            super.onError(error);
            Toast.makeText(getApplicationContext(),"Error Data Loading..",Toast.LENGTH_SHORT).show();
        }
    };


    public ValueEventListener profileListener = new ValueEventListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void onDataChange(DataSnapshot dataSnapshot) {
            for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                appUser = postSnapshot.getValue(AppUser.class);
                if(appUser!=null) {

                    updateUI(appUser);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    // dbUserRef.orderByKey().equalTo(user.getUid()).limitToFirst(1).addValueEventListener(profileListener);


    public ValueEventListener offerListener = new ValueEventListener() {
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                news = postSnapshot.getValue(News.class);
                try {
                    imageURLs.put(news.getTitle(), news.getImageURL());
                    imageURLList.add(news.getImageURL());

                }
                catch (Exception e){

                }
            }
            ImageUrlArr = imageURLList.toArray(new String[imageURLList.size()]);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        mViewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewPager);
        mCardAdapter = new com.ganonalabs.munir.electrtech.slider.CardPagerAdapter();
        mCardAdapter.addCardItem(new com.ganonalabs.munir.electrtech.slider.CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new com.ganonalabs.munir.electrtech.slider.CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new com.ganonalabs.munir.electrtech.slider.CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new com.ganonalabs.munir.electrtech.slider.CardItem(R.string.title_4, R.string.text_1));
        mFragmentCardAdapter = new com.ganonalabs.munir.electrtech.slider.CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mCardShadowTransformer = new com.ganonalabs.munir.electrtech.slider.ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new com.ganonalabs.munir.electrtech.slider.ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //showProgressDialog("Loading Data", getApplicationContext());

        main2_image_wrap = findViewById(R.id.main2_image_wrap);
        bankcardId = findViewById(R.id.bankcardId);

        dbUserRef= FirebaseDatabase.getInstance()
                .getReference().child("users");
        serviceDB.keepSynced(true);
        dbUserRef.keepSynced(true);
        dbNewsRef.keepSynced(true);



        mTextMessage = (TextView) findViewById(R.id.message);
        mainlayoutrecycler = findViewById(R.id.mainlayoutrecycler);
        main2_coord = findViewById(R.id.main2_coord);

        linear_carousel_area = findViewById(R.id.linear_carousel_area);

        recycler_carousel = findViewById(R.id.recycler_carousel);

        //sliderLayout = findViewById(R.id.slider);

        androidColors = getResources().getIntArray(R.array.androidcolors);

        snapHelper = new LinearSnapHelper();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        lm = new GridLayoutManager(getApplicationContext(),3);

        lm1 = new LinearLayoutManager(this);
        lm1.setOrientation(LinearLayoutManager.HORIZONTAL);

        //snapHelper.attachToRecyclerView(recycler_carousel);
       // recycler_carousel.setLayoutManager(lm1);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        txtuseremail = hView.findViewById(R.id.txtuseremail);
        txtusername = hView.findViewById(R.id.txtusername);
        txtuserphone = hView.findViewById(R.id.txtuserphone);
        imguser = hView.findViewById(R.id.imguser);
        btneditprofile = hView.findViewById(R.id.btneditprofile);

        mSearchField =  findViewById(R.id.search_field);
        mSearchBtn =  findViewById(R.id.search_btn);


        //dbUserRef.orderByKey().equalTo(user.getUid()).limitToFirst(1).addValueEventListener(profileListener);
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();
                Intent i  = new Intent(getApplicationContext(), SearchActivity.class);
                i.putExtra("USERDATA",appUser);
                i.putExtra("searchText", searchText);
                startActivity(i);


            }
        });
        dbNewsRef.addValueEventListener(offerListener);

//        double heightLinearOfferBlock = getResources().getDisplayMetrics().heightPixels / 5.5;
//        LinearLayout.LayoutParams lpOfferBlock = (LinearLayout.LayoutParams)linear_carousel_area.getLayoutParams();
//        lpOfferBlock.height = (int)heightLinearOfferBlock;



//        int size = imageURLs.size();
//        if(size>0){
//            activateSlider();
//        }

     com.synnapps.carouselview.ImageListener imageListener = new com.synnapps.carouselview.ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            if(imageURLList.size() > 0){

            ImageUrlArr = imageURLList.toArray(new String[imageURLList.size()]);
            imageView.setImageResource(ImageUrlArr[position]);
            }
        }
    };

    }

    @Override
    protected void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {

            userData = new ArrayList<>();
            userProviders = new ArrayList<>();
            if (user != null) {
                //Signed in, launch the Sign In Activity
                // Name, email address, and profile photo Url
                userData.add(user.getDisplayName());
                userData.add(user.getEmail());
                userData.add(user.getPhotoUrl());
                userData.add(user.getPhoneNumber());
                for (UserInfo profile : user.getProviderData()) {
                    userProviders.add(profile);
                }
                String provider = user.getProviders().get(0);

            }
        }
        main_adapter.startListening();
        news_adapter.startListening();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //activateSlider();
        if(user != null) {
            dbUserRef.orderByKey().equalTo(user.getUid()).limitToFirst(1).addValueEventListener(profileListener);
        }
        //activateSlider();
        mainlayoutrecycler.setLayoutManager(lm);
        mainlayoutrecycler.setItemAnimator(null);

//        recycler_carousel.setLayoutManager(lm1);
//        recycler_carousel.setItemAnimator(null);



         mainlayoutrecycler.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.OnItemClickListener() {
             @Override
             public void onItemClick(View view, int position) {
                 if(haveNetworkConnection()){
                    intent = new Intent(getApplicationContext(), JobPostingActivity.class);
                    //intent.putExtra("name", )
                     services = (Services) main_adapter.getItem(position);
                     intent.putExtra("service_id", services.getService_id());
                     intent.putExtra("name", services.getName());
                     intent.putExtra("imageUrl", services.getImageUrl());
                     intent.putExtra("service_tex", services.getService_tex());
                     intent.putExtra("service_text", services.getService_text());
                     intent.putExtra("tag", services.getTag());
                     intent.putExtra("base_price", services.getBase_price());
                     intent.putExtra("description", services.getDescription());
                     intent.putExtra("header_image", services.getHeader_image());
                     intent.putExtra("includes", services.getIncludes());
                     intent.putExtra("warranty", services.getWarranty());
                     if(appUser != null){
                         intent.putExtra("username", appUser.getName());
                         intent.putExtra("address", appUser.getAddress());
                         intent.putExtra("email", appUser.getEmail());
                         intent.putExtra("phone", appUser.getPhoneNumber());
                         intent.putExtra("uid", appUser.getUid());
                     }
                     startActivity(intent);
                 }
                 else{
                     noConnectionSnack(false,main2_coord);
                 }
             }
         }));
        main_adapter.notifyDataSetChanged();
        mainlayoutrecycler.setAdapter(main_adapter);

//        news_adapter.notifyDataSetChanged();
//        recycler_carousel.setAdapter(news_adapter);
       // news_adapter.notifyDataSetChanged();
       // recycler_carousel.setAdapter(news_adapter);
        //getToken("password", "2", "tHCzyYG6Iv67kVW4mJObOWuKCO0KqfhxzFYEe5DC",
        //   "shofin.cse@gmail.com", "123456");
        String m = null;
        //sendData();
        // postJob();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_history) {
            // Handle the camera action
            intent = new Intent(getApplicationContext(), OrderHistoryListActivity.class);
            intent.putExtra("uid",user.getUid());
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void logout() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        main_adapter.stopListening();
        news_adapter.stopListening();
       // sliderLayout.stopAutoCycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(dbUserRef != null){
            dbUserRef.removeEventListener(profileListener);
        }
    }

    public void updateUI(AppUser appUser){
        if(appUser!=null) {
            if(appUser.getPhotoURL()!= null) {
                Picasso.with(this)
                        .load(appUser.getPhotoURL())
                        .fit()
                        .into(imguser);
            }
            txtusername.setText(appUser.getName());
            txtuserphone.setText(appUser.getPhoneNumber());
            txtuseremail.setText(appUser.getEmail());
            btneditprofile.setVisibility(View.VISIBLE);
        }
    }

    public void editProfile(View view){
        // Intent i  = new Intent(this, UserProfileActivity.class);
        // i.putExtra("USERDATA",appUser);
        // startActivity(i);
    }


//    private void getToken(String grant_type, String client_id,
//                          String client_secret, String username,
//                          String password){
//        tokenDataAPIService.sendOauthdata(grant_type, client_id,
//                client_secret, username,
//                password).enqueue(new Callback<TokenClass>() {
//            @Override
//            public void onResponse(Call<TokenClass> call, Response<TokenClass> response) {
//                String m =response.raw().request().url().toString();
//                if(response.isSuccessful()){
//                    //String m =response.raw().request().url().toString();
//                    if (response.body() != null){
//
//                        Toast.makeText(getApplicationContext(), "Successfull", Toast.LENGTH_SHORT).show();
//                        // Toast.makeText(getApplicationContext(), response.body()., Toast.LENGTH_SHORT).show();
//
//                    }
//                    else {
//                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Sorry for inconvince server is down", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<TokenClass> call, Throwable t) {
//
//            }
//        });
//
//    }

//    public void sendData(){
//        Map<String, Object> jsonParams = new ArrayMap<>();
////put something inside the map, could be null
//        jsonParams.put("grant_type", "password");
//        jsonParams.put("client_id", "2");
//        jsonParams.put("client_secret", "tHCzyYG6Iv67kVW4mJObOWuKCO0KqfhxzFYEe5DC");
//        jsonParams.put("username", "shofin.cse@gmail.com");
//        jsonParams.put("password", "123456");
//
//        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
////serviceCaller is the interface initialized with retrofit.create...
//        Call<ResponseBody> response = tokenDataAPIService.sendToken(body);
//
//        response.enqueue(new Callback<ResponseBody>()
//        {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse)
//            {
//                try
//                {
//                    //get your response....
//                    Log.d("NSIT", "RetroFit2.0 :RetroGetLogin: " + rawResponse.body().string());
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable throwable)
//            {
//                // other stuff...
//            }
//        });
//
//
//    }



//
//    public void postJob(){
//        Map<String, Object> jsonParams = new ArrayMap<>();
////put something inside the map, could be null
//        jsonParams.put("ServiceItem", "AC Servicing");
//        jsonParams.put("Description", "Not Cooling");
//        jsonParams.put("DeviceQty", "1");
//        jsonParams.put("Brand", "1");
//        jsonParams.put("Phone", "123456");
//        jsonParams.put("Address", "Gulshan");
//
//        RequestBody body = RequestBody.create(okhttp3.MediaType.
//                parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
////serviceCaller is the interface initialized with retrofit.create...
//        Call<ResponseBody> response = tokenDataAPIService.postJob(body);
//
//        response.enqueue(new Callback<ResponseBody>()
//        {
//            @Override
//            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse)
//            {
//                try
//                {
//                    //get your response....
//                    Log.d("NSIT", "JOB POST: " + rawResponse.body().string());
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable throwable)
//            {
//                // other stuff...
//            }
//        });
//    }
    public void editUserProfile(View view){
        Intent i  = new Intent(getApplicationContext(), UserProfileActivity.class);
        i.putExtra("USERDATA",appUser);
        startActivity(i);
    }

    public Drawable createBitmapFromURL(String url){
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            return null;
        }
    }

//    public void activateSlider(){
//        for(String name : imageURLs.keySet()){
//
//            TextSliderView textSliderView = new TextSliderView(Main2Activity.this);
//
//            textSliderView
//                    .description(name)
//                    .image(imageURLs.get(name))
//                    .setScaleType(BaseSliderView.ScaleType.Fit);
//                   // .setOnSliderClickListener(this);
//
//            textSliderView.bundle(new Bundle());
//
//            textSliderView.getBundle()
//                    .putString("extra",name);
//
//            sliderLayout.addSlider(textSliderView);
//        }
//        sliderLayout.setPresetTransformer(SliderLayout.Transformer.DepthPage);
//
//        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
//
//        sliderLayout.setDuration(3000);
//
//       // sliderLayout.addOnPageChangeListener(this);
//    }

     public static float dpToPixels(int dp, android.content.Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }


    @Override
    public void onClick(View view) {
        if (!mShowingFragments) {
            //mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);
        } else {
            //mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
    }




}