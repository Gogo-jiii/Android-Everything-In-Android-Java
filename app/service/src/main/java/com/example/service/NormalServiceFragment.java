package com.example.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.service.databinding.FragmentNormalServiceBinding;

public class NormalServiceFragment extends Fragment {

    FragmentNormalServiceBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private Intent normalServiceIntent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNormalServiceBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        normalServiceIntent = new Intent(requireActivity(), NormalService.class);

        binding.btnStartNormalService.setOnClickListener(v -> startService());

        binding.btnStopNormalService.setOnClickListener(v -> stopService());

        return binding.getRoot();
    }

    private void startService() {
        requireActivity().startService(normalServiceIntent);
    }

    private void stopService() {
        requireActivity().stopService(normalServiceIntent);
    }

    @Override public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(broadcastReceiver,
                new IntentFilter("broadcast"));
    }

    @Override public void onStop() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("msg");
            binding.normalserviceTextView.setText(msg);
        }
    };

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.normalserviceToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}