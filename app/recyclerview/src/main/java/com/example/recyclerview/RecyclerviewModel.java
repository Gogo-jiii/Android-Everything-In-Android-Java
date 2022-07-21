package com.example.recyclerview;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class RecyclerviewModel {

    private final String itemName;

    public RecyclerviewModel(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

     static int getIndexOfItem(ArrayList<RecyclerviewModel> list, String value) {
        for(RecyclerviewModel model : list)  {
            if(model.getItemName().equals(value))
                return list.indexOf(model);
        }
        return -1;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModel{" +
                "itemName='" + itemName + '\'' +
                '}';
    }
}