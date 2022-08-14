package com.example.encryption;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.encryption.databinding.FragmentEncryptionBinding;

import java.util.Objects;


public class EncryptionFragment extends Fragment {

    FragmentEncryptionBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private EncryptionManager encryptionManager;
    private String result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEncryptionBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        encryptionManager = EncryptionManager.getInstance();

        binding.btnEncryptData.setOnClickListener(v -> {
            String data = Objects.requireNonNull(binding.textInputLayout.getEditText()).getText().toString();
            if (!TextUtils.isEmpty(data)) {
                result = EncryptionManager.encrypt(data);
                binding.txtResult.setText(result);
            } else {
                Toast.makeText(requireContext(), "Field is empty.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnDecryptData.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(result)) {
                result = EncryptionManager.decrypt(result);
                binding.txtResult.setText(result);
            } else {
                Toast.makeText(requireContext(), "No data to decrypt.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnCreateSHA256.setOnClickListener(v -> {
            String data = Objects.requireNonNull(binding.textInputLayout.getEditText()).getText().toString();
            if (!TextUtils.isEmpty(data)) {
                result = encryptionManager.getSHA256(data);
                binding.txtResult.setText(result);

                if (result.equals(encryptionManager.getSHA256(data))) {
                    Log.d("TAG", "same");
                } else {
                    Log.d("TAG", "NOT same");
                }
            } else {
                Toast.makeText(requireContext(), "Field is empty.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnEncodeBase64.setOnClickListener(v -> {
            String data = Objects.requireNonNull(binding.textInputLayout.getEditText()).getText().toString();
            if (!TextUtils.isEmpty(data)) {
                result = encryptionManager.encodeBase64(data);
                binding.txtResult.setText(result);
            } else {
                Toast.makeText(requireContext(), "Field is empty.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnDecodeBase64.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(result)) {
                result = encryptionManager.decodeBase64(result);
                binding.txtResult.setText(result);
            } else {
                Toast.makeText(requireContext(), "No data to decode.", Toast.LENGTH_SHORT).show();
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