package com.ganonalabs.munir.electrtech.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;


import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView orderdatetime,txtorderid,ordername;
    private List<String> status = new ArrayList<>();
    //private StepView step_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ordertoolbar);
        setSupportActionBar(toolbar);
        status.add("posted");
        status.add("accepted");
        status.add("assigned");
        status.add("completed");
        status.add("finished");

      //  step_view = findViewById(R.id.step_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // step_view.setSteps(status);
    }
}
