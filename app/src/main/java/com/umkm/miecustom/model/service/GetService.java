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
    @GET("/getdataproduk/produkAll.php")
    Call<List<ProductData>> getAll();
    @GET("/getdataproduk/produkModar.php")
    Call<List<ProductData>> getModar();
    @GET("/getdataproduk/produkCupu.php")
    Call<List<ProductData>> getCupu();
    @GET("/getdataproduk/produkBocil.php")
    Call<List<ProductData>> getBocil();
    @GET("/getdataproduk/produkSnack.php")
    Call<List<ProductData>> getSnack();
    @GET("/getdataproduk/produkMinum.php")
    Call<List<ProductData>> getMinum();
    @GET("/getdataproduk/jenisMie.php")
    Call<List<CustomMieData>> getMie();
    @GET("/getdataproduk/topping.php")
    Call<List<CustomToppingData>> getTopping();
    @GET("/cart/cart.php")
    Call<List<CartData>> getcart();
    @GET("/cart/getdatacustom.php")
    Call<List<CartData>> getcartcustom();
    @GET("/transaction/tampiltransaksi.php")
    Call<List<TransactionData>> getTransaction();
}
