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


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView imageView, imageViewPlus, imageViewMinus;
        final TextView nameView, descriptionView, priceView;
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
        }

        public void bind(Dish dish) {

            Glide.with(itemView.getContext())
                    .load(dish.getImage())
                    .into(imageView);


            nameView.setText(dish.getName());
            descriptionView.setText(dish.getDescription());
            priceView.setText(String.valueOf(dish.getPrice()));

            if (dish.isBadgeNew()) {
                imageViewNew.setVisibility(View.VISIBLE);
            }

        }

    }


}