package com.example.service;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.service.databinding.FragmentForegroundServiceBinding;

public class ForegroundServiceFragment extends Fragment {

    FragmentForegroundServiceBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private Intent foregroundServiceIntent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForegroundServiceBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        foregroundServiceIntent = new Intent(requireActivity(), ForegroundService.class);

        binding.btnStartForegroundService.setOnClickListener(v -> startService());

        binding.btnStopForegroundService.setOnClickListener(v -> stopService());

        return binding.getRoot();
    }

    private void startService() {
        requireActivity().startService(foregroundServiceIntent);
    }

    private void stopService() {
        requireActivity().stopService(foregroundServiceIntent);
    }


    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.foregroundserviceToolabr,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}