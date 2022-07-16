package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fragment.databinding.FragmentThreeBinding;

import java.util.Objects;


public class FragmentThree extends Fragment {

    FragmentThreeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentThreeBinding.inflate(inflater, container, false);
        binding.toolbar3.setTitle("Fragment Three");

        getData();
        return binding.getRoot();
    }

    private void getData() {
        String name = requireArguments().getString("name");
        Toast.makeText(getActivity(), "Data: " + name, Toast.LENGTH_SHORT).show();
    }
}