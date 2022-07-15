package com.example.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.databinding.ActivitySecondBinding;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.toolbar2.setTitle("Second Activity");
    }
}