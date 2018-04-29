package com.ganonalabs.munir.electrtech;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ganonalabs.munir.electrtech.model.Services;
import com.ganonalabs.munir.electrtech.viewholders.ServicesHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Main2Activity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TextView mTextMessage;
    private final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ArrayList<Object> userData, userProviders;
    //private Query query;
    //private FirebaseDatabase firebaseDatabase;
    public RecyclerView mainlayoutrecycler;
    public RecyclerView.LayoutManager lm;


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

    private Query query = FirebaseDatabase.getInstance()
            .getReference()
                .child("services")
                .orderByChild("service_id")
                .limitToLast(50);

    FirebaseRecyclerOptions<Services> options = new FirebaseRecyclerOptions.Builder<Services>()
                                                    .setQuery(query,Services.class).build();


    public FirebaseRecyclerAdapter main_adapter = new FirebaseRecyclerAdapter<Services,ServicesHolder>(options) {
        @Override
         protected void onBindViewHolder(@NonNull ServicesHolder holder, int position, @NonNull Services model) {
            holder.service_text.setText(model.getService_text());
            holder.service_tex.setText(model.getService_tex());
            holder.service_hidden_id.setText(model.getService_id());
            Picasso.with(getApplicationContext()).load(model.getImageUrl()).into(holder.service_image);
        }

        @Override
        public ServicesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_items,parent,false);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //showProgressDialog("Loading Data", getApplicationContext());

        mTextMessage = (TextView) findViewById(R.id.message);
        mainlayoutrecycler = findViewById(R.id.mainlayoutrecycler);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        lm = new GridLayoutManager(getApplicationContext(),2);
        //lm.setOrientation(LinearLayoutManager.VERTICAL);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
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
                String temp = "" ;
            }
        }
        main_adapter.startListening();
    }

    @Override
    protected void onResume() {
        super.onResume();



        mainlayoutrecycler.setLayoutManager(lm);
        mainlayoutrecycler.setItemAnimator(new DefaultItemAnimator());
        //mainlayoutrecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //mainlayoutrecycler.notifyDataSetChanged();
        //mainlayoutrecycler.startListening();
        main_adapter.notifyDataSetChanged();
        mainlayoutrecycler.setAdapter(main_adapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
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
    }
}
