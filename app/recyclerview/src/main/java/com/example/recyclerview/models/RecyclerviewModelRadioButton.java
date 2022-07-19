package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelRadioButton {

    private final String itemName;
    private boolean isSelected;

    public RecyclerviewModelRadioButton(String itemName, boolean isSelected) {
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
        return "RecyclerviewModelRadioButton{" +
                "itemName='" + itemName + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}