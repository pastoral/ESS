package com.ganonalabs.munir.electrtech.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.interfaces.ItemClickListner;

public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView notification_title, notification_content, id_hiddenid;
    public ImageView icon_notification;
    public LayoutInflater layoutInflater;
    public Context context;
    public ItemClickListner itemClickListner;

    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public NewsHolder(View view){
        super(view);
        view.setOnClickListener(this);
        notification_title = view.findViewById(R.id.notification_title);
        notification_content = view.findViewById(R.id.notification_content);
        icon_notification = view.findViewById(R.id.id_notificationicon);
        id_hiddenid = view.findViewById(R.id.id_hiddenid);
    }
}
