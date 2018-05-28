package com.ganonalabs.munir.electrtech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.interfaces.ItemClickListner;

public class SearchServiceHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView name_text, status_text, search_service_hidden_id;
    public ImageView profile_image;
    public ItemClickListner itemClickListner;




    @Override
    public void onClick(View v) {
        itemClickListner.onClick(v,getAdapterPosition(),false);

    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }

    public SearchServiceHolder(View view){
        super(view);
        view.setOnClickListener(this);
        //view.setOnCreateContextMenuListener(this);
        name_text = view.findViewById(R.id.name_text);
        status_text = view.findViewById(R.id.status_text);
        search_service_hidden_id = view.findViewById(R.id.search_service_hidden_id);
        profile_image = view.findViewById(R.id.profile_image);
    }

}
