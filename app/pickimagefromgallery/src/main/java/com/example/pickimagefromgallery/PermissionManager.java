package com.example.pickimagefromgallery;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

public class PermissionManager {

    private static PermissionManager instance = null;
    private Context context;

    private PermissionManager() {
    }

    public static PermissionManager getInstance(Context context) {
        if (instance == null) {
            instance = new PermissionManager();
        }
        instance.init(context);
        return instance;
    }

    private void init(Context context) {
        this.context = context;
    }

    boolean checkPermissions(String[] permissions) {
        int size = permissions.length;

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_DENIED) {
                return false;
            }
        }
        return true;
    }

    void askPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    boolean handlePermissionResult(Activity activity, int requestCode, String[] permissions,
                                   int[] grantResults) {
        boolean isAllPermissionsGranted = true;

        if (grantResults.length > 0) {
            for (int grantResult : grantResults) {
                if (grantResult == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(activity, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    isAllPermissionsGranted = false;
                    Toast.makeText(activity, "Permission denied.", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
            //showPermissionRational(activity, requestCode);
        } else {
            isAllPermissionsGranted = false;
        }
        return isAllPermissionsGranted;
    }

    private void showPermissionRational(Activity activity, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                showMessageOKCancel("You need to allow access to the permission(s)!",
                        (dialog, which) -> askPermissions(activity,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.CAMERA},
                                requestCode));
            }
        }
    }

    void showMessageOKCancel(String msg, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("Ok", onClickListener)
                .setNegativeButton("Cancel", onClickListener)
                .create()
                .show();
    }
}