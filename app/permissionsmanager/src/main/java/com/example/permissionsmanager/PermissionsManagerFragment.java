package com.example.permissionsmanager;

import android.Manifest;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.permissionsmanager.databinding.FragmentPermissionsManagerBinding;

public class PermissionsManagerFragment extends Fragment {

    FragmentPermissionsManagerBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;
    private final int PERMISSION_REQUEST_CODE = 100;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPermissionsManagerBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnCheckPermissions.setOnClickListener(view -> checkPermissions());

        binding.btnAskPermissions.setOnClickListener(view -> askPermissions());

        return binding.getRoot();
    }

    private void askPermissions() {
        if (!PermissionManager.getInstance(requireActivity()).checkPermissions(permissions)) {
            PermissionManager.getInstance(requireActivity()).askPermissions(requireActivity(),
                    permissions, PERMISSION_REQUEST_CODE);
        } else {
            Toast.makeText(requireContext(), "Permission already granted!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermissions() {
        if (PermissionManager.getInstance(requireActivity()).checkPermissions(permissions)) {
            Toast.makeText(requireContext(), "Permissions already granted.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Please request permissions.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void onRequestPermissionsResult(int requestCode,
                                                     @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            PermissionManager.getInstance(requireActivity()).handlePermissionResult(requireActivity(),
                    PERMISSION_REQUEST_CODE,
                    permissions,
                    grantResults);
        }
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