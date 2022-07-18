package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelDragDropItem {

    private final String itemName;

    public RecyclerviewModelDragDropItem(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelDragDropItem{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}