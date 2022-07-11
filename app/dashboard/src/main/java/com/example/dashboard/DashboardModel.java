package com.example.dashboard;

import androidx.annotation.NonNull;

public class DashboardModel {

    private final String itemName;

    public DashboardModel(String itemName) {
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