package com.example.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.service.databinding.FragmentBoundedServiceBinding;

public class BoundedServiceFragment extends Fragment {

    FragmentBoundedServiceBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    Intent boundedServiceIntent;
    BoundedService boundedService;
    private boolean isServiceBounded = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBoundedServiceBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        boundedServiceIntent = new Intent(requireActivity(), BoundedService.class);

        binding.btnBindToService.setOnClickListener(v -> {
            if (!isServiceBounded) {
                requireActivity().bindService(boundedServiceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                isServiceBounded = true;
                Toast.makeText(requireContext(), "Service is bounded.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnUnbindService.setOnClickListener(v -> {
            if (isServiceBounded) {
                requireActivity().unbindService(serviceConnection);
                isServiceBounded = false;
                Toast.makeText(requireContext(), "Service is un-bounded.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnGetDataFromBoundedService.setOnClickListener(v -> {
            if (isServiceBounded) {
                if(boundedService != null){
                    int num = boundedService.data;
                    Toast.makeText(requireContext(), "Data: " + num, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(requireContext(), "The service is null.", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(requireContext(), "The service is not bounded.", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundedService.LocalBinder binder = (BoundedService.LocalBinder) service;
            boundedService = binder.getService();
            //isServiceBounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //isServiceBounded = false;
        }
    };

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.boundedserviceToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}