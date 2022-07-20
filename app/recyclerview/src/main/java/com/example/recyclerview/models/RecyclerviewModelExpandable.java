package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelExpandable {

    private final String itemName;
    private boolean shouldExpand;

    public RecyclerviewModelExpandable(String itemName, boolean isExpanded) {
        this.itemName = itemName;
        this.shouldExpand = isExpanded;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isShouldExpand() {
        return shouldExpand;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelExpandable{" +
                "itemName='" + itemName + '\'' +
                ", shouldExpand=" + shouldExpand +
                '}';
    }
}