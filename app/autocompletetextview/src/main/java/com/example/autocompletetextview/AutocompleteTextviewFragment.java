package com.example.autocompletetextview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.autocompletetextview.databinding.FragmentAutocompleteTextviewBinding;
import com.example.commonmodule.ToolbarManager;

public class AutocompleteTextviewFragment extends Fragment {

    FragmentAutocompleteTextviewBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    String[] data = {"a", "b", "c", "aa", "bb", "cc", "aaa", "bbb", "ccc", "abc"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAutocompleteTextviewBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, data);
        binding.autoCompleteTextView.setAdapter(adapter);
        binding.autoCompleteTextView.setThreshold(2);

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