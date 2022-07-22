package com.example.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.toolbar.databinding.FragmentToolbarBinding;

public class ToolbarFragment extends Fragment implements View.OnClickListener {

    FragmentToolbarBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentToolbarBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnToolbarMenu.setOnClickListener(this);
        binding.btnCollapsingToobar.setOnClickListener(this);
        binding.btnScrollingToolbar.setOnClickListener(this);
        binding.btnImageToolbar.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if(v == binding.btnToolbarMenu){
            navController.navigate(R.id.action_toolbarFragment_to_toolbarMenuFragment);
        }else  if(v == binding.btnCollapsingToobar){
            navController.navigate(R.id.action_toolbarFragment_to_collapsingToolbarFragment);
        }else  if(v == binding.btnScrollingToolbar){
            navController.navigate(R.id.action_toolbarFragment_to_scrollingToolbarFragment);
        }else  if(v == binding.btnImageToolbar){
            navController.navigate(R.id.action_toolbarFragment_to_toolbarWithImageFragment);
        }
    }
}