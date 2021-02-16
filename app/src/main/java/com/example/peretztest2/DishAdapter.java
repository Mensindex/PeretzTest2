package com.example.peretztest2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {
    private final List<Dish> dishes;

    public DishAdapter(List<Dish> dishes) {
        this.dishes = dishes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false)
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish dish = dishes.get(position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView;
        final TextView nameView, descriptionView, priceView;

        ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageViewDish);
            nameView = (TextView) view.findViewById(R.id.textViewName);
            descriptionView = (TextView) view.findViewById(R.id.textViewDescription);
            priceView = (TextView) view.findViewById(R.id.textViewPrice);
        }

        public void bind(Dish dish) {
            nameView.setText(dish.getName());
            descriptionView.setText(dish.getDescription());
            priceView.setText(dish.getPrice());
            Glide
                    .with(itemView.getContext())
                    .load(dish.getImage())
                    .into(imageView);

    }


}