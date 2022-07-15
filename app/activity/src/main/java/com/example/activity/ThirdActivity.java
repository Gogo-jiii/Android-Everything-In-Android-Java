package com.example.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.databinding.ActivityThirdBinding;

import java.util.Objects;

public class ThirdActivity extends AppCompatActivity {

    ActivityThirdBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThirdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.toolbar3.setTitle("Third Activity");

        getData();
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("name");
            Toast.makeText(this, "Data: " + name, Toast.LENGTH_SHORT).show();
        }
    }
}