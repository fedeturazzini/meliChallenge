package com.challengemeli.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("price")
    @Expose
    private float price;

    @SerializedName("condition")
    @Expose
    private String condition;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("sold_quantity")
    @Expose
    private int soldQuantity;

    @SerializedName("available_quantity")
    @Expose
    private int avaliableQuantity;

    @SerializedName("accepts_mercadopago")
    @Expose
    private boolean acceptsMercadoPago;

    @SerializedName("address")
    @Expose
    private ProductAddress address;

    @SerializedName("shipping")
    @Expose
    private Shipping shipping;


    public Product(String id, String title, float price, String thumbnail, boolean acceptsMercadoPago) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.thumbnail = thumbnail;
        this.acceptsMercadoPago = acceptsMercadoPago;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return "$ " + Math.round(price);
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCondition() {
        return condition;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public int getAvaliableQuantity() {
        return avaliableQuantity;
    }

    public boolean isAcceptingMercadoPago() {
        return acceptsMercadoPago;
    }

    public ProductAddress getAddress() {
        return address;
    }

    public Shipping getShipping() {
        return shipping;
    }


}

