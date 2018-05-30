package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.model.News;
import com.ganonalabs.munir.electrtech.model.Services;
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
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<News,NewsHolder> newsRecyclerAdapter;
    public TextView notification_title, notification_content, id_hiddenid;
    public  ImageView icon_notification;
    public List<News> storedNewsList;
    private ProgressBar newsprogressbar;
    private Intent intent;
    private Snackbar snackbar;
    private CoordinatorLayout newscoordinator;


    public Query query = mDatabaseReference
            .orderByChild("revtimestamp")
            .limitToLast(50);

    FirebaseRecyclerOptions<News> options = new FirebaseRecyclerOptions.Builder<News>()
            .setQuery(query,News.class).build();


    public FirebaseRecyclerAdapter main_adapter = new FirebaseRecyclerAdapter<News,NewsHolder>(options) {
        @Override
        protected void onBindViewHolder(@NonNull NewsHolder holder, int position, @NonNull News model) {
            holder.notification_title.setText(model.getTitle());
            holder.notification_content.setText(model.getDescription());

            //holder.main2_image_wrap.setBackgroundColor(color);
            Picasso.with(getApplicationContext()).load(model.getImageURL()).into(holder.icon_notification);

        }

        @Override
        public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_items,parent,false);

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
            Toast.makeText(getApplicationContext(),"Error Data Loading..",Toast.LENGTH_SHORT).show();
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
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("news");
        mDatabaseReference.keepSynced(true);



    }
}
