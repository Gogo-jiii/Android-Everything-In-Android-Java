package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelHorizontalLayout {

    private final String itemName;

    public RecyclerviewModelHorizontalLayout(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelHorizontalLayout{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}