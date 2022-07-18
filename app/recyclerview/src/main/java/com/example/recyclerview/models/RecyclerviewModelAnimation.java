package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelAnimation {

    private final String itemName;

    public RecyclerviewModelAnimation(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelAnimation{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}
