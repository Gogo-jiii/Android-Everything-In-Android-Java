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
import com.example.tablayout.databinding.FragmentSkeletonDynamicBinding;

import java.util.Objects;

public class SkeletonDynamicFragment extends Fragment {

    FragmentSkeletonDynamicBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSkeletonDynamicBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupUI();
        return binding.getRoot();
    }

    private void setupUI() {
        Bundle bundle = getArguments();
        binding.textView20.setText(Objects.requireNonNull(bundle).getString("key"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}