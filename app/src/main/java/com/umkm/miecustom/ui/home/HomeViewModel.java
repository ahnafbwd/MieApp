package com.umkm.miecustom.ui.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.umkm.miecustom.model.ProductData;
import com.umkm.miecustom.model.service.ApiClient;
import com.umkm.miecustom.model.service.GetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<ProductData>> dataList;

    public HomeViewModel(){
        dataList = new MutableLiveData<>();
    }
    public MutableLiveData<List<ProductData>> getDataListObserver() {
        return dataList;
    }

    public void getAll() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<ProductData>> call = service.getAll();
        call.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                dataList.postValue(response.body());
            }
            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                dataList.postValue(null);
            }
        });
    }
    public void getModar() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<ProductData>> call = service.getModar();
        call.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                dataList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                dataList.postValue(null);
            }
        });
    };
    public void getCupu() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<ProductData>> call = service.getCupu();
        call.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                dataList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                dataList.postValue(null);
            }
        });
    }
    public void getBocil() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<ProductData>> call = service.getBocil();
        call.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                dataList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                dataList.postValue(null);
            }
        });
    }
    public void getSnack() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<ProductData>> call = service.getSnack();
        call.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                dataList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                dataList.postValue(null);
            }
        });
    }
    public void getMinuman() {
        GetService service = ApiClient.getRetrofitInstance().create(GetService.class);
        Call<List<ProductData>> call = service.getMinum();
        call.enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                dataList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                dataList.postValue(null);
            }
        });
    }


    
}