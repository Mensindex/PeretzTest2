package com.example.peretztest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.peretztest2.mvp.global.MvpView;
import com.example.peretztest2.mvp.listdish.ListPresenter;
import com.example.peretztest2.mvp.listdish.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListView {

    private List<Dish> dishes = new ArrayList<>();
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private static SharedPreferences sPref;
    private final String SAVED_ID = "SavedID";

    private ListPresenter presenter = new ListPresenter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        //Установил свой тулбар
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);

        //Создал кнопки Назад и Поиск и накинул на них переходы
        ImageView leftIcon = findViewById(R.id.icoBackArrow);
        leftIcon.setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, PreviousScreenActivity.class);
            startActivity(i);
        });
        ImageView rightIcon = findViewById(R.id.icoSearch);
        rightIcon.setOnClickListener(v -> {
            Intent y = new Intent(MainActivity.this, SearchScreenActivity.class);
            startActivity(y);
        });

        //Разделитель Item's от Камиля
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);

        presenter.getListDishes();

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setDishesList(List listDishes) {
        recyclerView.setAdapter(new DishAdapter(listDishes, MainActivity.this));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}