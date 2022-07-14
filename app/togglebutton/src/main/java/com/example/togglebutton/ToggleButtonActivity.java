package com.example.togglebutton;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.togglebutton.databinding.ActivityToggleButtonBinding;

import java.util.Objects;

public class ToggleButtonActivity extends AppCompatActivity {

    private NavController navController;
    private ActivityToggleButtonBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToggleButtonBinding.inflate(getLayoutInflater());
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