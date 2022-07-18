package com.example.recyclerview;

import androidx.annotation.NonNull;

public class RecyclerviewModel {

    private final String itemName;

    public RecyclerviewModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModel{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}