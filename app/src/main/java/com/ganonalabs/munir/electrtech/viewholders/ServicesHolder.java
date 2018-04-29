package com.ganonalabs.munir.electrtech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;

public class ServicesHolder extends RecyclerView.ViewHolder {
    public TextView service_text, service_tex, service_hidden_id;
    public ImageView service_image;

    public ServicesHolder(View view){
        super(view);
        service_text = view.findViewById(R.id.service_text);
        service_tex = view.findViewById(R.id.service_tex);
        service_hidden_id = view.findViewById(R.id.service_hidden_id);
        service_image = view.findViewById(R.id.service_image);
    }
}
