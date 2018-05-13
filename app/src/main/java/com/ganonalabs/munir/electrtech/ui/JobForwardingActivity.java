package com.ganonalabs.munir.electrtech.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private Day day;
    private String service_time, service_date;
    private RadioGroup jobFwdRadioGroup;
    private CoordinatorLayout coord_job_fwd ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_forwarding);
        Toolbar toolbar = (Toolbar) findViewById(R.id.jobfwdtoolbar);
        fwdCalendarView = (CollapsibleCalendar) findViewById(R.id.fwdCalendarView);
        jobFwdRadioGroup = findViewById(R.id.jobFwdRadioGroup);
        coord_job_fwd = findViewById(R.id.coord_job_fwd);
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
                day = fwdCalendarView.getSelectedDay();
                service_date = day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay();
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
                service_date = day.getYear() + "/" + (day.getMonth() + 1) + "/" + day.getDay();
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

        jobFwdRadioGroup.clearCheck();
    }

    public void loadJobConfirmation(View view){
//        RadioButton rb = (RadioButton) jobFwdRadioGroup.findViewById(jobFwdRadioGroup.getCheckedRadioButtonId());
//        service_time = rb.getText().toString();
        if(haveNetworkConnection()){
            if (service_time != null && service_time.length() > 0 && service_date != null && service_date.length()>0) {
                intent = new Intent(getApplicationContext(), JobFinalActivity.class);
                intent.putExtra("service_id", service_id);
                intent.putExtra("name", name);
                intent.putExtra("imageUrl", imageUrl);
                intent.putExtra("service_tex", service_tex);
                intent.putExtra("service_text", service_text);
                intent.putExtra("tag", tag);
                intent.putExtra("base_price", base_price);
                intent.putExtra("description", description);
                intent.putExtra("header_image", header_image);
                intent.putExtra("includes", includes);
                intent.putExtra("warranty", warranty);
                intent.putExtra("username", username);
                intent.putExtra("address", address);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);
                intent.putExtra("uid", uid);

                intent.putExtra("brand_id", String.valueOf(brand_id));
                intent.putExtra("brand_name", brand_name);
                intent.putExtra("capacities", capacities);
                intent.putExtra("problem", problem);
                intent.putExtra("qty", qty);

                intent.putExtra("service_time", service_time);
                intent.putExtra("service_date", service_date);
                intent.putExtra("payment_method", "Cash on Delivery");
                startActivity(intent);

            }
            else{
                showSnack(coord_job_fwd,"Please fill those empty fields");
            }

        }
        else {
            noConnectionSnack(false, coord_job_fwd);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        jobFwdRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb && checkedId !=0) {
                    //Toast.makeText(JobForwardingActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                    service_time = rb.getText().toString();

                }
            }

        });
    }
}
