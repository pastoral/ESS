package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.ganonalabs.munir.electrtech.BaseActivity;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.adapters.CustomSpinnerArrayAdapter;
import com.ganonalabs.munir.electrtech.data.model.Brands;
import com.ganonalabs.munir.electrtech.data.model.Brands_Data;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiService;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiUtils;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.michaelmuenzer.android.scrollablennumberpicker.ScrollableNumberPicker;


import com.squareup.picasso.Picasso;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Query;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;


public class JobSchedulingActivity extends BaseActivity {

    private String service_id, name, imageUrl, service_tex, service_text, tag, base_price, description, header_image, includes, warranty, username, email, address, phone, uid, brand_name, capacities, problem, qty;

    private int brand_id = 0;
    public ImageView jobschedulingimageview;
    public ScrollableNumberPicker number_picker_horizontal;
    private Bundle bundle;
    private Intent intent;
    private EditText user_name, user_email, user_phone, user_address, capacity, problem_description, service_quantiy;
    private int quantiy = 0;
    private CoordinatorLayout coord_job_schedule;
    private TextView schedule_service_name;
    private TokenDataApiService tokenDataAPIService = TokenDataApiUtils.getUserDataAPIServices();
  // private List<Brands> data = new ArrayList<>();
   private List<Brands_Data> brands_data;
    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };
    public Spinner textView;


    ArrayList<Integer> brandID= new ArrayList<>();
    ArrayList<String> brandName = new ArrayList<>();
    public ArrayAdapter<String> adapter ;
  // public  ArrayAdapter<String> adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.job_schedule_toolbar);
        coord_job_schedule = findViewById(R.id.coord_job_schedule);
        schedule_service_name = findViewById(R.id.schedule_service_name);
        setSupportActionBar(toolbar);
        invalidateOptionsMenu();

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

        user_name = findViewById(R.id.user_name);
        user_email = findViewById(R.id.user_email);
        user_phone = findViewById(R.id.user_phone);
        user_address = findViewById(R.id.user_address);
        //service_brand = findViewById(R.id.service_brand);
        capacity = findViewById(R.id.capacity);
        problem_description = findViewById(R.id.problem_description);
        service_quantiy = findViewById(R.id.service_quantiy);
        //number_picker_horizontal = findViewById(R.id.number_picker_horizontal);
        jobschedulingimageview = findViewById(R.id.jobschedulingimageview);

        if(user_address != null){
            user_address.setHorizontallyScrolling(false);
            user_address.setLines(2);
        }
        if(problem_description != null){
            problem_description.setHorizontallyScrolling(false);
            problem_description.setLines(3);
        }
    textView = findViewById(R.id.spinner);

        if( header_image != null && !header_image.isEmpty()){
            Picasso.with(getApplicationContext()).load(header_image).into(jobschedulingimageview);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getBrandInfo();
       // brand = service_brand.getText().toString();




        user_name.setText(username);
        user_phone.setText(phone);
        user_email.setText(email);
        user_address.setText(address);
        schedule_service_name.setText(name);

        if(bundle.getString("capacities") != null && !bundle.getString("capacities").isEmpty()){
            capacity.setText( intent.getStringExtra("capacities"));
        }
        if(bundle.getString("problem") != null && !bundle.getString("problem").isEmpty()){
            problem_description.setText( intent.getStringExtra("problem"));
        }
        if(bundle.getString("qty") != null && !bundle.getString("qty").isEmpty()){
            service_quantiy.setText( intent.getStringExtra("qty"));
        }


//        if(brands_data!=null && brands_data.size()>0 && adapter != null) {
//
//        }

        textView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem =  parent.getItemAtPosition(position).toString();
                if(!selectedItem.equals("Select Model")){
                    //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                    brand_id = position;
                    brand_name = selectedItem;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void loadJobScheduling(View view) {
        if (haveNetworkConnection()) {
            if (user_name.getText().toString() == null || user_name.getText().toString().length() < 2) {
                new MaterialDialog.Builder(this)
                        .title("Scheduling Failed")
                        .content("User name empty")
                        .neutralText("Close")
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            } else {
                username = user_name.getText().toString();
            }
            if (user_phone.getText().toString() == null || user_phone.getText().toString().length() < 8) {
                new MaterialDialog.Builder(this)
                        .title("Scheduling Failed")
                        .content("Phone Number empty")
                        .neutralText("Close")
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            } else {
                phone = user_phone.getText().toString();
            }

            if (user_address.getText().toString() == null || user_address.getText().toString().length() < 1) {
                new MaterialDialog.Builder(this)
                        .title("Scheduling Failed")
                        .content("Address empty")
                        .neutralText("Close")
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            } else {
                address = user_address.getText().toString();
            }


            if (brand_id == 0) {
                new MaterialDialog.Builder(this)
                        .title("Scheduling Failed")
                        .content("Please select Brand")
                        .neutralText("Close")
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            } else {
                brand_id = brand_id;
            }

            if (service_quantiy.getText().toString() == null || service_quantiy.getText().toString().length()<1) {
                new MaterialDialog.Builder(this)
                        .title("Scheduling Failed")
                        .content("Qantity empty")
                        .neutralText("Close")
                        .onNeutral(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
            else{
                qty = service_quantiy.getText().toString();
            }

            capacities = capacity.getText().toString();
            problem = problem_description.getText().toString();
            qty = service_quantiy.getText().toString();

            if (qty.length() > 0 && brand_id != 0 && address.length() > 0 && phone.length() > 6 && username.length() > 0) {
                intent = new Intent(getApplicationContext(), JobForwardingActivity.class);

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

                startActivity(intent);

                }
                else{
                    showSnack(coord_job_schedule,"Please fill those empty fields");
                }
            }
            else {
                noConnectionSnack(false, coord_job_schedule);
            }
        }


    public void getBrandInfo(){
                Map<String, Object> jsonParams = new ArrayMap<>();


        RequestBody body = RequestBody.create(okhttp3.MediaType.
                parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
//serviceCaller is the interface initialized with retrofit.create...
        Call<Brands> response = tokenDataAPIService.getBrands();

        response.enqueue(new Callback<Brands>()
        {
            @Override
            public void onResponse(Call<Brands> call, retrofit2.Response<Brands> rawResponse)
            {
                try
                {
                    List<Brands_Data> data = rawResponse.body().getData();
                    brandName.add("Select Model");
                    for(int i = 0; i<data.size(); i++){
                        brandID.add(data.get(i).getId());
                        brandName.add(data.get(i).getName());
                    }

                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, brandName);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    textView.setAdapter(adapter);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Brands> call, Throwable throwable)
            {
                // other stuff...
            }
        });
    }





}
