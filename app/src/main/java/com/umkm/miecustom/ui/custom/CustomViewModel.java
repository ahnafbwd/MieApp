package com.umkm.miecustom.ui.custom;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.umkm.miecustom.model.CustomMieData;
import com.umkm.miecustom.model.CustomToppingData;
import com.umkm.miecustom.model.service.ApiClient;
import com.umkm.miecustom.model.service.GetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomViewModel extends ViewModel {
    private MutableLiveData<List<CustomMieData>> dataList;
    private MutableLiveData<List<CustomToppingData>> topList;

    public CustomViewModel() {
        dataList = new MutableLiveData<>();
        topList = new MutableLiveData<>();
    }
    public MutableLiveData<List<CustomMieData>> getDataListObserver() {
        return dataList;

    }
    public MutableLiveData<List<CustomToppingData>> getTopListObserver() {
        return topList;

    }
    public void mieCall() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<CustomMieData>> call = service.getMie();
        call.enqueue(new Callback<List<CustomMieData>>() {
            @Override
            public void onResponse(Call<List<CustomMieData>> call, Response<List<CustomMieData>> response) {
                dataList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CustomMieData>> call, Throwable t) {
                dataList.postValue(null);
            }
        });
    }
    public void toppingCall() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<CustomToppingData>> call = service.getTopping();
        call.enqueue(new Callback<List<CustomToppingData>>() {
            @Override
            public void onResponse(Call<List<CustomToppingData>> call, Response<List<CustomToppingData>> response) {
                topList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<CustomToppingData>> call, Throwable t) {
                topList.postValue(null);
            }
        });
    }
}