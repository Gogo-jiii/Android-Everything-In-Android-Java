package com.example.alertdialog;

import androidx.annotation.NonNull;

public class AlertDialogModel {

    private final String itemName;

    public AlertDialogModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "AlertDialogModel{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}