package com.ganonalabs.munir.electrtech.data.model;

/**
 * Created by munirul.hoque on 07/05/2018.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Brands_Data {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;

    /**
     * No args constructor for use in serialization
     *
     */
    public Brands_Data() {
    }

    /**
     *
     * @param id
     * @param name
     */
    public Brands_Data(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
