package com.umkm.miecustom.model;

import com.google.gson.annotations.SerializedName;

public class CustomToppingData {
    @SerializedName("id_product")
    private Integer id_topping;
    @SerializedName("title")
    private String title;
    @SerializedName("price")
    private String price;

    public CustomToppingData(Integer id_topping, String title, String price) {
        this.id_topping = id_topping;
        this.title = title;
        this.price = price;
    }

    public Integer getId_topping() {
        return id_topping;
    }

    public void setId_topping(Integer id_topping) {
        this.id_topping = id_topping;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

