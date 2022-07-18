package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelSimple {

    private final String itemName;

    public RecyclerviewModelSimple(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelSimple{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}