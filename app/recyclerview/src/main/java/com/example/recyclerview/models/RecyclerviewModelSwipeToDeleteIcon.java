package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelSwipeToDeleteIcon {

    private final String itemName;

    public RecyclerviewModelSwipeToDeleteIcon(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelSwipeToDeleteIcon{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}