package com.epicodus.myrestaurants.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.myrestaurants.Constants;
import com.firebase.client.Firebase;

/**
 * Created by Guest on 5/12/16.
 */
public class BaseActivity extends AppCompatActivity {
    public Firebase mFirebaseRef;
    public SharedPreferences mSharedPreferences;
    public SharedPreferences.Editor mEditor;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
    }
}
