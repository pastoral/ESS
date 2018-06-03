package com.ganonalabs.munir.electrtech.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;

public class MyJobReqHolder extends RecyclerView.ViewHolder{
    public TextView order_item_id,order_item_date,order_item_status,jobhiddenid,order_item_address;
    public ImageView order_item_service_image;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyJobReqHolder(View view){
        super(view);
        order_item_id = view.findViewById(R.id.order_item_id);
        order_item_date = view.findViewById(R.id.order_item_date);
        order_item_status = view.findViewById(R.id.order_item_status);
        jobhiddenid = view.findViewById(R.id.jobhiddenid);
        order_item_address = view.findViewById(R.id.order_item_address);
        order_item_service_image = view.findViewById(R.id.order_item_service_image);
    }
}
