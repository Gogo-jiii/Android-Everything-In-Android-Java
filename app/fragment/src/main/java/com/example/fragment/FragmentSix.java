package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragment.databinding.FragmentSixBinding;


public class FragmentSix extends Fragment {

    FragmentSixBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSixBinding.inflate(inflater, container, false);

        binding.toolbar6.setTitle("Fragment Six");

        return binding.getRoot();
    }
}