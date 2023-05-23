package com.umkm.miecustom.ui.cart;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.umkm.miecustom.model.CartData;
import com.umkm.miecustom.model.service.ApiClient;
import com.umkm.miecustom.model.service.GetService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class CartViewModel extends ViewModel {
    private MutableLiveData<List<CartData>> dataList;
    private MutableLiveData<List<CartData>> dataList2;

    public CartViewModel(){
        dataList = new MutableLiveData<>();
        dataList2 = new MutableLiveData<>();
    }

    public MutableLiveData<List<CartData>> getDataListObserver() {
        return dataList;
    }

    public MutableLiveData<List<CartData>> getDataList2Observer() {
        return dataList2;
    }

    public void getnotcustom(String id_user,Context contextt) {
        RequestQueue queue = Volley.newRequestQueue(contextt);
        String url ="https://miesuhh.000webhostapp.com/mobileapps/cart/getdatanotcustom.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            List<CartData> list = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                CartData data = new CartData(
                                        object.getInt("id_cart"),
                                        object.getInt("id_user"),
                                        object.getInt("id_product"),
                                        object.getString("title_product"),
                                        object.getString("description"),
                                        object.getString("note"),
                                        object.getString("price"),
                                        object.getString("image"),
                                        object.getString("quantity")
                                );
                                list.add(data);
                            }
                            dataList.postValue(list);
                        }catch (JSONException e){
                            e.printStackTrace();
                            dataList.postValue(null);
                        }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contextt, error.getLocalizedMessage(),Toast.LENGTH_LONG);
                dataList.postValue(null);
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id_user", id_user);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
    public void getcustom(String id_user,Context contextt) {
        RequestQueue queue = Volley.newRequestQueue(contextt);
        String url ="https://miesuhh.000webhostapp.com/mobileapps/cart/getdatacustom.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            List<CartData> list = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                CartData data = new CartData(
                                        object.getInt("id_cart"),
                                        object.getInt("id_user"),
                                        object.getInt("id_product"),
                                        object.getString("title_product"),
                                        object.getString("description"),
                                        object.getString("note"),
                                        object.getString("price"),
                                        object.getString("image"),
                                        object.getString("quantity")
                                );
                                list.add(data);
                            }
                            dataList2.postValue(list);
                        }catch (JSONException e){
                            e.printStackTrace();
                            dataList2.postValue(null);
                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(contextt, error.getLocalizedMessage(),Toast.LENGTH_LONG);
                dataList2.postValue(null);
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("id_user", id_user);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }

}