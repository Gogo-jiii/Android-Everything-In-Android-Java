package com.example.recyclerview.models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class RecyclerviewModelNestedParent {

    private String parent;
    private ArrayList<RecyclerviewModelNestedChild> arrayListChild;
    private boolean isChildVisible;

    public RecyclerviewModelNestedParent(String parent, ArrayList<RecyclerviewModelNestedChild> arrayListChild, boolean isChildVisible) {
        this.parent = parent;
        this.arrayListChild = arrayListChild;
        this.isChildVisible = isChildVisible;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public ArrayList<RecyclerviewModelNestedChild> getArrayListChild() {
        return arrayListChild;
    }

    public void setArrayListChild(ArrayList<RecyclerviewModelNestedChild> arrayListChild) {
        this.arrayListChild = arrayListChild;
    }

    public boolean isChildVisible() {
        return isChildVisible;
    }

    public void setChildVisible(boolean childVisible) {
        isChildVisible = childVisible;
    }

    @NonNull
    @Override
    public String toString() {
        return "{" +
                "parent='" + parent + '\'' +
                ", arrayListChild=" + arrayListChild +
                '}';
    }
}