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

import com.example.button.databinding.FragmentMultipleButtonsOnClickListenerBinding;
import com.example.commonmodule.ToolbarManager;


public class MultipleButtonsOnClickListenerFragment extends Fragment implements View.OnClickListener {

    FragmentMultipleButtonsOnClickListenerBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMultipleButtonsOnClickListenerBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();

        binding.btnOne.setOnClickListener(this);
        binding.btnTwo.setOnClickListener(this);
        binding.btnThree.setOnClickListener(this);

        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.btnOne) {
            Toast.makeText(getContext(), "First button clicked.", Toast.LENGTH_SHORT).show();
        } else if (view == binding.btnTwo) {
            Toast.makeText(getContext(), "Second button clicked.", Toast.LENGTH_SHORT).show();
        } else if (view == binding.btnThree) {
            Toast.makeText(getContext(), "Third button clicked.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}