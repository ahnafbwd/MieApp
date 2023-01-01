package com.umkm.miecustom.model;

import com.google.gson.annotations.SerializedName;

public class ProductData {
    @SerializedName("id_product")
    private String id_product;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String shortdesc;
    @SerializedName("price")
    private String price;
    @SerializedName("image")
    private String imageProduk;

    public ProductData(String id_product, String title, String shortdesc, String price, String imageProduk) {
        this.id_product = id_product;
        this.title = title;
        this.shortdesc = shortdesc;
        this.price = price;
        this.imageProduk = imageProduk;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageProduk() {return imageProduk;}

}

