package com.umkm.miecustom.model.service;

import com.umkm.miecustom.model.CartData;
import com.umkm.miecustom.model.CustomMieData;
import com.umkm.miecustom.model.CustomToppingData;
import com.umkm.miecustom.model.ProductData;
import com.umkm.miecustom.model.TransactionData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    ////////////////////////////////
    //revisi
    //////////////////////
    //dari dishes
    @GET("/mobileapps/getdataproduk/produkAll.php")
    Call<List<ProductData>> getAll();
    @GET("/mobileapps/getdataproduk/produkModar.php")
    Call<List<ProductData>> getModar();
    @GET("/mobileapps/getdataproduk/produkCupu.php")
    Call<List<ProductData>> getCupu();
    @GET("/mobileapps/getdataproduk/produkBocil.php")
    Call<List<ProductData>> getBocil();
    @GET("/mobileapps/getdataproduk/produkSnack.php")
    Call<List<ProductData>> getSnack();
    @GET("/mobileapps/getdataproduk/produkMinum.php")
    Call<List<ProductData>> getMinum();
    ///////////////////////////////////////////
    //untuk fitur custom
    @GET("/mobileapps/getdataproduk/jenisMie.php")
    Call<List<CustomMieData>> getMie();
    @GET("/mobileapps/getdataproduk/topping.php")
    Call<List<CustomToppingData>> getTopping();
    ////////////////////////////////////////
    // untuk cart
    @GET("/mobileapps/cart/cart.php")
    Call<List<CartData>> getcart();
    @GET("/mobileapps/cart/getdatacustom.php")
    Call<List<CartData>> getcartcustom();
    // untuk transaksi
    @GET("/mobileapps/transaction/tampiltransaksi.php")
    Call<List<TransactionData>> getTransaction();
}
