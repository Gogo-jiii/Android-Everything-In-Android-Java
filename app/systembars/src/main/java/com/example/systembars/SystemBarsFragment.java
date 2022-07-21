package com.example.systembars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.systembars.databinding.FragmentSystemBarsBinding;

import java.util.Objects;

public class SystemBarsFragment extends Fragment implements View.OnClickListener {

    FragmentSystemBarsBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSystemBarsBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnShowStatusBar.setOnClickListener(this);
        binding.btnHideStatusBar.setOnClickListener(this);
        binding.btnShowNavigationBar.setOnClickListener(this);
        binding.btnHideNavigationBar.setOnClickListener(this);

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
        View decorView = requireActivity().getWindow().getDecorView();
        int uiOptions;

        if (v == binding.btnShowStatusBar) {
            decorView.setSystemUiVisibility(0);
        } else if (v == binding.btnHideStatusBar) {
            uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
            decorView.setSystemUiVisibility(uiOptions);
        } else if (v == binding.btnShowNavigationBar) {
            uiOptions = View.SYSTEM_UI_FLAG_VISIBLE;
            decorView.setSystemUiVisibility(uiOptions);
        } else if (v == binding.btnHideNavigationBar) {
            uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}