package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.adapters.MyJobReqAdapter;
import com.ganonalabs.munir.electrtech.data.model.Brands;
import com.ganonalabs.munir.electrtech.data.model.Brands_Data;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequest;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequestList;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiService;
import com.ganonalabs.munir.electrtech.data.remote.TokenDataApiUtils;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class OrderHistoryListActivity extends AppCompatActivity {
    private TokenDataApiService tokenDataAPIService = TokenDataApiUtils.getUserDataAPIServices();
    private MyJobReqAdapter myJobReqAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.orderhistorytoolbar);
        setSupportActionBar(toolbar);

        invalidateOptionsMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void tempLoad(View view){
        Intent intent = new Intent(this,OrderDetailActivity.class);
        startActivity(intent);
    }

    public void getMyJobRequestInfo(){
        Map<String, Object> jsonParams = new ArrayMap<>();


        RequestBody body = RequestBody.create(okhttp3.MediaType.
                parse("application/json; charset=utf-8"),(new JSONObject(jsonParams)).toString());
//serviceCaller is the interface initialized with retrofit.create...
        Call<MyJobRequestList> response = tokenDataAPIService.getMyJobRequests();

        response.enqueue(new Callback<MyJobRequestList>()
        {
            @Override
            public void onResponse(Call<MyJobRequestList> call, retrofit2.Response<MyJobRequestList> rawResponse)
            {
                try
                {
                      List<MyJobRequest> data = rawResponse.body().getData();
                    Log.d("ESS_JOB ", data.toString());
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
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MyJobRequestList> call, Throwable throwable)
            {
                // other stuff...
            }
        });
    }
}
