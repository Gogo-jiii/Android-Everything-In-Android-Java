package com.example.biometricauth;

import static android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.concurrent.Executor;

public class MyBiometricManager {

    private static MyBiometricManager instance = null;
    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private androidx.biometric.BiometricPrompt.PromptInfo promptInfo;
    private Fragment context;
    private Callback callback;
    private FragmentActivity activity;
    public static final int REQUEST_CODE = 100;

    private MyBiometricManager() {
    }

    public static MyBiometricManager getInstance(FragmentActivity activity, Fragment context) {
        if (instance == null) {
            instance = new MyBiometricManager();
        }
        instance.init(activity, context);
        return instance;
    }

    private void init(FragmentActivity activity, Fragment context) {
        this.activity = activity;
        this.context = context;
        this.callback = (Callback) context;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    boolean checkIfBiometricFeatureAvailable() {
        BiometricManager biometricManager = BiometricManager.from(context.requireContext());

        int i = biometricManager.canAuthenticate();
        if (i == BiometricManager.BIOMETRIC_SUCCESS) {
            Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
            //Toast.makeText(context, "App can authenticate using biometrics.",Toast.LENGTH_SHORT).show();
            return true;
        } else if (i == BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE) {
            Log.e("MY_APP_TAG", "No biometric features available on this device.");
            Toast.makeText(context.requireContext(), "No biometric features available on this device.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (i == BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE) {
            Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
            Toast.makeText(context.requireContext(), "Biometric features are currently unavailable.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (i == BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) {// Prompts the user to create credentials that your app accepts.
            final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
            enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG | DEVICE_CREDENTIAL);

            activity.startActivityForResult(enrollIntent, REQUEST_CODE);
            return false;
        }
        return false;
    }

    void authenticate() {
        setupBiometric();
        biometricPrompt.authenticate(promptInfo);
    }

    private void setupBiometric() {
        executor = ContextCompat.getMainExecutor(context.requireContext());
        biometricPrompt = new BiometricPrompt(activity, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationError(int errorCode,
                                                      @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        callback.onBiometricAuthenticationResult(Callback.AUTHENTICATION_ERROR, errString);
                    }

                    @Override
                    public void onAuthenticationSucceeded(
                            @NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        callback.onBiometricAuthenticationResult(Callback.AUTHENTICATION_SUCCESSFUL,
                                "");
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        callback.onBiometricAuthenticationResult(Callback.AUTHENTICATION_FAILED, "");
                    }
                });

        showBiometricPrompt();
    }

    private void showBiometricPrompt() {
        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();
    }

    interface Callback {
        void onBiometricAuthenticationResult(String result, CharSequence errString);

        String AUTHENTICATION_SUCCESSFUL = "AUTHENTICATION_SUCCESSFUL";
        String AUTHENTICATION_FAILED = "AUTHENTICATION_FAILED";
        String AUTHENTICATION_ERROR = "AUTHENTICATION_ERROR";
    }
}