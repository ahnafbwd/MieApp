package com.umkm.miecustom.model;

import com.google.gson.annotations.SerializedName;

public class TransactionData {
    @SerializedName("id_order")
    private String id_transaction;
    @SerializedName("id_user")
    private String id_user;
    @SerializedName("title")
    private String title;
    @SerializedName("quantity")
    private String quantity;
    @SerializedName("price")
    private String price;
    @SerializedName("status")
    private String status;
    @SerializedName("remark")
    private String remark;
    @SerializedName("date")
    private String date;

    public TransactionData(String id_transaction, String id_user, String title, String quantity, String price, String status, String date) {
        this.id_transaction = id_transaction;
        this.id_user = id_user;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.remark = remark;
        this.date = date;
    }

    public String getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(String id_transaction) {
        this.id_transaction = id_transaction;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {return remark;}

    public void setRemark(String remark) {this.remark = remark;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
