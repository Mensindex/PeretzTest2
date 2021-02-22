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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Dish> dishes = new ArrayList<>();
    private static final String key = "47be9031474183ea92958d5e255d888e47bdad44afd5d7b7201d0eb572be5278";
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private static SharedPreferences sPref;
    private final String SAVED_ID = "SavedID";


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
        leftIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PreviousScreenActivity.class);
                startActivity(i);
            }
        });
        ImageView rightIcon = findViewById(R.id.icoSearch);
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent y = new Intent(MainActivity.this, SearchScreenActivity.class);
                startActivity(y);
            }
        });

        //Разделитель Item's от Камиля
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);

        loadId();
        if (loadId() == null) {
            getDishes("93");
        } else {
            getDishes(loadId());
        }

    }

    private String loadId() {
        sPref = getPreferences(MODE_PRIVATE);
        return sPref.getString(SAVED_ID, "");
    }

    private void saveId() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_ID, "93");
        ed.apply();
    }

    private void getDishes(String id) {
        MyApplication.getApiService()
                .getProducts(id, key)
                .enqueue(new Callback<List<Dish>>() {

                    @Override
                    public void onResponse(Call<List<Dish>> call, Response<List<Dish>> response) {
                        dishes = response.body();
                        recyclerView.setAdapter(new DishAdapter(dishes));

                    }

                    @Override
                    public void onFailure(Call<List<Dish>> call, Throwable t) {
                        Log.e("TAG", "" + t);
                    }
                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveId();
    }
}