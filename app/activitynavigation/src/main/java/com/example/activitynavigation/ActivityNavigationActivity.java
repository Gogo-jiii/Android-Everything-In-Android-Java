package com.example.activitynavigation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Objects;

public class ActivityNavigationActivity extends AppCompatActivity {

    Button btnGotoSecondActivity;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Objects.requireNonNull(getSupportActionBar()).hide();

        btnGotoSecondActivity = findViewById(R.id.btnGotoSecondActivity);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Activity Navigation");

        btnGotoSecondActivity.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityNavigationActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}