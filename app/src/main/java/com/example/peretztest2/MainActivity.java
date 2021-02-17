package com.example.peretztest2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Dish> dishes = new ArrayList<>();
    private static final String key = "47be9031474183ea92958d5e255d888e47bdad44afd5d7b7201d0eb572be5278";
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.list);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recyclerView.addItemDecoration(itemDecoration);

        getDishes("93");

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
}