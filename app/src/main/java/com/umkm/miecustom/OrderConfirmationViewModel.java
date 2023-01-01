package com.umkm.miecustom;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderConfirmationViewModel extends ViewModel {
    private MutableLiveData<List<CartData>> dataList;

    public OrderConfirmationViewModel(){
        dataList = new MutableLiveData<>();
    }
    public MutableLiveData<List<CartData>> getDataListObserver() {
        return dataList;

    }


    public void getallorder(String id_user, Context contextt) {
        RequestQueue queue = Volley.newRequestQueue(contextt);
        String url ="https://miesuhh.000webhostapp.com/mobileapps/cart/cart.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
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

}