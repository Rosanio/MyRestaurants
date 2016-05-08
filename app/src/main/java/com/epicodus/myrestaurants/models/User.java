package com.epicodus.myrestaurants.models;

/**
 * Created by Matt on 5/8/2016.
 */
public class User {
    private String name;
    private String email;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
