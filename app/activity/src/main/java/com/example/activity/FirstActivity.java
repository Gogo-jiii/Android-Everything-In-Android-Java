package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.activity.databinding.ActivityFirstBinding;

import java.util.Objects;

public class FirstActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 100;
    ActivityFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding.toolbar.setTitle("First Activity");

        binding.btnGotoSecondActivity.setOnClickListener(view -> {
            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        binding.btnGotoThirdActivityWithData.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", "IT wala");
            Intent intent = new Intent(FirstActivity.this, ThirdActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        });

        binding.btnOnActivityResult.setOnClickListener(view -> {
            Intent intent = new Intent(FirstActivity.this, FourthActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        binding.btnActivityLauncher.setOnClickListener(view -> {
            Intent intent1 = new Intent(FirstActivity.this, FifthActivity.class);
            activityResultLauncher.launch(intent1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getExtras();
            String name = bundle.getString("name");
            Toast.makeText(this, "Data: " + name, Toast.LENGTH_SHORT).show();
        }
    }

    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent intent = result.getData();
                            Toast.makeText(FirstActivity.this, "Data: " + Objects.requireNonNull(intent).getStringExtra("name"), Toast.LENGTH_SHORT).show();
                        }
                    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}