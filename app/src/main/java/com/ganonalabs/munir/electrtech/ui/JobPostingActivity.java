package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.BaseActivity;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiService;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiUtils;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class JobPostingActivity extends BaseActivity {

    private Bundle bundle;
    private Intent intent;
    private String service_id, name,imageUrl,service_tex,service_text,tag,base_price,description,header_image
            ,includes,warranty, username, email,address,phone,uid;
    public ImageView jobpostingimageview;
    public TextView warranty_text,quality_text, price_text,service_name,service_description,service_includes;
    public Button btn_job_post;
    public ScrollableNumberPicker number_picker_horizontal;
    public CoordinatorLayout coord_job_post;

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
        coord_job_post = findViewById(R.id.coord_job_post);
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
        if(haveNetworkConnection()) {
            intent = new Intent(getApplicationContext(), JobSchedulingActivity.class);
            intent.putExtra("service_id", service_id);
            intent.putExtra("name", name);
            intent.putExtra("imageUrl", imageUrl);
            intent.putExtra("service_tex", service_tex);
            intent.putExtra("service_text", service_text);
            intent.putExtra("tag", tag);
            intent.putExtra("base_price", base_price);
            intent.putExtra("description", description);
            intent.putExtra("header_image", header_image);
            intent.putExtra("includes", includes);
            intent.putExtra("warranty", warranty);
            intent.putExtra("username", username);
            intent.putExtra("address", address);
            intent.putExtra("email", email);
            intent.putExtra("phone", phone);
            intent.putExtra("uid", uid);

            startActivity(intent);
        }
        else{
            noConnectionSnack(false,coord_job_post);
        }

    }


}
