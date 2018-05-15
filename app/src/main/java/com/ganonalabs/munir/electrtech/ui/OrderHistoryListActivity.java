package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ganonalabs.munir.electrtech.R;

public class OrderHistoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.orderhistorytoolbar);
        setSupportActionBar(toolbar);

        invalidateOptionsMenu();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void tempLoad(View view){
        Intent intent = new Intent(this,OrderDetailActivity.class);
        startActivity(intent);
    }
}
