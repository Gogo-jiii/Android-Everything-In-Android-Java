package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragment.databinding.FragmentOneBinding;


public class FragmentOne extends Fragment {

    FragmentOneBinding binding;
    FragmentHandler fragmentHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOneBinding.inflate(inflater, container, false);

        binding.toolbar.setTitle("Fragment One");
        fragmentHandler = FragmentHandler.getInstance();

        binding.btnGotoSecondFragment.setOnClickListener(view -> gotoSecondFragment());

        binding.btnGotoThirdFragmentWithData.setOnClickListener(view -> gotoThirdFragmentWithData());

        binding.btnGotoFragmentFourWithBackstack.setOnClickListener(view -> gotoFourthFragmentWithBackstack());

        return binding.getRoot();
    }

    private void gotoFourthFragmentWithBackstack() {
        fragmentHandler.replaceFragment(new FragmentFour(), getParentFragmentManager(), null,
                R.id.container, true, "FragmentFour");
    }

    private void gotoThirdFragmentWithData() {
        Bundle bundle = new Bundle();
        bundle.putString("name","IT wala");

        fragmentHandler.replaceFragment(new FragmentThree(), getParentFragmentManager(), bundle,
                R.id.container, false, "FragmentThree");
    }

    private void gotoSecondFragment() {
        fragmentHandler.replaceFragment(new FragmentTwo(), getParentFragmentManager(), null,
                R.id.container, false, "FragmentTwo");
    }
}