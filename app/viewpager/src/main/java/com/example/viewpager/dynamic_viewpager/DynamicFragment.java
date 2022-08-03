package com.example.viewpager.dynamic_viewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.viewpager.R;
import com.example.viewpager.databinding.FragmentDynamicBinding;

import java.util.Objects;


public class DynamicFragment extends Fragment {

    FragmentDynamicBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private String data;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDynamicBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setUI();
        setupToolbar();

        return binding.getRoot();
    }

    private void setUI() {
        data = requireArguments().getString("key");
        binding.textView2.setText(data);
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar5,
                false);
        binding.toolbar5.setTitle(data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}