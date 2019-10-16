package com.challengemeli.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Results implements Serializable {

    @SerializedName("results")
    @Expose
    private List<Product> results;

    public List<Product> getProducts() {
        return results;
    }

    public Results(List<Product> results) {
        this.results = results;
    }
}
