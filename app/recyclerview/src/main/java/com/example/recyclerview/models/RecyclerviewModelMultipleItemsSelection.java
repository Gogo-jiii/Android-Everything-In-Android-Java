package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelMultipleItemsSelection {

    private final String itemName;
    private boolean isSelected;

    public RecyclerviewModelMultipleItemsSelection(String itemName, boolean isSelected) {
        this.itemName = itemName;
        this.isSelected = isSelected;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelMultipleItemsSelection{" +
                "itemName='" + itemName + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}