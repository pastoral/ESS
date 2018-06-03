package com.ganonalabs.munir.electrtech.data.model.JobRequests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyJobRequestList {

    @SerializedName("data")
    @Expose
    private List<MyJobRequest> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MyJobRequestList() {
    }

    /**
     *
     * @param data
     */
    public MyJobRequestList(List<MyJobRequest> data) {
        super();
        this.data = data;
    }

    public List<MyJobRequest> getData() {
        return data;
    }

    public void setData(List<MyJobRequest> data) {
        this.data = data;
    }
}
