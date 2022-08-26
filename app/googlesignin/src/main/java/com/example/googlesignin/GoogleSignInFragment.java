package com.example.googlesignin;

import android.content.Intent;
import android.net.Uri;
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
import com.example.googlesignin.databinding.FragmentGoogleSignInBinding;
import com.google.firebase.auth.FirebaseUser;

public class GoogleSignInFragment extends Fragment {

    FragmentGoogleSignInBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private GoogleSignInManager googleSignInManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGoogleSignInBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        googleSignInManager = GoogleSignInManager.getInstance(requireContext());
        googleSignInManager.setupGoogleSignInOptions();

        binding.btnGoogleSignIn.setOnClickListener(v -> googleSignInManager.signIn(GoogleSignInFragment.this));

        binding.btnSignOut.setOnClickListener(v -> {
            googleSignInManager.signOut();
            binding.txtProfileInfo.setText("");
        });

        binding.btnGetProfileInfo.setOnClickListener(v -> {
            FirebaseUser account =
                    googleSignInManager.getProfileInfo();
            if (account != null) {
                String personName = account.getDisplayName();
                String personEmail = account.getEmail();
                Uri personPhoto = account.getPhotoUrl();

                String profileInfo = "Name: " + personName + "\n" +
                        "Email: " + personEmail + "\n" +
                        "Photo: " + personPhoto;

                binding.txtProfileInfo.setText(profileInfo);
            }
        });

        return binding.getRoot();
    }

    @Override public void onStart() {
        super.onStart();
        if (googleSignInManager.isUserAlreadySignIn()) {
            Toast.makeText(requireContext(), "Already Signed in.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG","1");
        if (requestCode == googleSignInManager.GOOGLE_SIGN_IN) {
            googleSignInManager.handleSignInResult(data);
        }
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.googlesigninToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}