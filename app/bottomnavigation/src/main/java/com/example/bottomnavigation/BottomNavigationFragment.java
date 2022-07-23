package com.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.bottomnavigation.databinding.FragmentBottomNavigationBinding;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigationFragment extends Fragment {

    FragmentBottomNavigationBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomNavigationBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        showFirstFragment();

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            int itemID = item.getItemId();
            Fragment fragment = null;
            item.setChecked(true);

            if (itemID == R.id.page_1) {
                fragment = new BottomNavigationFragment1();
            } else if (itemID == R.id.page_2) {
                fragment = new BottomNavigationFragment2();
            }

            if (fragment != null) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.replace(R.id.container, fragment);
                ft.commit();
            }
            return false;
        });
        return binding.getRoot();
    }

    private void showFirstFragment() {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.container, new BottomNavigationFragment1());
        ft.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}