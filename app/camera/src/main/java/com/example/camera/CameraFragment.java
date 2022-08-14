package com.example.camera;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.camera.databinding.FragmentCameraBinding;
import com.example.commonmodule.ToolbarManager;

public class CameraFragment extends Fragment implements CameraManager.CameraCallback {

    FragmentCameraBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private PermissionManager permissionManager;
    private String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private CameraManager cameraManager;
    private Uri photoUri;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCameraBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        permissionManager = PermissionManager.getInstance(requireContext());

        cameraManager = CameraManager.getInstance(requireActivity(), this);

        binding.btnClickPhoto.setOnClickListener(v -> {
            if (!permissionManager.checkPermissions(permissions)) {
                permissionManager.askPermissions(requireActivity(), permissions, 100);
            } else {
                //permission granted
                cameraManager.openCamera();
            }
        });

        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar2,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraManager.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                Log.d("TAG", "1");
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), photoUri);
                //binding.imageView.setImageBitmap(bitmap);

                cameraManager.setPic(binding.imageView);
                cameraManager.addPicToGallery(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            permissionManager.handlePermissionResult(requireActivity(), 100, permissions,
                    grantResults);

            //permission granted
            cameraManager.openCamera();
        }
    }

    @Override
    public void getPhotoUri(Uri photoUri, Intent takePictureIntent,
                            int REQUEST_IMAGE_CAPTURE) {
        this.photoUri = photoUri;
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
    }
}