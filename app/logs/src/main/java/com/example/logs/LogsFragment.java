package com.example.logs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.logs.databinding.FragmentLogsBinding;

import java.util.Objects;


public class LogsFragment extends Fragment {

    private static final String TAG = LogsFragment.class.getName();
    FragmentLogsBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLogsBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnShowLogs.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Check logcat on android studio...", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "IT Wala...");
            Log.e(TAG, "IT Wala...");
            Log.i(TAG, "IT Wala...");
            Log.v(TAG, "IT Wala...");
            Log.w(TAG, "IT Wala...");
        });
        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }
}