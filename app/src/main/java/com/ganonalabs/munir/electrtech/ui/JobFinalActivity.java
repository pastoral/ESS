package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.BaseActivity;
import com.ganonalabs.munir.electrtech.R;
import com.squareup.picasso.Picasso;

public class JobFinalActivity extends BaseActivity {

    private Intent intent;
    private Bundle bundle;
    private String service_id, name, imageUrl, service_tex, service_text, tag, base_price, description, header_image, includes, warranty, username, email, address, phone, uid, brand_name, capacities, problem, qty, service_time, service_date, payment_method;
    private CoordinatorLayout coord_job_final;
    private TextView final_service_name,final_user_email, final_user_phone,final_user_address,
            final_spinner,final_service_quantiy,final_capacity,final_problem_description,final_service_date,
            final_service_time, final_payment_method, final_user_name;
    private ImageView jobfinalimageview;
    private int brand_id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_final);
        Toolbar toolbar = (Toolbar) findViewById(R.id.jobfinaltoolbar);
        setSupportActionBar(toolbar);
        invalidateOptionsMenu();

        coord_job_final = findViewById(R.id.coord_job_final);

        final_service_name = findViewById(R.id.final_service_name);
        final_user_name = findViewById(R.id.final_user_name);
        final_user_email = findViewById(R.id.final_user_email);
        final_user_phone = findViewById(R.id.final_user_phone);
        final_user_address = findViewById(R.id.final_user_address);
        final_spinner = findViewById(R.id.final_spinner);
        final_service_quantiy = findViewById(R.id.final_service_quantiy);
        final_capacity = findViewById(R.id.final_capacity);
        final_problem_description = findViewById(R.id.final_problem_description);
        final_service_date = findViewById(R.id.final_service_date);
        final_service_time = findViewById(R.id.final_service_time);
        final_payment_method = findViewById(R.id.final_payment_method);
        jobfinalimageview = findViewById(R.id.jobfinalimageview);

        intent = getIntent();
        bundle = intent.getExtras();

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

        brand_name = intent.getStringExtra("brand_name");
        brand_id = Integer.parseInt(intent.getStringExtra("brand_id"));
        capacities = intent.getStringExtra("capacities");
        problem = intent.getStringExtra("problem");
        qty = intent.getStringExtra("qty");

        service_time = intent.getStringExtra("service_time");
        service_date = intent.getStringExtra("service_date");
        payment_method = intent.getStringExtra("payment_method");



        final_service_name.setText(name);
        final_user_name.setText(username);
        final_user_email.setText(email);
        final_user_phone.setText(phone);
        final_user_address.setText(address);
        final_spinner.setText(brand_name);
        final_service_quantiy.setText(qty);
        final_capacity.setText(capacities);
        final_problem_description.setText(problem);
        final_service_date.setText(service_date);
        final_service_time.setText(service_time);
        final_payment_method.setText(payment_method);

        if( header_image != null && !header_image.isEmpty()){
            Picasso.with(getApplicationContext()).load(header_image).into(jobfinalimageview);
        }


    }
    public void loadJobFinalization(View view){

        if(haveNetworkConnection()){

        }
        else{
            noConnectionSnack(false, coord_job_final);
        }
    }
    public void loadJobEdit(View view){
        intent = new Intent(getApplicationContext(),JobSchedulingActivity.class);

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

        intent.putExtra("brand_id", String.valueOf(brand_id));
        intent.putExtra("brand_name", brand_name);
        intent.putExtra("capacities", capacities);
        intent.putExtra("problem", problem);
        intent.putExtra("qty", qty);

        intent.putExtra("service_time", service_time);
        intent.putExtra("service_date", service_date);
        intent.putExtra("payment_method", payment_method);

        startActivity(intent);

    }
}
