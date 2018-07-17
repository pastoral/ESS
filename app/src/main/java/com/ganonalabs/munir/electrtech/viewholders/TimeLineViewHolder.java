package com.ganonalabs.munir.electrtech.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ganonalabs.munir.electrtech.R;
import com.github.vipulasri.timelineview.TimelineView;

public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    public TimelineView mTimelineView;
    public TextView text_timeline_date,text_timeline_title;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);
        mTimelineView = itemView.findViewById(R.id.time_marker);
        text_timeline_date = itemView.findViewById(R.id.text_timeline_date);
        text_timeline_title = itemView.findViewById(R.id.text_timeline_title);
        mTimelineView.initLine(viewType);
    }
}
