package com.example.peretztest2;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    private final List<Dish> dishes;
    private Context contex;
    private static SharedPreferences sPref;
    private int countProduct = 0;

    public DishAdapter(List<Dish> dishes, Context context) {
        this.dishes = dishes;
        this.contex = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dishes.get(position));
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView, imageViewPlus, imageViewMinus;
        final TextView nameView, descriptionView, priceView, textViewNumber;
        ImageView imageViewNew;

        ViewHolder(View view) {
            super(view);
            imageViewNew = view.findViewById(R.id.imageViewNew);
            imageViewPlus = view.findViewById(R.id.imageViewPlus);
            imageViewMinus = view.findViewById(R.id.imageViewMinus);
            imageView = (ImageView) view.findViewById(R.id.imageViewDish);
            nameView = (TextView) view.findViewById(R.id.textViewName);
            descriptionView = (TextView) view.findViewById(R.id.textViewDescription);
            priceView = (TextView) view.findViewById(R.id.textViewPrice);
            textViewNumber = (TextView) view.findViewById(R.id.textViewNumber);
        }

        public void bind(Dish dish) {

            textViewNumber.setText(loadId(dish.getId()));

            Glide.with(itemView.getContext())
                    .load(dish.getImage())
                    .into(imageView);


            nameView.setText(dish.getName());
            descriptionView.setText(dish.getDescription());
            priceView.setText(String.valueOf(dish.getPrice()));


            if (dish.isBadgeNew()) {
                imageViewNew.setVisibility(View.VISIBLE);
            }

            imageViewPlus.setOnClickListener(v -> {
                countProduct++;
                saveId(dish.getId(), countProduct);
                textViewNumber.setText(String.valueOf(countProduct));
            });

            imageViewMinus.setOnClickListener(v -> {
                countProduct--;
                saveId(dish.getId(), countProduct);
                textViewNumber.setText(String.valueOf(countProduct));
            });

        }

        private String loadId(int id) {
            sPref = contex.getSharedPreferences("ids", MODE_PRIVATE);
            return sPref.getString(String.valueOf(id), "");
        }

        void saveId(int id, int count) {
            sPref = contex.getSharedPreferences("ids", MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(String.valueOf(id), String.valueOf(count));
            ed.apply();
        }

    }


}