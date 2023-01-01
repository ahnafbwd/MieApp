package com.umkm.miecustom.model;

import com.google.gson.annotations.SerializedName;

public class CartData {
    @SerializedName("id_cart")
    private Integer id_cart;
    @SerializedName("id_user")
    private Integer id_user;
    @SerializedName("id_product")
    private Integer id_product;
    @SerializedName("title_product")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("note")
    private String note;
    @SerializedName("price")
    private String price;
    @SerializedName("image")
    private String imageProduk;
    @SerializedName("quantity")
    private String quantity;

    public CartData(Integer id_cart, Integer id_user, Integer id_product,String title, String description, String note, String price, String imageProduk, String quantity) {
        this.id_cart = id_cart;
        this.id_user = id_user;
        this.id_product = id_product;
        this.title = title;
        this.description = description;
        this.note = note;
        this.price = price;
        this.imageProduk = imageProduk;
        this.quantity = quantity;
    }

    public CartData() {

    }

    public Integer getId_cart() {
        return id_cart;
    }

    public void setId_cart(Integer id_cart) {
        this.id_cart = id_cart;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageProduk() {
        return imageProduk;
    }

    public void setImageProduk(String imageProduk) {
        this.imageProduk = imageProduk;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

