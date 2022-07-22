package com.example.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.toolbar.databinding.FragmentToolbarMenuBinding;

public class ToolbarMenuFragment extends Fragment {

    FragmentToolbarMenuBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentToolbarMenuBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        return binding.getRoot();
    }

    private void setupToolbar() {
        binding.toolbar2.setTitle("Toolbar Menu");

        binding.toolbar2.setNavigationOnClickListener(v -> {
            Toast.makeText(getContext(), "back icon clicked.", Toast.LENGTH_SHORT).show();
            //navController.popBackStack();
        });

        binding.toolbar2.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.one) {
                Toast.makeText(getContext(), "one clicked.", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.bluetooth) {
                Toast.makeText(getContext(), "bluetooth clicked.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}