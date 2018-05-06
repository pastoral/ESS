package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.squareup.picasso.Picasso;

public class JobPostingActivity extends AppCompatActivity {

    private Bundle bundle;
    private Intent intent;
    private String service_id, name,imageUrl,service_tex,service_text,tag,base_price,description,header_image
            ,includes,warranty, username, email,address,phone,uid;
    public ImageView jobpostingimageview;
    public TextView warranty_text,quality_text, price_text,service_name,service_description,service_includes;
    public Button btn_job_post;
    public ScrollableNumberPicker number_picker_horizontal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_posting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        jobpostingimageview = findViewById(R.id.jobpostingimageview);
        warranty_text = findViewById(R.id.warranty_text);

        price_text = findViewById(R.id.price_text);
        service_name = findViewById(R.id.service_name);
        service_description = findViewById(R.id.service_description);
        service_includes = findViewById(R.id.service_includes);
        //number_picker_horizontal = findViewById(R.id.number_picker_horizontal);


        setSupportActionBar(toolbar);
        invalidateOptionsMenu();

        intent = getIntent();
        bundle = intent.getExtras();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        service_id = intent.getStringExtra("service_id");
        name = intent.getStringExtra("name");
        imageUrl = intent.getStringExtra("imageUrl");
        service_tex = intent.getStringExtra("service_tex");
        service_text = intent.getStringExtra("service_text");
        tag = intent.getStringExtra("tag");
        base_price = intent.getStringExtra("base_price");
        description = intent.getStringExtra("description");
        header_image = intent.getStringExtra("header_image");
        includes = intent.getStringExtra("includes");
        warranty = intent.getStringExtra("warranty");
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        address = intent.getStringExtra("address");
        phone = intent.getStringExtra("phone");
        uid = intent.getStringExtra("uid");

       if( header_image != null && !header_image.isEmpty()){
           Picasso.with(getApplicationContext()).load(header_image).into(jobpostingimageview);
       }

    }

    @Override
    protected void onResume() {
        super.onResume();
        warranty_text.setText(warranty);
        price_text.setText(base_price + " BDT");
        service_name.setText(name);
        service_description.setText(description);
        service_includes.setText(includes);

    }

    public void loadJobSchedule(View view){
        intent = new Intent(getApplicationContext(),JobSchedulingActivity.class);
        startActivity(intent);
    }
}
