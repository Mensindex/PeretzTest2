package com.example.peretztest2.mvp.listdish;


import android.util.Log;
import android.widget.Toast;

import com.example.peretztest2.Dish;
import com.example.peretztest2.MyApplication;
import com.example.peretztest2.mvp.global.MvpPresenter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter extends MvpPresenter<ListView> {

    public void getListDishes(){
        getView().showProgress();
        MyApplication.getApiService()
                .getProducts()
                .enqueue(new Callback<List<Dish>>() {
                    @Override
                    public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {

                        getView().hideProgress();
                        getView().setDishesList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Dish>> call, Throwable t) {

                        getView().hideProgress();
                        Log.d("AHSCDSAC", t.getMessage());
                    }
                });
    }

}
