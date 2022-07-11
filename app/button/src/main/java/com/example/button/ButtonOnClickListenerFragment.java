package com.example.button;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.button.databinding.FragmentButtonOnClickListenerBinding;
import com.example.commonmodule.ToolbarManager;


public class ButtonOnClickListenerFragment extends Fragment {

    FragmentButtonOnClickListenerBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentButtonOnClickListenerBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();

        doOnClickListener();
        return binding.getRoot();
    }

    private void doOnClickListener() {
        binding.button.setOnClickListener(view -> Toast.makeText(getContext(), "Button clicked.", Toast.LENGTH_SHORT).show());
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