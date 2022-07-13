package com.example.edittext;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.edittext.databinding.FragmentEditTextBinding;


public class EditTextFragment extends Fragment {

    FragmentEditTextBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEditTextBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        StringBuilder name = new StringBuilder();
        binding.btnGetText.setOnClickListener(view -> {
            name.setLength(0);
            name.append(binding.editText.getText().toString());

            if (!TextUtils.isEmpty(name)) {
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Please Enter your name.", Toast.LENGTH_SHORT).show();
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