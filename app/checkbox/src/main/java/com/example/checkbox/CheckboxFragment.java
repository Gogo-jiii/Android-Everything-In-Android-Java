package com.example.checkbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.checkbox.databinding.FragmentCheckboxBinding;
import com.example.commonmodule.ToolbarManager;


public class CheckboxFragment extends Fragment implements View.OnClickListener {

    FragmentCheckboxBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCheckboxBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        binding.checkBoxA.setOnClickListener(this);
        binding.checkBoxB.setOnClickListener(this);
        binding.checkBoxC.setOnClickListener(this);
        binding.checkBoxD.setOnClickListener(this);

        setupToolbar();
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
    public void onClick(View view) {
        boolean isChecked = ((CheckBox) view).isChecked();

        if (view == binding.checkBoxA) {
            if (isChecked) {
                Toast.makeText(getContext(), "A is checked.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "A is un-checked.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (view == binding.checkBoxB) {
            if (isChecked) {
                Toast.makeText(getContext(), "B is checked.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "B is un-checked.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (view == binding.checkBoxC) {
            if (isChecked) {
                Toast.makeText(getContext(), "C is checked.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "C is un-checked.",
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (view == binding.checkBoxD) {
            if (isChecked) {
                Toast.makeText(getContext(), "D is checked.",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "D is un-checked.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}