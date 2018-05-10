package com.ganonalabs.munir.electrtech.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.data.model.Brands_Data;

import java.util.List;

public class CustomSpinnerArrayAdapter extends ArrayAdapter<String> {
    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<Brands_Data> items;
    private final int mResource;

    public CustomSpinnerArrayAdapter(@NonNull Context context, @LayoutRes int resource,
                                     @NonNull List objects){
        super(context, resource, 0, objects);
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        items = objects;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView t = view.findViewById(R.id.spinner_tet);

        Brands_Data brands_data = items.get(position);

        t.setText(brands_data.getName());


        return view;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }
}
