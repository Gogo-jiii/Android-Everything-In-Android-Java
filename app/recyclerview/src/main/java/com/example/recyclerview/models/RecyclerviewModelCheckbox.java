package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelCheckbox {

    private final String itemName;
    private boolean isSelected;

    public RecyclerviewModelCheckbox(String itemName, boolean isSelected) {
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
        return "RecyclerviewModelCheckbox{" +
                "itemName='" + itemName + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}