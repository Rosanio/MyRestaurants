package com.epicodus.myrestaurants.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.models.Restaurant;
import com.epicodus.myrestaurants.ui.RestaurantDetailActivity;
import com.epicodus.myrestaurants.util.OnRestaurantSelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Matt on 4/24/2016.
 */
public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private ArrayList<Restaurant> mRestaurants = new ArrayList<>();
    public RestaurantViewHolder viewHolder;
    private OnRestaurantSelectedListener mOnRestaurantSelectedListener;

    public RestaurantListAdapter(ArrayList<Restaurant> restaurants, OnRestaurantSelectedListener restaurantSelectedListener) {
        mRestaurants = restaurants;
        mOnRestaurantSelectedListener = restaurantSelectedListener;
    }

        //I'm pretty sure this is just some magic that sets up our view holder, which we need to display the right contents to our list item
    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item, parent, false);
        viewHolder = new RestaurantViewHolder(view, mRestaurants, mOnRestaurantSelectedListener);
        return viewHolder;
    }
    //this is calling the function which fills in our views with text
    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(mRestaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return mRestaurants.size();
    }
}
