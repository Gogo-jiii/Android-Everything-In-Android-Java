package com.example.pickimagefromgallery;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
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

import com.example.commonmodule.ToolbarManager;
import com.example.pickimagefromgallery.databinding.FragmentPickImageFromGalleryBinding;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PickImageFromGalleryFragment extends Fragment {

    FragmentPickImageFromGalleryBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;

    private PermissionManager permissionManager;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
    private final int PICK_SINGLE_IMAGE_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION = 100;
    private final int PICK_MULTIPLE_IMAGES_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION = 200;
    private final int PICK_SINGLE_IMAGE_FROM_GALLERY_REQUEST_CODE = 300;
    private final int PICK_MULTIPLE_IMAGES_FROM_GALLERY_REQUEST_CODE = 400;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPickImageFromGalleryBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        permissionManager = PermissionManager.getInstance(requireContext());

        binding.btnPickImageFromGallery.setOnClickListener(v -> {
            if (!permissionManager.checkPermissions(permissions)) {
                permissionManager.askPermissions(requireActivity(), permissions,
                        PICK_SINGLE_IMAGE_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION);
            } else {
                pickSingleImageFromGallery();
            }
        });

        binding.btnMultipleImagesFromGallery.setOnClickListener(v -> {
            if (!permissionManager.checkPermissions(permissions)) {
                permissionManager.askPermissions(requireActivity(), permissions,
                        PICK_MULTIPLE_IMAGES_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION);
            } else {
                pickMultipleImagesFromGallery();
            }
        });

        return binding.getRoot();
    }

    private void pickMultipleImagesFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                PICK_MULTIPLE_IMAGES_FROM_GALLERY_REQUEST_CODE);
    }

    private void pickSingleImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_SINGLE_IMAGE_FROM_GALLERY_REQUEST_CODE);
    }

    @Override public void onActivityResult(int requestCode, int resultCode,
                                              @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        if (requestCode == PICK_SINGLE_IMAGE_FROM_GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            binding.imageView.setImageURI(uri);
        } else if (requestCode == PICK_MULTIPLE_IMAGES_FROM_GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            if (data.getClipData() != null) {
                int count = data.getClipData().getItemCount();

                for (int i = 0; i < count; i++) {
                    Uri uri = data.getClipData().getItemAt(i).getUri();
                    File file = new File(uri.getPath());
                    Log.d("TAG_URI: ", file.getName());
                    binding.imageView.setImageURI(data.getClipData().getItemAt(0).getUri());
                }
            } else {
                Uri uri = data.getData();
                File file = new File(uri.getPath());
                Log.d("TAG_PATH: ", file.getName());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override public void onRequestPermissionsResult(int requestCode,
                                                     @NonNull @NotNull String[] permissions,
                                                     @NonNull @NotNull int[] grantResults) {
        if (requestCode == PICK_SINGLE_IMAGE_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION &&
                permissionManager.handlePermissionResult(requireActivity(),
                PICK_SINGLE_IMAGE_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION, permissions,
                grantResults)) {
            pickSingleImageFromGallery();
        } else if (requestCode == PICK_MULTIPLE_IMAGES_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION &&
                permissionManager.handlePermissionResult(requireActivity(),
                PICK_MULTIPLE_IMAGES_FROM_GALLERY_READ_EXTERNAL_STORAGE_PERMISSION, permissions,
                grantResults)) {
            pickMultipleImagesFromGallery();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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