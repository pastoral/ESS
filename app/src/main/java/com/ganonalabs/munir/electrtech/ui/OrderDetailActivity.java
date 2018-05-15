package com.ganonalabs.munir.electrtech.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.ganonalabs.munir.electrtech.R;


import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {

    private TextView orderdatetime,txtorderid,ordername;
    private List<StepBean> status = new ArrayList<>();
    private HorizontalStepView step_view;
    //private StepView step_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ordertoolbar);
        setSupportActionBar(toolbar);
        status.add(new StepBean("Service posted",1));
        status.add(new StepBean("Service accepted",1));
        status.add(new StepBean("assigned",0));
        status.add(new StepBean("completed",-1));
        //status.add(new StepBean("finished",5));


        step_view = findViewById(R.id.step_view);


      //  step_view = findViewById(R.id.step_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // step_view.setSteps(status);
    }

    @Override
    protected void onResume() {
        super.onResume();
        step_view.setStepViewTexts(status)
                .setTextSize(12)
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_green_dark))
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(getApplicationContext(), R.color.uncompleted_text_color))
                .setStepViewComplectedTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.holo_blue_dark))
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(getApplicationContext(), R.color.uncompleted_text_color))
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.notification_done))
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_icon))
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.attention));




    }
}
