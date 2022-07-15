package com.example.sharedpreference;

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
import com.example.sharedpreference.databinding.FragmentSharedPreferenceBinding;

import java.util.HashSet;
import java.util.Set;


public class SharedPreferenceFragment extends Fragment {

    FragmentSharedPreferenceBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private SharedPreferenceManager sharedPreferenceManager;
    private StringBuilder result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSharedPreferenceBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        sharedPreferenceManager = SharedPreferenceManager.getInstance(getContext());
        result = new StringBuilder();

        Set<String> stringSet = new HashSet<>();
        stringSet.add("a");
        stringSet.add("b");
        stringSet.add("c");

        binding.btnSaveData.setOnClickListener(view -> {
            sharedPreferenceManager.write("string", "a", SharedPreferenceManager.DATA_TYPE_STRING);
            sharedPreferenceManager.write("int", 1, SharedPreferenceManager.DATA_TYPE_INT);
            sharedPreferenceManager.write("float", 10.5f, SharedPreferenceManager.DATA_TYPE_FLOAT);
            sharedPreferenceManager.write("long", 3L, SharedPreferenceManager.DATA_TYPE_LONG);
            sharedPreferenceManager.write("boolean", true, SharedPreferenceManager.DATA_TYPE_BOOLEAN);
            sharedPreferenceManager.write("set", stringSet, SharedPreferenceManager.DATA_TYPE_SET);

            Toast.makeText(getContext(), "Data saved.", Toast.LENGTH_SHORT).show();
            binding.txtResult.setVisibility(View.GONE);
        });

        binding.btnGetData.setOnClickListener(view -> {
            result.setLength(0);
            result.append("Result :\n" + "String : ").append(sharedPreferenceManager.read("string", SharedPreferenceManager.DATA_TYPE_STRING).getPrefString()).append("\n").append("Integer : ").append(sharedPreferenceManager.read("int", SharedPreferenceManager.DATA_TYPE_INT).getPrefInt()).append("\n").append("Float : ").append(sharedPreferenceManager.read("float", SharedPreferenceManager.DATA_TYPE_FLOAT).getPrefFloat()).append("\n").append("Long : ").append(sharedPreferenceManager.read("long", SharedPreferenceManager.DATA_TYPE_LONG).getPrefLong()).append("\n").append("Boolean : ").append(sharedPreferenceManager.read("boolean", SharedPreferenceManager.DATA_TYPE_BOOLEAN).isPrefBoolean()).append("\n").append("Set : ").append(sharedPreferenceManager.read("set", SharedPreferenceManager.DATA_TYPE_SET).getPrefSet());

            binding.txtResult.setVisibility(View.VISIBLE);
            binding.txtResult.setText(result.toString());
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