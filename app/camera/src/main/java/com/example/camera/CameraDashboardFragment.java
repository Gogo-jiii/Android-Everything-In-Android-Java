package com.example.camera;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.camera.databinding.FragmentCameraDashboardBinding;
import com.example.commonmodule.ToolbarManager;

public class CameraDashboardFragment extends Fragment {

    FragmentCameraDashboardBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCameraDashboardBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnUseCameraApi.setOnClickListener(v -> navController.navigate(R.id.action_cameraDashboardFragment_to_cameraFragment));

        binding.btnUseCameraXApi.setOnClickListener(v -> navController.navigate(R.id.action_cameraDashboardFragment_to_cameraXFragment));

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