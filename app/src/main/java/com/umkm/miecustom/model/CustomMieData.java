package com.umkm.miecustom.model;

import com.google.gson.annotations.SerializedName;

public class CustomMieData {
    @SerializedName("id_product")
    private Integer id_mie;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String shortdesc;
    @SerializedName("price")
    private String price;
    @SerializedName("image")
    private String imageProduk;
    private boolean isSelected;

    public CustomMieData(Integer id_mie, String title, String shortdesc, String price, String imageProduk) {
        this.id_mie = id_mie;
        this.title = title;
        this.shortdesc = shortdesc;
        this.price = price;
        this.imageProduk = imageProduk;
    }

    public Integer getId_mie() {
        return id_mie;
    }

    public void setId_mie(Integer id_mie) {
        this.id_mie = id_mie;
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

    public void setImageProduk(String imageProduk) {
        this.imageProduk = imageProduk;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

