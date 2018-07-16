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
        holder.order_item_status.setText(myJobRequestList.get(position).getRequestStatus());
        holder.order_item_address.setText(myJobRequestList.get(position).getAddress());
        holder.order_item_service_name.setText(myJobRequestList.get(position).getServiceItem());
        holder.jobhiddenid.setText(myJobRequestList.get(position).getId());


        // Picasso.with(mContext).load(myJobRequestList.get(position).get).into(holder.service_image);
        holder.setItemClickListner(new ItemClickListner() {
            @Override
            public void onClick(View view, int position, boolean isLongPressed) {
//                Toast.makeText(orderHistoryListActivity.getApplicationContext(),myJobRequestList.get(position).getServiceId(),Toast.LENGTH_SHORT).show();
                Log.d("Clicked",myJobRequestList.get(position).getServiceId());
                Intent i = new Intent(mContext, OrderDetailActivity.class);
                i.putExtra("SERVICEID", myJobRequestList.get(position).getServiceId());
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