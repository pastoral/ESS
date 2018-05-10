package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.BaseActivity;
import com.ganonalabs.munir.electrtech.R;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;
import com.squareup.picasso.Picasso;

public class JobForwardingActivity extends BaseActivity {

   private Intent intent;
   private Bundle bundle;
    private String service_id, name, imageUrl, service_tex, service_text, tag, base_price, description, header_image, includes, warranty, username, email, address, phone, uid, brand_name, capacities, problem, qty;
    private int brand_id = 0;
    private CollapsibleCalendar fwdCalendarView;
    private ImageView jobfwdImageView;
    private TextView fwd_service_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_forwarding);
        Toolbar toolbar = (Toolbar) findViewById(R.id.jobfwdtoolbar);
        fwdCalendarView = (CollapsibleCalendar) findViewById(R.id.fwdCalendarView);
        setSupportActionBar(toolbar);
        invalidateOptionsMenu();

        intent = getIntent();
        bundle = intent.getExtras();

        jobfwdImageView = findViewById(R.id.jobfwdimageview);

        service_id = intent.getStringExtra("service_id");
        name = intent.getStringExtra("name");
        imageUrl = intent.getStringExtra("imageUrl");
        service_tex = intent.getStringExtra("service_tex");
        service_text = intent.getStringExtra("service_text");
        tag = intent.getStringExtra("tag");
        base_price = intent.getStringExtra("base_price");
        description = intent.getStringExtra("description");
        header_image = intent.getStringExtra("header_image");
        includes = intent.getStringExtra("includes");
        warranty = intent.getStringExtra("warranty");
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        address = intent.getStringExtra("address");
        phone = intent.getStringExtra("phone");
        uid = intent.getStringExtra("uid");

        brand_name = intent.getStringExtra("brand_name");
        brand_id = Integer.parseInt(intent.getStringExtra("brand_id"));
        capacities = intent.getStringExtra("capacities");
        problem = intent.getStringExtra("problem");
        qty = intent.getStringExtra("qty");
        fwd_service_name = findViewById(R.id.fwd_service_name);

        fwdCalendarView.setCalendarListener(new CollapsibleCalendar.CalendarListener() {
            @Override
            public void onDaySelect() {
                Day day = fwdCalendarView.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
            }

            @Override
            public void onItemClick(View view) {

            }

            @Override
            public void onDataUpdate() {
                Day day = fwdCalendarView.getSelectedDay();
                Log.i(getClass().getName(), "Selected Day: "
                        + day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay());
            }

            @Override
            public void onMonthChange() {

            }

            @Override
            public void onWeekChange(int i) {

            }
        });
        if( header_image != null && !header_image.isEmpty()){
            Picasso.with(getApplicationContext()).load(header_image).into(jobfwdImageView);
        }
        fwd_service_name.setText(name);
    }
}
