package com.example.dashboard;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DashboardModel {

    private final String itemName;

    public DashboardModel(String itemName) {
        this.itemName = itemName;
    }

    static int getIndexOfItem(ArrayList<DashboardModel> list, String value) {
        for(DashboardModel model : list)  {
            if(model.getItemName().equals(value))
                return list.indexOf(model);
        }
        return -1;
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