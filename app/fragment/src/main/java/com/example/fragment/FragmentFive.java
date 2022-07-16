package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragment.databinding.FragmentFiveBinding;


public class FragmentFive extends Fragment {

    FragmentFiveBinding binding;
    FragmentHandler fragmentHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFiveBinding.inflate(inflater, container, false);

        binding.toolbar5.setTitle("Fragment Five");
        fragmentHandler = FragmentHandler.getInstance();

        binding.btnGotoFragmentSixWithOutBackstack.setOnClickListener(view -> gotoSixthFragmentWithOutBackstack());

        return binding.getRoot();
    }

    private void gotoSixthFragmentWithOutBackstack() {
        fragmentHandler.replaceFragment(new FragmentSix(), getParentFragmentManager(), null,
                R.id.container, false, "FragmentSix");
    }

}