package com.ganonalabs.munir.electrtech.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequest;
import com.ganonalabs.munir.electrtech.viewholders.MyJobReqHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyJobReqAdapter extends RecyclerView.Adapter<MyJobReqHolder> {
    private List<MyJobRequest> myJobRequestList;
    private Context mContext;

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
    }

    @Override
    public int getItemCount() {
        if (myJobRequestList != null)
            return myJobRequestList.size();
        else
            return 0;
    }
}
