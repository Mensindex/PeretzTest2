package com.example.peretztest2.mvp.listdish;

import com.example.peretztest2.Dish;
import com.example.peretztest2.mvp.global.MvpView;

import java.util.List;

public interface ListView extends MvpView {

    void showProgress();
    void hideProgress();
    void setDishesList(List<Dish> listDishes);
    void showMessage(String message);
}
