package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelPagination {

    private final String itemName;

    public RecyclerviewModelPagination(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelPagination{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}