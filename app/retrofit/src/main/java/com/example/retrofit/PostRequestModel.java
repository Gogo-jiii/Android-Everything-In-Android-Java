package com.example.retrofit;

import androidx.annotation.NonNull;

public class PostRequestModel {

    private String name;
    private String city;

    public PostRequestModel(String name, String city) {
        this.name = name;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "PostRequestModel{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}