package com.example.edittext;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.edittext.databinding.ActivityEditTextBinding;

import java.util.Objects;

public class EditTextActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityEditTextBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        setupNaviagtion();
    }

    private void setupNaviagtion() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().
                        findFragmentById(R.id.fragmentContainerView);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
    }

    @Override public boolean onSupportNavigateUp() {
        if (navController != null) {
            navController.navigateUp();
        }
        return super.onSupportNavigateUp();
    }
}