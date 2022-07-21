package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelShimmerLayout {

    private final String itemName;

    public RecyclerviewModelShimmerLayout(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelShimmerLayout{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}