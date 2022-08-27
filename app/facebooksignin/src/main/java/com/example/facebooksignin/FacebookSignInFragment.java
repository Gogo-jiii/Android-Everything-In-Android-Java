package com.example.facebooksignin;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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
import com.example.facebooksignin.databinding.FragmentFacebookSignInBinding;
import com.google.firebase.auth.FirebaseUser;

import java.security.MessageDigest;

public class FacebookSignInFragment extends Fragment implements FacebookSignInManager.Callback{

    FragmentFacebookSignInBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private FacebookSignInManager facebookSignInManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFacebookSignInBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        facebookSignInManager = FacebookSignInManager.getInstance(this);
        facebookSignInManager.setupFacebookAuth();
        facebookSignInManager.setLoginButton(binding.btnSignIn);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        facebookSignInManager.isUserAlreadySignedIn();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "!");
        // Pass the activity result back to the Facebook SDK
        facebookSignInManager.getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.facebooksigninToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void getProfile(FirebaseUser user) {
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            boolean emailVerified = user.isEmailVerified();

            String profile = "Name: " + name + "\n" +
                    "Email: " + email + "\n" +
                    "Photo: " + photoUrl + "\n" +
                    "Verified: " + emailVerified;

            Log.d("TAG", profile);
            binding.txtResult.setText(profile);
        } else {
            Toast.makeText(requireActivity(), "No account found!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateUI() {
        binding.txtResult.setText("");
    }

    void getHash() {
        try {
            PackageInfo info = requireActivity().getPackageManager().getPackageInfo(
                    "com.yourappname.app",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "KeyHash:" + Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));
                Toast.makeText(requireActivity(), Base64.encodeToString(md.digest(),
                        Base64.DEFAULT), Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {

        }
    }
}