package com.ganonalabs.munir.electrtech.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ganonalabs.munir.electrtech.Main2Activity;
import com.ganonalabs.munir.electrtech.MyESS;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.model.News;
import com.ganonalabs.munir.electrtech.model.Services;
import com.ganonalabs.munir.electrtech.utils.RecyclerTouchListener;
import com.ganonalabs.munir.electrtech.viewholders.NewsHolder;
import com.ganonalabs.munir.electrtech.viewholders.ServicesHolder;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

public class NewsListActivity extends AppCompatActivity {
    RecyclerView notificationRecyclerView;
    private RecyclerView.Adapter mAdapter;
    LinearLayoutManager lm;
    //private FirebaseRemoteConfig mFirebaseRemoteConfig;
    //private RemoteConfig remoteConfig;
    public ImageView headerImg;
   // private AdView adViewStoredNews;
    TextView noNitifaication;
    static boolean newsCalledAlready = false;
    private NewsHolder newsHolder;
    //private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<News,NewsHolder> newsRecyclerAdapter;
    public TextView notification_title, notification_content, id_hiddenid;
    public  ImageView icon_notification;
    public List<News> storedNewsList;
    private ProgressBar newsprogressbar;
    private Intent intent;
    private Snackbar snackbar;
    private CoordinatorLayout newscoordinator;
    private News news = new News();

    private DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference().child("news");

    public Query query = mDatabaseReference.orderByChild("revtimestamp");

    FirebaseRecyclerOptions<News> options = new FirebaseRecyclerOptions.Builder<News>()
            .setQuery(query,News.class).build();


    public FirebaseRecyclerAdapter news_adapterr = new FirebaseRecyclerAdapter<News,NewsHolder>(options) {
        @Override
        protected void onBindViewHolder(@NonNull NewsHolder holder, int position, @NonNull News model) {
           String title, description,imageurl ;
           int id;
            try{
               title = model.getTitle();
                description = model.getDescription();
                imageurl = model.getImageURL();
                id = model.getNews_id();
           }
            catch (NullPointerException e) {

                title = "Null";
                description = "Null";
                imageurl = "https://image.ibb.co/dOrZ1d/collage3.jpg";
                id = 0;
            }

            holder.notification_title.setText(title);
            holder.notification_title.setText(title);
            holder.notification_content.setText(description);
            holder.id_hiddenid.setText(String.valueOf(id));

            //holder.main2_image_wrap.setBackgroundColor(color);
            Picasso.with(getApplicationContext()).load(imageurl).into(holder.icon_notification);
            newsprogressbar.setVisibility(View.GONE);




        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stored_news_list,parent,false);

            return new NewsHolder(view);

        }

        @Override
        public void onDataChanged() {
            super.onDataChanged();
            //hideProgressDialog();
        }

        @Override
        public void onError(@NonNull DatabaseError error) {
            super.onError(error);
            Log.d("ERROR_NEWSLIST", error.toString());
            Toast.makeText(getApplicationContext(),"Error Data Loading.." ,Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_news_list);
        setSupportActionBar(toolbar);
        invalidateOptionsMenu();

        notificationRecyclerView = (RecyclerView) findViewById(R.id.notification_recycler);
        headerImg = (ImageView)findViewById(R.id.image_header_news_list) ;
        newsprogressbar = findViewById(R.id.newsprogressbar);
        //adViewStoredNews = (AdView)findViewById(R.id.adViewStoredNews);
        newscoordinator = findViewById(R.id.newscoordinator);
        lm = new LinearLayoutManager(getApplicationContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        if (!newsCalledAlready)
        {
            FirebaseDatabase.getInstance();//.setPersistenceEnabled(true);
            newsCalledAlready = true;
        }

    }



    @Override
    protected void onResume() {
        super.onResume();

        mDatabaseReference.keepSynced(true);
        notificationRecyclerView.setLayoutManager(lm);

        notificationRecyclerView.hasFixedSize();
        news_adapterr.startListening();
        news_adapterr.notifyDataSetChanged();
        notificationRecyclerView.setItemAnimator(new DefaultItemAnimator());
        notificationRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        notificationRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, new RecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                intent = new Intent(getApplicationContext(), NewsActivity.class);
                //intent.putExtra("name", )
                news = (News) news_adapterr.getItem(position);

                if(haveNetworkConnection()){

                    Intent intent = new Intent(MyESS.getContext(), NewsActivity.class);
                    intent.putExtra("news_id", news.getNews_id());
                    intent.putExtra("title", news.getTitle());
                    intent.putExtra("description", news.getDescription());
                    intent.putExtra("imageurl", news.getImageURL());


                    startActivity(intent);
                }
                else{
                    showNoNetworkSnack(false);
                }
            }
        }));


        notificationRecyclerView.setAdapter(news_adapterr);

        newsprogressbar.setVisibility(View.GONE);



    }


    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void showNoNetworkSnack(boolean isConnected){
        String message;
        int color;
        if(!isConnected){
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }
        else{
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        }
        snackbar = Snackbar.make(newscoordinator, message, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        TextView textView = (TextView)sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        snackbar.show();
    }

        @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i;

            i = new Intent(getApplicationContext(),Main2Activity.class);
            i.putExtra("NEWSLISTACIVITY" , "yes");

        startActivity(i);
    }
}
