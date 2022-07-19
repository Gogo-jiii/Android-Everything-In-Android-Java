package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelSwipeToRefresh {

    private final String itemName;

    public RecyclerviewModelSwipeToRefresh(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelSwipeToRefresh{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}