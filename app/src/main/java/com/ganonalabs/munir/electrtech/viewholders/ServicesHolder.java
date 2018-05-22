package com.ganonalabs.munir.electrtech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.interfaces.ItemClickListner;

import java.util.Random;

public class ServicesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView service_text, service_tex, service_hidden_id;
    public ImageView service_image;
    public ItemClickListner itemClickListner;
    public LinearLayout main2_image_wrap;



    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public ServicesHolder(View view){
        super(view);
        view.setOnClickListener(this);
        //view.setOnCreateContextMenuListener(this);
        service_text = view.findViewById(R.id.service_text);
        service_tex = view.findViewById(R.id.service_tex);
        service_hidden_id = view.findViewById(R.id.service_hidden_id);
        service_image = view.findViewById(R.id.service_image);
        main2_image_wrap = view.findViewById(R.id.main2_image_wrap);
    }


}
