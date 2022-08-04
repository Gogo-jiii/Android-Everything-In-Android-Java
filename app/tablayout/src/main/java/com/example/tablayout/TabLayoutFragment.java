package com.example.tablayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.tablayout.databinding.FragmentTabLayoutBinding;

public class TabLayoutFragment extends Fragment {

    FragmentTabLayoutBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTabLayoutBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnTabLayoutStaticFragments.setOnClickListener(view -> navController.navigate(R.id.action_tabLayoutFragment_to_tabLayoutStaticFragment));

        binding.btnTabLayoutDynamicFragments.setOnClickListener(view -> navController.navigate(R.id.action_tabLayoutFragment_to_tabLayoutDynamicFragment));

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