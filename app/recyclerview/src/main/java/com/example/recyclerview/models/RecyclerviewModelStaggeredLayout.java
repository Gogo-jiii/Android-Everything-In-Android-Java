package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelStaggeredLayout {

    private final String itemName;

    public RecyclerviewModelStaggeredLayout(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelStaggeredLayout{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}