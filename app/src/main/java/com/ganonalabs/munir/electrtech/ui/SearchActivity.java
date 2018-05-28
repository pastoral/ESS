package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.model.AppUser;
import com.ganonalabs.munir.electrtech.model.Services;
import com.ganonalabs.munir.electrtech.viewholders.SearchServiceHolder;
import com.ganonalabs.munir.electrtech.viewholders.ServicesHolder;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class SearchActivity extends AppCompatActivity {

    public RecyclerView searchlayoutrecycler;
    private AppUser appUser;
    private Intent i;
    public DatabaseReference serviceDB = FirebaseDatabase.getInstance()
            .getReference()
            .child("services");
    public FirebaseRecyclerAdapter search_adapter;
    private LinearLayoutManager lm;
    //private int[] andColors = getResources().getIntArray(R.array.androidcolors);
    private String searchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar search_toolbar = (Toolbar) findViewById(R.id.search_toolbar);
        setSupportActionBar(search_toolbar);
        searchlayoutrecycler = findViewById(R.id.searchlayoutrecycler);
        i = getIntent();

        appUser = (AppUser)i.getSerializableExtra("USERDATA");
        searchText = i.getStringExtra("searchText");
        lm = new LinearLayoutManager(getApplicationContext());



        Query query = serviceDB
        .orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");

        FirebaseRecyclerOptions<Services> options = new FirebaseRecyclerOptions.Builder<Services>()
                .setQuery(query,Services.class).build();


        search_adapter = new FirebaseRecyclerAdapter<Services,SearchServiceHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull SearchServiceHolder holder, int position, @NonNull Services model) {
                holder.name_text.setText(model.getService_text());
                holder.status_text.setText(model.getService_tex());
                holder.search_service_hidden_id.setText(model.getService_id());
               // int color = andColors[new Random().nextInt(andColors.length)];
                Picasso.with(getApplicationContext()).load(model.getImageUrl()).into(holder.profile_image);

            }

            @Override
            public SearchServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_search_list_item,parent,false);

                return new SearchServiceHolder(view);

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(search_adapter != null) {
            search_adapter.startListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchlayoutrecycler.setLayoutManager(lm);
        searchlayoutrecycler.setItemAnimator(null);

        search_adapter.notifyDataSetChanged();
        searchlayoutrecycler.setAdapter(search_adapter);
    }
}
