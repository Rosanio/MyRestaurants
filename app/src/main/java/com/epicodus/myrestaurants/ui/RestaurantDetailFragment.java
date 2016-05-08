package com.epicodus.myrestaurants.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.myrestaurants.Constants;
import com.epicodus.myrestaurants.R;
import com.epicodus.myrestaurants.models.Restaurant;
import com.firebase.client.Firebase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.restaurantImageView) ImageView mImageLabel;
    @Bind(R.id.restaurantNameTextView) TextView mNameLabel;
    @Bind(R.id.cuisineTextView) TextView mCategoriesLabel;
    @Bind(R.id.ratingTextView) TextView mRatingLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

    private Restaurant mRestaurant;

    private SharedPreferences mSharedPreferences;

    public static RestaurantDetailFragment newInstance(Restaurant restaurant) {
        RestaurantDetailFragment restaurantDetailFragment = new RestaurantDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", Parcels.wrap(restaurant));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRestaurant = Parcels.unwrap(getArguments().getParcelable("restaurant"));
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext()).load(mRestaurant.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mImageLabel);
        mNameLabel.setText(mRestaurant.getName());
        mCategoriesLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getCategories()));
        mRatingLabel.setText(Double.toString(mRestaurant.getRating()) + "/5");
        mPhoneLabel.setText(mRestaurant.getPhone());
        mAddressLabel.setText(android.text.TextUtils.join(", ", mRestaurant.getAddress()));
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mSaveRestaurantButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.websiteTextView:
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRestaurant.getWebsite()));
                startActivity(webIntent);
                break;
            case R.id.phoneTextView:
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + mRestaurant.getPhone()));
                startActivity(phoneIntent);
                break;
            case R.id.addressTextView:
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"+mRestaurant.getLatitude()+","+mRestaurant.getLongitude()+"?q=("+mRestaurant.getName()+")"));
                startActivity(mapIntent);
                break;
            case R.id.saveRestaurantButton:
                String userUid = mSharedPreferences.getString(Constants.KEY_UID, null);
                Firebase userRestaurantsFirebaseRef = new Firebase(Constants.FIREBASE_URL_RESTAURANTS).child(userUid);
                Firebase pushRef = userRestaurantsFirebaseRef.push();
                String restaurantPushId = pushRef.getKey();
                mRestaurant.setPushId(restaurantPushId);
                pushRef.setValue(mRestaurant);
                Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
