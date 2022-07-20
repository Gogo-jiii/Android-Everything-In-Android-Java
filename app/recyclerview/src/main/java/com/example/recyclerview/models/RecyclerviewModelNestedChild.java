package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelNestedChild {

    private  String child;

    public RecyclerviewModelNestedChild(String child) {
        this.child = child;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "child='" + child + '\'' +
                '}';
    }
}