package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelSingleItemSelection {

    private final String itemName;

    public RecyclerviewModelSingleItemSelection(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelSingleItemSelection{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}