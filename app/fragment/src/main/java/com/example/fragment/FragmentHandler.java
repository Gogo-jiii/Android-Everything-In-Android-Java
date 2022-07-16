package com.example.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentHandler {

    private static FragmentHandler instance;
    private Fragment currentFragment;

    private FragmentHandler() {
    }

    public static FragmentHandler getInstance() {
        if (instance == null) {
            instance = new FragmentHandler();
        }
        return instance;
    }

    void addFragmentToActivity(Fragment fragment, FragmentManager supportFragmentManager, Bundle bundle, int containerID) {
        currentFragment = fragment;
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(containerID, fragment);

        if (null != bundle)
            fragment.setArguments(bundle);

        fragmentTransaction.commit();
    }

    void replaceFragment(Fragment fragment, FragmentManager fragmentManager, Bundle bundle, int containerID, boolean isAddToBAckStack, String backstackTag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        currentFragment = fragment;

        //bundle data
        if (null != bundle)
            fragment.setArguments(bundle);

        //backstack
        if (isAddToBAckStack) {
            fragmentTransaction.addToBackStack(backstackTag);
        }

        fragmentTransaction.replace(containerID, fragment);
        fragmentTransaction.commit();
    }

    public void onFragmentBackPress(FragmentManager supportFragmentManager) {
        supportFragmentManager.beginTransaction().remove(currentFragment).commitAllowingStateLoss();
        supportFragmentManager.popBackStack();
    }
}
