package com.example.button;

import androidx.annotation.NonNull;

public class ButtonModel {

    private final String itemName;

    public ButtonModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "ButtonModel{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}