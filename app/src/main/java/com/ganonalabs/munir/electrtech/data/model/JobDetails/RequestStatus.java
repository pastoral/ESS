package com.ganonalabs.munir.electrtech.data.model.JobDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestStatus {

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public RequestStatus() {
    }

    /**
     *
     * @param data
     */
    public RequestStatus(List<Datum_> data) {
        super();
        this.data = data;
    }

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

}
