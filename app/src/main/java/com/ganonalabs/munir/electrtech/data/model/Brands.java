package com.ganonalabs.munir.electrtech.data.model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by munirul.hoque on 07/05/2018.
 */

public class Brands {
    @SerializedName("data")
    @Expose
    private List<Brands_Data> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Brands() {
    }

    /**
     *
     * @param data
     */
    public Brands(List<Brands_Data> data) {
        super();
        this.data = data;
    }

    public List<Brands_Data> getData() {
        return data;
    }

    public void setData(List<Brands_Data> data) {
        this.data = data;
    }
}
