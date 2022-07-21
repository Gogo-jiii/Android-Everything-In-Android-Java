package com.example.interfacecallback;

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
import com.example.interfacecallback.databinding.FirstFragmentBinding;

public class FirstFragment extends Fragment implements SecondFragment.SomeInterface {

    FirstFragmentBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FirstFragmentBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        SecondFragment.setInstance(this);

        binding.btnGotoSecondFragment.setOnClickListener(v -> navController.navigate(R.id.action_fragmentOne_to_fragmentTwo));
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
    public void callbackFromFragmentTwo() {
        Toast.makeText(getContext(), "Arrived in fragment one from fragment two via interface callback.", Toast.LENGTH_SHORT).show();
    }
}