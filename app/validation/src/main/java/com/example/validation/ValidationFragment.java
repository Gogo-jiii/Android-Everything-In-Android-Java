package com.example.validation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.validation.databinding.FragmentValidationBinding;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ValidationFragment extends Fragment implements TextWatcher,
        ValidationManager.ErrorSetter {

    FragmentValidationBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentValidationBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        setTextWatcher();

        binding.btnSubmit.setOnClickListener(v -> {
            ValidationManager.getInstance().doValidation(this, binding.tilEmpty).checkEmpty();

            ValidationManager.getInstance().doValidation(this, binding.tilNumberofDigits).checkEmpty().checkPhoneNumber();

            ValidationManager.getInstance().doValidation(this, binding.tilEmail).checkEmpty().checkEmail();

            ValidationManager.getInstance().doValidation(this, binding.tilPassword).checkEmpty();

            ValidationManager.getInstance().doValidation(this, binding.tilRePassword).checkEmpty().matchPassword(binding.tilPassword, binding.tilRePassword);

            if (ValidationManager.getInstance().isAllValid()) {
                Toast.makeText(getContext(), "All valid.", Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }

    private void setTextWatcher() {
        Objects.requireNonNull(binding.tilEmpty.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(binding.tilEmail.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(binding.tilNumberofDigits.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(binding.tilPassword.getEditText()).addTextChangedListener(this);
        Objects.requireNonNull(binding.tilRePassword.getEditText()).addTextChangedListener(this);
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.hashCode() == Objects.requireNonNull(binding.tilEmpty.getEditText()).getText().hashCode()) {
            binding.tilEmpty.setErrorEnabled(false);
        } else if (editable.hashCode() == Objects.requireNonNull(binding.tilNumberofDigits.getEditText()).getText().hashCode()) {
            binding.tilNumberofDigits.setErrorEnabled(false);
        } else if (editable.hashCode() == Objects.requireNonNull(binding.tilEmail.getEditText()).getText().hashCode()) {
            binding.tilEmail.setErrorEnabled(false);
        } else if (editable.hashCode() == Objects.requireNonNull(binding.tilPassword.getEditText()).getText().hashCode()) {
            binding.tilPassword.setErrorEnabled(false);
        } else if (editable.hashCode() == Objects.requireNonNull(binding.tilRePassword.getEditText()).getText().hashCode()) {
            binding.tilRePassword.setErrorEnabled(false);
        }
    }

    @Override
    public void setError(TextInputLayout textInputLayout, String errorMsg) {
        textInputLayout.setError(errorMsg);
    }
}