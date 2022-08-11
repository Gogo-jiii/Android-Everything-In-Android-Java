package com.example.storage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.storage.databinding.FragmentStorageBinding;

public class StorageFragment extends Fragment {

    FragmentStorageBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStorageBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnInternalStorage.setOnClickListener(v -> gotoInternalStorageFragment());

        binding.btnExternalStorage.setOnClickListener(v -> gotoExternalStorageFragment());
        return binding.getRoot();
    }

    private void gotoExternalStorageFragment() {
        navController.navigate(R.id.action_storageFragment_to_externalStorageFragment);
    }

    private void gotoInternalStorageFragment() {
        navController.navigate(R.id.action_storageFragment_to_internalStorageFragment);
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.storageToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}