package com.example.tablayout.dynamic_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabLayoutDynamicViewPagerAdapter extends FragmentStateAdapter {

    private String[] data;

    public TabLayoutDynamicViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, String[] data) {
        super(fragmentActivity);
        this.data = data;
    }

    @NonNull @Override public Fragment createFragment(int position) {
        Fragment fragment = new SkeletonDynamicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", data[position]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override public int getItemCount() {
        return data.length;
    }
}