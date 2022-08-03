package com.example.viewpager.dynamic_viewpager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.viewpager.ViewpagerFragment;

public class DynamicViewPagerAdapter extends FragmentStateAdapter {

    private String[] fragmentData;
    private int numPages;

    public DynamicViewPagerAdapter(Fragment fragment, String[] fragmentData, int numPages) {
        super(fragment);

        this.fragmentData = fragmentData;
        this.numPages = numPages;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("key", fragmentData[position]);
        DynamicFragment fragment = new DynamicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return numPages;
    }
}