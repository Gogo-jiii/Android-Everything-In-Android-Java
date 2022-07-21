package com.example.chips;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.chips.databinding.FragmentChipsBinding;
import com.example.commonmodule.ToolbarManager;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;


public class ChipsFragment extends Fragment {

    FragmentChipsBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private ArrayList<String> multipleCheckedFilterChips = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChipsBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();

        handleChips();
        getMultipleCheckedFilterChips();
        return binding.getRoot();
    }

    private void getMultipleCheckedFilterChips() {
        for (int i = 0; i < binding.chipGroupFilter.getChildCount(); i++) {
            Chip chip = (Chip) binding.chipGroupFilter.getChildAt(i);

            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    multipleCheckedFilterChips.add(buttonView.getText().toString());
                } else {
                    multipleCheckedFilterChips.remove(buttonView.getText().toString());
                }

                if (!multipleCheckedFilterChips.isEmpty()) {
                    Toast.makeText(getContext(), multipleCheckedFilterChips.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void handleChips() {
        binding.chip1EntryChip.setOnCloseIconClickListener(v -> binding.chipGroupEntry.removeView(v));

        binding.chip2EntryChip.setOnCloseIconClickListener(v -> binding.chipGroupEntry.removeView(v));

        binding.chip3EntryChip.setOnCloseIconClickListener(v -> binding.chipGroupEntry.removeView(v));

        binding.chipEntry.setOnCloseIconClickListener(v -> Toast.makeText(getContext(), "closed", Toast.LENGTH_SHORT).show());

        binding.chipGroupChoice.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.size() > 0) {
                Chip chip = group.findViewById(checkedIds.get(0));
                Toast.makeText(getContext(), "Choice Chip: " + chip.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.chipGroupEntry.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.size() > 0) {
                Chip chip = group.findViewById(checkedIds.get(0));
                Toast.makeText(getContext(), "Entry Chip: " + chip.getText(), Toast.LENGTH_SHORT).show();
            }
        });
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