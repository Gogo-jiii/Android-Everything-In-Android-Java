package com.example.navigationdrawer;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.navigationdrawer.databinding.ActivityNavigationDrawerBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityNavigationDrawerBinding binding;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //Objects.requireNonNull(getSupportActionBar()).hide();

        showFirstFragment();
        setupNavigationDrawer();
    }

    private void showFirstFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.container, new NavigationDrawerFragmentOne());
        ft.commit();
    }

    private void setupNavigationDrawer() {
        //setSupportActionBar(binding.toolbar100);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar100,
                R.string.drawer_open, R.string.drawer_close);
        binding.drawerLayout.addDrawerListener(toggle);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        binding.navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState,
                             @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        int itemId = item.getItemId();
        if (itemId == R.id.nav_item_one) {
            Toast.makeText(this, "one", Toast.LENGTH_SHORT).show();
            binding.toolbar100.setTitle("Fragment 1");
            fragment = new NavigationDrawerFragmentOne();
        } else if (itemId == R.id.nav_item_two) {
            Toast.makeText(this, "two", Toast.LENGTH_SHORT).show();
            binding.toolbar100.setTitle("Fragment 2");
            fragment = new NavigationDrawerFragmentTwo();
        } else if (itemId == R.id.nav_item_three) {
            Toast.makeText(this, "three", Toast.LENGTH_SHORT).show();
            binding.toolbar100.setTitle("Fragment 3");
            fragment = new NavigationDrawerFragmentThree();
        } else if (itemId == R.id.nav_item_four) {
            Toast.makeText(this, "four", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_item_five) {
            Toast.makeText(this, "five", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_item_six) {
            Toast.makeText(this, "six", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_item_seven) {
            Toast.makeText(this, "seven", Toast.LENGTH_SHORT).show();
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.container, fragment);
            ft.commit();
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}