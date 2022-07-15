package com.example.textinputlayout;

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

import java.util.Objects;

public class TextInputLayoutFragment extends Fragment {

    com.example.textinputlayout.databinding.FragmentTextInputLayoutBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = com.example.textinputlayout.databinding.FragmentTextInputLayoutBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.buttonSubmit.setOnClickListener(v -> {
            String text = Objects.requireNonNull(binding.textInputLayoutError.getEditText()).getText().toString();
            if (text.isEmpty()) {
                binding.textInputLayoutError.setError("Field can't be empty");
            } else {
                binding.textInputLayoutError.setError(null);
                Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
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