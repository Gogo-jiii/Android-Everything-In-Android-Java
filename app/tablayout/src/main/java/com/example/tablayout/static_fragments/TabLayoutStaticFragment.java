package com.example.tablayout.static_fragments;

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
import com.example.tablayout.databinding.FragmentTabLayoutStaticBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class TabLayoutStaticFragment extends Fragment {

    FragmentTabLayoutStaticBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    TabLayoutStaticViewPagerAdapter adapter;
    String[] tabTitle = {"Tab 1", "Tab 2", "Tab 3"};
    int[] icons = {R.drawable.ic_baseline_android_24, R.drawable.ic_baseline_alarm_24,
            R.drawable.ic_baseline_cloud_24};
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTabLayoutStaticBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupViewPager();
        return binding.getRoot();
    }

    private void setupViewPager() {
        fragmentArrayList.add(new TabLayoutStaticFragmentOne());
        fragmentArrayList.add(new TabLayoutStaticFragmentTwo());
        fragmentArrayList.add(new TabLayoutStaticFragmentThree());

        adapter = new TabLayoutStaticViewPagerAdapter(requireActivity(), fragmentArrayList);
        binding.viewPager4.setAdapter(adapter);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager4,
                (tab, position) -> {
                    tab.setText(tabTitle[position]);
                    tab.setIcon(icons[position]);
                }).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}