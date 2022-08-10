package com.example.gson;

import androidx.annotation.NonNull;

public class UserModel {

    private String name;

    public UserModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                '}';
    }
}