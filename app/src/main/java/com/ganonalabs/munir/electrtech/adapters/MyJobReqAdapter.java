package com.ganonalabs.munir.electrtech.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequest;
import com.ganonalabs.munir.electrtech.interfaces.ItemClickListner;
import com.ganonalabs.munir.electrtech.ui.OrderDetailActivity;
import com.ganonalabs.munir.electrtech.ui.OrderHistoryListActivity;
import com.ganonalabs.munir.electrtech.viewholders.MyJobReqHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyJobReqAdapter extends RecyclerView.Adapter<MyJobReqHolder> {
    private List<MyJobRequest> myJobRequestList;
    private Context mContext;
    private ItemClickListner itemClickListner;
    private OrderHistoryListActivity orderHistoryListActivity = new OrderHistoryListActivity();
    private String amount,status;




    public MyJobReqAdapter(List<MyJobRequest> myJobRequestList, Context mContext){
        this.myJobRequestList = myJobRequestList;
        this.mContext = mContext;


    }



    @NonNull
    @Override
    public MyJobReqHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_order_list_iem, parent, false);
        return new MyJobReqHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyJobReqHolder holder, int position) {
        holder.order_item_id.setText(myJobRequestList.get(position).getServiceId());
        holder.order_item_date.setText(myJobRequestList.get(position).getExpectedDate());
        status = myJobRequestList.get(position).getRequestStatus();
        if(status == null || status.length() < 5){
            status = "Submitted";
            holder.order_item_status.setBackgroundColor(mContext.getResources().getColor(R.color.Submitted));
        }
        else if(status.equals("Assigned")){
            holder.order_item_status.setBackgroundColor(mContext.getResources().getColor(R.color.Assigned));
        }
        else if(status.equals("Delivered")){
            holder.order_item_status.setBackgroundColor(mContext.getResources().getColor(R.color.Delivered));
        }
        else if(status.equals("Completed")){
            holder.order_item_status.setBackgroundColor(mContext.getResources().getColor(R.color.Complelted));
        }
        else if(status.equals("Finished")){
            holder.order_item_status.setBackgroundColor(mContext.getResources().getColor(R.color.Finished));
        }
        else {
            holder.order_item_status.setBackgroundColor(mContext.getResources().getColor(R.color.color2));
        }

        holder.order_item_status.setText(status);
        holder.order_item_address.setText(myJobRequestList.get(position).getAddress());
        holder.order_item_service_name.setText(myJobRequestList.get(position).getServiceItem());
        if(myJobRequestList.get(position).getJobAmount() != null){
            amount = myJobRequestList.get(position).getJobAmount() + " BDT";
            holder.order_item_job_amount.setText(amount);
            holder.linear_amount.setVisibility(View.VISIBLE);
        }
        else{
            amount = String.valueOf(0);
            holder.linear_amount.setVisibility(View.GONE);

        }


        holder.jobhiddenid.setText(myJobRequestList.get(position).getId());


        Picasso.with(mContext).load(myJobRequestList.get(position).getImageUrl()).into(holder.order_item_service_image);



        // Picasso.with(mContext).load(myJobRequestList.get(position).get).into(holder.service_image);
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onClick(View view, int position, boolean isLongPressed) {
//                Toast.makeText(orderHistoryListActivity.getApplicationContext(),myJobRequestList.get(position).getServiceId(),Toast.LENGTH_SHORT).show();
                Log.d("Clicked",myJobRequestList.get(position).getServiceId());
                Intent i = new Intent(mContext, OrderDetailActivity.class);
                i.putExtra("SERVICEID", myJobRequestList.get(position).getServiceId());
                i.putExtra("SERVICEIMAGE",myJobRequestList.get(position).getImageUrl());
                i.putExtra("SERVICEAMOUNT",myJobRequestList.get(position).getJobAmount());
                i.putExtra("SERVICEDATE",myJobRequestList.get(position).getExpectedDate());
                i.putExtra("SERVICENAME",myJobRequestList.get(position).getServiceItem());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (myJobRequestList != null)
            return myJobRequestList.size();
        else
            return 0;
    }
}