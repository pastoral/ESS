package com.ganonalabs.munir.electrtech.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsHolder extends RecyclerView.ViewHolder {
    public TextView notification_title, notification_content, id_hiddenid;
    public ImageView icon_notification;
    public LayoutInflater layoutInflater;
    public Context context;

    public NewsHolder(View view){
        super(view);
        notification_title = (TextView)view.findViewById(R.id.notification_title);
        notification_content = (TextView)view.findViewById(R.id.notification_content);
        icon_notification = (ImageView)view.findViewById(R.id.id_notificationicon);
        id_hiddenid = (TextView)view.findViewById(R.id.id_hiddenid);
    }
}
