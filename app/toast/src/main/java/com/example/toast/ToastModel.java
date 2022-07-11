package com.example.toast;

import androidx.annotation.NonNull;

public class ToastModel {

    private final String itemName;

    public ToastModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    @NonNull
    @Override
    public String toString() {
        return "DashboardModel{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}