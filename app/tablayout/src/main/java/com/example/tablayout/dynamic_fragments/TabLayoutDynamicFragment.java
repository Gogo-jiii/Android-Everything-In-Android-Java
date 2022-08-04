package com.example.tablayout.dynamic_fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.tablayout.R;
import com.example.tablayout.databinding.FragmentTabLayoutDynamicBinding;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabLayoutDynamicFragment extends Fragment {

    FragmentTabLayoutDynamicBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    String[] data = {"A", "B", "C"};
    String[] tabTitle = {"Tab 1", "Tab 2", "Tab 3"};
    int[] icons = {R.drawable.ic_baseline_android_24, R.drawable.ic_baseline_alarm_24,
            R.drawable.ic_baseline_cloud_24};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTabLayoutDynamicBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupViewPager();
        return binding.getRoot();
    }

    private void setupViewPager() {
        TabLayoutDynamicViewPagerAdapter adapter = new TabLayoutDynamicViewPagerAdapter(requireActivity(), data);
        binding.viewPager6.setAdapter(adapter);
        new TabLayoutMediator(binding.tabLayout2, binding.viewPager6,
                (tab, position) -> {
                    tab.setText(tabTitle[position]);
                    tab.setIcon(icons[position]);
                }).attach();
    }
}