package com.example.fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fragment.databinding.ActivityFragmentsBinding;

import java.util.Objects;

public class FragmentsActivity extends AppCompatActivity {

    ActivityFragmentsBinding binding;
    FragmentHandler fragmentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        fragmentHandler = FragmentHandler.getInstance();

        loadFirstFragment();
    }

    private void loadFirstFragment() {
        fragmentHandler.addFragmentToActivity(new FragmentOne(), getSupportFragmentManager(), null, R.id.container);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Log.d("TAG", "If");
            super.onBackPressed();
        } else {
            Log.d("TAG", "else");
            fragmentHandler.onFragmentBackPress(getSupportFragmentManager());
        }
    }
}