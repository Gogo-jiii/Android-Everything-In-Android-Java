package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragment.databinding.FragmentFourBinding;


public class FragmentFour extends Fragment {

    FragmentFourBinding binding;
    FragmentHandler fragmentHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFourBinding.inflate(inflater, container, false);

        binding.toolbar4.setTitle("Fragment Four");
        fragmentHandler = FragmentHandler.getInstance();

        binding.btnGotoFragmentFiveWithBackstack.setOnClickListener(view -> gotoFifthFragmentWithBackstack());

        return binding.getRoot();
    }

    private void gotoFifthFragmentWithBackstack() {
        fragmentHandler.replaceFragment(new FragmentFive(), getParentFragmentManager(), null,
                R.id.container, true, "FragmentFive");
    }

}