package com.ganonalabs.munir.electrtech.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobPostResponse {
    @SerializedName("0")
    @Expose
    private Integer _0;
    @SerializedName("OrderId")
    @Expose
    private String orderId;

    /**
     * No args constructor for use in serialization
     *
     */
    public JobPostResponse() {
    }

    /**
     *
     * @param _0
     * @param orderId
     */
    public JobPostResponse(Integer _0, String orderId) {
        super();
        this._0 = _0;
        this.orderId = orderId;
    }

    public Integer get0() {
        return _0;
    }

    public void set0(Integer _0) {
        this._0 = _0;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
