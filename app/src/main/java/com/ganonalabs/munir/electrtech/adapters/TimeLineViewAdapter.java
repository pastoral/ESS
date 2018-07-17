package com.ganonalabs.munir.electrtech.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ganonalabs.munir.electrtech.R;
import com.ganonalabs.munir.electrtech.data.model.JobDetails.Datum_;
import com.ganonalabs.munir.electrtech.data.model.JobRequests.MyJobRequest;
import com.ganonalabs.munir.electrtech.utils.VectorDrawableUtils;
import com.ganonalabs.munir.electrtech.viewholders.MyJobReqHolder;
import com.ganonalabs.munir.electrtech.viewholders.TimeLineViewHolder;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class TimeLineViewAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {
    private List<Datum_> jobStatusList;
    private Context mContext;

    public TimeLineViewAdapter(List<Datum_> jobStatusList, Context mContext){
        this.jobStatusList = jobStatusList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_timeline, null);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {
        Datum_ timeLineModel = jobStatusList.get(position);

//        if(timeLineModel.getStatus() == OrderStatus.INACTIVE) {
//            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_inactive, android.R.color.darker_gray));
//        } else if(timeLineModel.getStatus() == OrderStatus.ACTIVE) {
//            holder.mTimelineView.setMarker(VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_active, R.color.colorPrimary));
//        } else {
            holder.mTimelineView.setMarker(ContextCompat.getDrawable(mContext, R.drawable.notification_done), ContextCompat.getColor(mContext, R.color.colorPrimary));
        //}

        if(!timeLineModel.getDate().isEmpty()) {
            holder.text_timeline_date.setVisibility(View.VISIBLE);
            holder.text_timeline_date.setText(timeLineModel.getDate());
        }
        else
            holder.text_timeline_date.setVisibility(View.GONE);

        holder.text_timeline_title.setText(timeLineModel.getStatus());
    }

    @Override
    public int getItemCount() {
        return (jobStatusList!=null? jobStatusList.size():0);
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }
}
