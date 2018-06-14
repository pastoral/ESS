package com.ganonalabs.munir.electrtech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ganonalabs.munir.electrtech.R;

public class OfferHolder extends RecyclerView.ViewHolder {
    public ImageView offer_card_image;
    public OfferHolder(View view){
        super(view);
        offer_card_image = view.findViewById(R.id.offer_card_image);
    }
}
