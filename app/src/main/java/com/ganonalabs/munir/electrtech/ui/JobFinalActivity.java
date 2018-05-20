package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ganonalabs.munir.electrtech.BaseActivity;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiService;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiUtils;
import com.jpardogo.android.googleprogressbar.library.ChromeFloatingCirclesDrawable;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.jpardogo.android.googleprogressbar.library.NexusRotationCrossDrawable;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

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
    private TokenDataApiService tokenDataAPIService = TokenDataApiUtils.getUserDataAPIServices();
    private ProgressBar mProgressbar;
    private LinearLayout linlaHeaderProgress;
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
        mProgressbar = findViewById(R.id.final_progressbar);
        linlaHeaderProgress = findViewById(R.id.linlaHeaderProgress);

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
           // Toast.makeText(getApplicationContext(),"Clicked", Toast.LENGTH_SHORT).show();

            postJob();

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


        public void postJob(){
      // linlaHeaderProgress.setVisibility(View.VISIBLE);
                Map<String, Object> jsonParams = new ArrayMap<>();
//put something inside the map, could be null
        jsonParams.put("ServiceItem", name);
        jsonParams.put("Description", problem);
        jsonParams.put("DeviceQty", qty);
        jsonParams.put("Brand", brand_id);
        jsonParams.put("Phone", phone);
        jsonParams.put("Address", address);
            jsonParams.put("Name", username);
            jsonParams.put("Email", email);
            jsonParams.put("Capacity", capacities);
            jsonParams.put("ExpectedDate", service_date);
            jsonParams.put("ExpectedTime", service_time);
            jsonParams.put("ReqCreatedBy", uid);
            jsonParams.put("PaymentMethod", 1);


        RequestBody body = RequestBody.create(okhttp3.MediaType.
                parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
//serviceCaller is the interface initialized with retrofit.create...
        Call<ResponseBody> response = tokenDataAPIService.postJob(body);

        response.enqueue(new Callback<ResponseBody>()
        {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> rawResponse)
            {
                try
                {
                    //get your response....
                    Log.d("NSIT", "JOB POST: " + rawResponse.body().string());


                    linlaHeaderProgress.setVisibility(View.GONE);
                    new MaterialDialog.Builder(getApplicationContext())
                            .title("Order posted successfully")
                            .backgroundColor(getResources().getColor(R.color.com_facebook_blue))
                            .neutralText("Close")
                            .onNeutral(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    finish();
                                }
                            })
                            .show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable)
            {
                // other stuff...
            }
        });
    }
}
