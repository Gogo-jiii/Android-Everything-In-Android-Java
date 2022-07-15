package com.example.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.databinding.ActivityFourthBinding;

import java.util.Objects;

public class FourthActivity extends AppCompatActivity {

    ActivityFourthBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFourthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.toolbar4.setTitle("Fourth Activity");

        binding.btnGoBack.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("name", "IT wala");
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}