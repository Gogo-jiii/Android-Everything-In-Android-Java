package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelSwipeToDelete {

    private final String itemName;

    public RecyclerviewModelSwipeToDelete(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelSwipeToDelete{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}