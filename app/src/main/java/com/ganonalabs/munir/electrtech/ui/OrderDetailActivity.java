package com.ganonalabs.munir.electrtech.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.LoginActivity;
import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.adapters.MyJobReqAdapter;
import com.ganonalabs.munir.electrtech.adapters.TimeLineViewAdapter;
import com.ganonalabs.munir.electrtech.data.model.JobDetails.Datum;
import com.ganonalabs.munir.electrtech.data.model.JobDetails.Datum_;
import com.ganonalabs.munir.electrtech.data.model.JobDetails.JobDetails;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequest;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequestList;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiService;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class OrderDetailActivity extends AppCompatActivity {

    private String orderid,imageurl,date,amount,name, servicemanname, servicemanimage, servicemanphone, billdetails, uid;
    private TextView orderdatetime,txtorderid,ordername,txtbills,service_man_name,txtbilldetails;
   // private EditText txtbilldetails;
    private ImageView service_man_image,service_man_phone, order_image;
    //private List<StepBean> status = new ArrayList<>();
    private Intent intent;
    private Bundle bundle;
    private TokenDataApiService tokenDataAPIService = TokenDataApiUtils.getUserDataAPIServices();
    private List<Datum> data = new ArrayList<Datum>();
    private ProgressBar jobDetailProgressBar;
    private boolean mWithLinePadding;
    private RecyclerView timelineRecyclerView;
    private TimeLineViewAdapter mTimeLineAdapter;
    private List<Datum_> mDataList = new ArrayList<>();
    private Context mContext;
    private FirebaseUser user;



    List<String> list= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ordertoolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();
        bundle = intent.getExtras();

        mContext = OrderDetailActivity.this;

        orderid = intent.getStringExtra("SERVICEID");
        imageurl = intent.getStringExtra("SERVICEIMAGE");
        date = intent.getStringExtra("SERVICEDATE");
        amount = "Total: "+intent.getStringExtra("SERVICEAMOUNT") + " BDT";
        name = intent.getStringExtra("SERVICENAME");

        orderdatetime = findViewById(R.id.orderdatetime);
        txtorderid = findViewById(R.id.txtorderid);
        ordername = findViewById(R.id.ordername);
        txtbills = findViewById(R.id.txtbills);
        service_man_name = findViewById(R.id.service_man_name);
        service_man_image = findViewById(R.id.service_man_image);
        jobDetailProgressBar = findViewById(R.id.jobDetailProgressBar);
        order_image = findViewById(R.id.order_image);
        orderdatetime = findViewById(R.id.orderdatetime);
        timelineRecyclerView = findViewById(R.id.timelineRecyclerView);
        txtbilldetails = findViewById(R.id.txtbilldetails);

//        list.add("Service posted");
//        list.add("Service accepted");
//        list.add("Service assigned");
//        list.add("Service completed");
//        list.add("Service finished");
//
//
//
//        status.add(new StepBean("Service posted",1));
//        status.add(new StepBean("Service accepted",1));
//        status.add(new StepBean("assigned",0));
//        status.add(new StepBean("completed",-1));
        //status.add(new StepBean("finished",5));





      //  step_view = findViewById(R.id.step_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // step_view.setSteps(status);
        jobDetailProgressBar.setVisibility(View.VISIBLE);


        timelineRecyclerView.setLayoutManager(getLinearLayoutManager());
        timelineRecyclerView.setHasFixedSize(true);

        getMyJobRequestInfo();
    }


    @Override
    protected void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            uid = user.getUid();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        txtorderid.setText(orderid);
        orderdatetime.setText(date);
        if(imageurl!=null) {
            Picasso.with(this).load(imageurl).into(order_image);
        }
        ordername.setText(name);
        txtbills.setText(amount);


    }



    public void getMyJobRequestInfo(){
        Map<String, Object> jsonParams = new ArrayMap<>();

        String url = "http://e-tech.com.bd/ess/api/jobRequestByService/"+orderid;
        RequestBody body = RequestBody.create(okhttp3.MediaType.
                parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
//serviceCaller is the interface initialized with retrofit.create...
        Call<JobDetails> response = tokenDataAPIService.getJobDetails(url);


        response.enqueue(new Callback<JobDetails>()
        {
            @Override
            public void onResponse(Call<JobDetails> call, retrofit2.Response<JobDetails> rawResponse)
            {
                try
                {
                    data = rawResponse.body().getData();
                    Log.d("ESS_JOB_DETAIL ", data.toString());
                    if(data.size()>0){

                      //  mTimeLineAdapter = new TimeLineViewAdapter(data,mContext);
                        for(int i = 0; i<data.size(); i++){
                            for(int j = i; j < data.get(i).getRequestStatus().getData().size(); j++){

                                mDataList.add(data.get(i).getRequestStatus().getData().get(j));
                            }
                            servicemanname = data.get(i).getEmployeesName().toString();
                            servicemanimage = data.get(i).getPhoto();
                            servicemanphone = data.get(i).getEmployeesPhone().toString();
                            billdetails = data.get(i).getBilling().toString();

                            String x = data.get(i).getTotalAmount().toString();
                        }

                        Picasso.with(mContext).load(servicemanimage).into(service_man_image);
                        service_man_name.setText(servicemanname);
                        txtbilldetails.setText(billdetails);



                        mTimeLineAdapter = new TimeLineViewAdapter(mDataList,mContext);
                        mTimeLineAdapter.notifyDataSetChanged();
                       timelineRecyclerView.setAdapter(mTimeLineAdapter);

                        jobDetailProgressBar.setVisibility(View.GONE);
                    }



//                    brandName.add("Select Model");
//                    for(int i = 0; i<data.size(); i++){
//                        brandID.add(data.get(i).getId());
//                        brandName.add(data.get(i).getName());
//                    }
//
//                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, brandName);
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    textView.setAdapter(adapter);
                }
                catch (Exception e)
                {
                    service_man_image.setVisibility(View.GONE);
                    service_man_name.setVisibility(View.GONE);
                    txtbilldetails.setVisibility(View.GONE);
                    timelineRecyclerView.setVisibility(View.GONE);
                    jobDetailProgressBar.setVisibility(View.GONE);
                    txtbills.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<JobDetails> call, Throwable throwable)
            {
                // other stuff...
            }
        });
    }


    private LinearLayoutManager getLinearLayoutManager() {

            return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

    }

//    private void initView() {
//        setDataListItems();
//        mTimeLineAdapter = new TimeLineAdapter(mDataList, mOrientation, mWithLinePadding);
//        mRecyclerView.setAdapter(mTimeLineAdapter);
//    }

    public void dialServiceman(View view){
        if(servicemanphone != null) {
            Uri number = Uri.parse("tel:" + servicemanphone);
            Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
            getApplicationContext().startActivity(callIntent);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, OrderHistoryListActivity.class);
        i.putExtra("uid",user.getUid());
        startActivity(i);
    }
}
