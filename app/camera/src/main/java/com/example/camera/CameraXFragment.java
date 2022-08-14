package com.example.camera;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Rational;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.camera.databinding.FragmentCameraXBinding;
import com.example.commonmodule.ToolbarManager;

import java.io.File;

public class CameraXFragment extends Fragment {

    FragmentCameraXBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private PermissionManager permissionManager;
    private String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private ImageCapture imgCap;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCameraXBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        permissionManager = PermissionManager.getInstance(requireContext());

        binding.btnOpenCamera.setOnClickListener(v -> {
            if (!permissionManager.checkPermissions(permissions)) {
                permissionManager.askPermissions(requireActivity(), permissions, 100);
            } else {
                //permission granted
                openCamera();
            }
        });

        binding.btnClickPhoto.setOnClickListener(v -> clickPhoto());

        return binding.getRoot();
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                     @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            permissionManager.handlePermissionResult(requireActivity(), 100, permissions,
                    grantResults);

            //permission granted
            openCamera();
        }
    }

    private void openCamera() {
        CameraX.unbindAll();

        Rational aspectRatio = new Rational(binding.textureView.getWidth(), binding.textureView.getHeight());
        Size screen = new Size(binding.textureView.getWidth(), binding.textureView.getHeight()); //size of the
        // screen

        PreviewConfig pConfig =
                new PreviewConfig.Builder().setTargetAspectRatio(aspectRatio).setTargetResolution(screen).build();
        Preview preview = new Preview(pConfig);

        //to update the surface texture we  have to destroy it first then re-add it
        preview.setOnPreviewOutputUpdateListener(
                output -> {
                    ViewGroup parent = (ViewGroup) binding.textureView.getParent();
                    parent.removeView(binding.textureView);
                    parent.addView(binding.textureView, 0);

                    binding.textureView.setSurfaceTexture(output.getSurfaceTexture());
                    updateTransform();
                });


        ImageCaptureConfig imageCaptureConfig =
                new ImageCaptureConfig.Builder().setCaptureMode(ImageCapture.CaptureMode.MIN_LATENCY)
                        .setTargetRotation(requireActivity().getWindowManager().getDefaultDisplay().getRotation()).build();
        imgCap = new ImageCapture(imageCaptureConfig);

        //bind to lifecycle:
        CameraX.bindToLifecycle((LifecycleOwner) this, preview, imgCap);
    }

    private void updateTransform() {
        Matrix mx = new Matrix();
        float w = binding.textureView.getMeasuredWidth();
        float h = binding.textureView.getMeasuredHeight();

        float cX = w / 2f;
        float cY = h / 2f;

        int rotationDgr;
        int rotation = (int) binding.textureView.getRotation();

        switch (rotation) {
            case Surface.ROTATION_0:
                rotationDgr = 0;
                break;
            case Surface.ROTATION_90:
                rotationDgr = 90;
                break;
            case Surface.ROTATION_180:
                rotationDgr = 180;
                break;
            case Surface.ROTATION_270:
                rotationDgr = 270;
                break;
            default:
                return;
        }

        mx.postRotate((float) rotationDgr, cX, cY);
        binding.textureView.setTransform(mx);
    }

    private void clickPhoto() {
        File file =
                new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) + "/" + System.currentTimeMillis() + ".png");
        imgCap.takePicture(file, new ImageCapture.OnImageSavedListener() {
            @Override
            public void onImageSaved(@NonNull File file) {
                String msg = "Pic captured at " + file.getAbsolutePath();
                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show();

                addImageToGallery(file.getPath(), requireActivity());
            }

            @Override
            public void onError(@NonNull ImageCapture.UseCaseError useCaseError,
                                @NonNull String message,
                                @Nullable Throwable cause) {
                String msg = "Pic capture failed : " + message;
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show();
                if (cause != null) {
                    cause.printStackTrace();
                }
            }
        });
    }

    public static void addImageToGallery(final String filePath, final Context context) {

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.MediaColumns.DATA, filePath);

        context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar3,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}