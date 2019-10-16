package com.challengemeli.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductAddress implements Serializable {

    @SerializedName("state_name")
    @Expose
    private String stateName;

    @SerializedName("city_name")
    @Expose
    private String cityName;

    public String getStateName() {
        return stateName;
    }

    public String getCityName() {
        return cityName;
    }
}
