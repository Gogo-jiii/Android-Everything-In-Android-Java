package com.example.retrofit;

import androidx.annotation.NonNull;

public class PostResponseModel {

    private String name;
    private String city;
    private String id;
    private String createdAt;

    public PostResponseModel(String name, String city, String id, String createdAt) {
        this.name = name;
        this.city = city;
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @NonNull
    @Override public String toString() {
        return "PostResponseModel{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}