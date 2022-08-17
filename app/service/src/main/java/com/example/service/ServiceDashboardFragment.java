package com.example.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.service.databinding.FragmentServiceDashboardBinding;

public class ServiceDashboardFragment extends Fragment {

    FragmentServiceDashboardBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentServiceDashboardBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnNormalActivity.setOnClickListener(v -> navController.navigate(R.id.action_serviceDashboardFragment_to_normalServiceFragment));

        binding.btnBoundedActivity.setOnClickListener(v -> navController.navigate(R.id.action_serviceDashboardFragment_to_boundedServiceFragment));

        binding.btnForegroundService.setOnClickListener(v -> navController.navigate(R.id.action_serviceDashboardFragment_to_foregroundServiceFragment));

        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}