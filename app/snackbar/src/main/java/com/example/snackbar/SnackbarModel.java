package com.example.snackbar;

import androidx.annotation.NonNull;

public class SnackbarModel {

    private final String itemName;

    public SnackbarModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "SnackbarModel{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}