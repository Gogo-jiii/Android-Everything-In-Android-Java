package com.example.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.databinding.ActivityFifthBinding;

import java.util.Objects;

public class FifthActivity extends AppCompatActivity {

    ActivityFifthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFifthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.toolbar5.setTitle("Fifth Activity");

        binding.btnGoBack.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("name", "IT wala");
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}