package com.example.biometricauth;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.biometricauth.databinding.FragmentBiometricAuthBinding;
import com.example.commonmodule.ToolbarManager;


public class BiometricAuthFragment extends Fragment implements MyBiometricManager.Callback {

    FragmentBiometricAuthBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private MyBiometricManager myBiometricManager;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBiometricAuthBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        myBiometricManager = MyBiometricManager.getInstance(getActivity(), this);

        binding.btnFingerPrintAuth.setOnClickListener(v -> {
            if (myBiometricManager.checkIfBiometricFeatureAvailable()) {
                myBiometricManager.authenticate();
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

    @Override
    public void onBiometricAuthenticationResult(String result, CharSequence errString) {
        switch (result) {
            case AUTHENTICATION_SUCCESSFUL:
                Toast.makeText(requireContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "AUTHENTICATION_SUCCESSFUL");
                break;

            case AUTHENTICATION_FAILED:
                Toast.makeText(requireContext(), "Authentication failed",
                                Toast.LENGTH_SHORT)
                        .show();
                Log.d("TAG", "AUTHENTICATION_FAILED");
                break;

            case AUTHENTICATION_ERROR:
                Toast.makeText(requireContext(),
                                "Authentication error: " + errString, Toast.LENGTH_SHORT)
                        .show();
                Log.d("TAG", "AUTHENTICATION_ERROR");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyBiometricManager.REQUEST_CODE && resultCode == RESULT_OK) {
            //check if biometric is now enrolled or not
        }
    }
}