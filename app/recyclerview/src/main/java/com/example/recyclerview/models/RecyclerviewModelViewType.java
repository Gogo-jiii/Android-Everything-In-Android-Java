package com.example.recyclerview.models;

import androidx.annotation.NonNull;

public class RecyclerviewModelViewType {

    public static final int TYPE_TEXT = 1;
    public static final int TYPE_IMAGE = 2;

    private int type;
    private String text;
    private int image;

    public RecyclerviewModelViewType(int type, String text, int image) {
        this.type = type;
        this.text = text;
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @NonNull
    @Override
    public String toString() {
        return "RecyclerviewModelViewType{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", image=" + image +
                '}';
    }
}