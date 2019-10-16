package com.challengemeli.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shipping implements Serializable {

    @SerializedName("free_shipping")
    @Expose
    private boolean freeShipping;

    public boolean isFreeShipping() {
        return freeShipping;
    }
}
