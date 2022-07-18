package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelGridLayout {

    private final String itemName;

    public RecyclerviewModelGridLayout(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelGridLayout{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}