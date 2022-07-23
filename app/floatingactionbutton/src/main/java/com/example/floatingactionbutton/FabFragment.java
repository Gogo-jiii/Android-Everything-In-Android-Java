package com.example.floatingactionbutton;

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
import com.example.floatingactionbutton.databinding.FragmentFabBinding;

public class FabFragment extends Fragment {

    FragmentFabBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFabBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.fabDefault.setOnClickListener(v -> Toast.makeText(getContext(), "Default Fab", Toast.LENGTH_SHORT).show());

        binding.fabMini.setOnClickListener(v -> Toast.makeText(getContext(), "Mini Fab", Toast.LENGTH_SHORT).show());

        binding.fabExtended.shrink();
        binding.fabExtended.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Extended Fab", Toast.LENGTH_SHORT).show();
            if (binding.fabExtended.isExtended()) {
                binding.fabExtended.shrink();
            }else {
                binding.fabExtended.extend();
            }
        });

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