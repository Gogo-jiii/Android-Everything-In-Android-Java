package com.example.sharedpreference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.Set;

public class SharedPreferenceManager {

    @SuppressLint("StaticFieldLeak")
    private static SharedPreferenceManager instance;
    private Context context;
    private SharedPreferences sharedPreferences;

    private String prefString;
    private int prefInt;
    private float prefFloat;
    private long prefLong;
    private boolean prefBoolean;
    private Set<String> prefSet;


    public String getPrefString() {
        return prefString;
    }

    private void setPrefString(String prefString) {
        this.prefString = prefString;
    }

    public int getPrefInt() {
        return prefInt;
    }

    private void setPrefInt(int prefInt) {
        this.prefInt = prefInt;
    }

    public float getPrefFloat() {
        return prefFloat;
    }

    private void setPrefFloat(float prefFloat) {
        this.prefFloat = prefFloat;
    }

    public long getPrefLong() {
        return prefLong;
    }

    private void setPrefLong(long prefLong) {
        this.prefLong = prefLong;
    }

    public boolean isPrefBoolean() {
        return prefBoolean;
    }

    private void setPrefBoolean(boolean prefBoolean) {
        this.prefBoolean = prefBoolean;
    }

    public Set<String> getPrefSet() {
        return prefSet;
    }

    private void setPrefSet(Set<String> prefSet) {
        this.prefSet = prefSet;
    }

    @NonNull
    @Override
    public String toString() {
        return "SharedPref{" +
                "context=" + context +
                ", sharedPreferences=" + sharedPreferences +
                ", prefString='" + prefString + '\'' +
                ", prefInt=" + prefInt +
                ", prefFloat=" + prefFloat +
                ", prefLong=" + prefLong +
                ", prefBoolean=" + prefBoolean +
                ", prefSet=" + prefSet +
                '}';
    }

    public static final int DATA_TYPE_STRING = 1;
    public static final int DATA_TYPE_INT = 2;
    public static final int DATA_TYPE_FLOAT = 3;
    public static final int DATA_TYPE_LONG = 4;
    public static final int DATA_TYPE_BOOLEAN = 5;
    public static final int DATA_TYPE_SET = 6;

    private SharedPreferenceManager() {
    }

    public static SharedPreferenceManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferenceManager();
        }
        instance.init(context);
        return instance;
    }

    private void init(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("pref",
                Context.MODE_PRIVATE);
    }

    void write(String key, Object data, int dataType) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (dataType) {
            case DATA_TYPE_STRING:
                editor.putString(key, String.valueOf(data));
                editor.apply();
                break;
            case DATA_TYPE_INT:
                editor.putInt(key, (Integer) data);
                editor.apply();
                break;
            case DATA_TYPE_FLOAT:
                editor.putFloat(key, (Float) data);
                editor.apply();
                break;
            case DATA_TYPE_LONG:
                editor.putLong(key, (Long) data);
                editor.apply();
                break;
            case DATA_TYPE_BOOLEAN:
                editor.putBoolean(key, (Boolean) data);
                editor.apply();
                break;
            case DATA_TYPE_SET:
                editor.putStringSet(key, (Set<String>) data);
                editor.apply();
                break;
        }
    }

    SharedPreferenceManager read(String key, int dataType) {
        switch (dataType) {
            case DATA_TYPE_STRING:
                instance.setPrefString(sharedPreferences.getString(key, ""));
                return instance;
            case DATA_TYPE_INT:
                instance.setPrefInt(sharedPreferences.getInt(key, 0));
                return instance;
            case DATA_TYPE_FLOAT:
                instance.setPrefFloat(sharedPreferences.getFloat(key, 0));
                return instance;
            case DATA_TYPE_LONG:
                instance.setPrefLong(sharedPreferences.getLong(key, 0));
                return instance;
            case DATA_TYPE_BOOLEAN:
                instance.setPrefBoolean(sharedPreferences.getBoolean(key, false));
                return instance;
            case DATA_TYPE_SET:
                instance.setPrefSet(sharedPreferences.getStringSet(key, null));
                return instance;
        }
        return null;
    }
}