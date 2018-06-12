package com.ganonalabs.munir.electrtech.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobPostResponse {
    @SerializedName("0")
    @Expose
    private Integer _0;
    @SerializedName("ServiceId")
    @Expose
    private String serviceId;

    /**
     * No args constructor for use in serialization
     *
     */
    public JobPostResponse() {
    }

    /**
     *
     * @param serviceId
     * @param _0
     */
    public JobPostResponse(Integer _0, String serviceId) {
        super();
        this._0 = _0;
        this.serviceId = serviceId;
    }

    public Integer get0() {
        return _0;
    }

    public void set0(Integer _0) {
        this._0 = _0;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }
}
