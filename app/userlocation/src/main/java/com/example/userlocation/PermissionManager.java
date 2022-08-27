package com.example.userlocation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;

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
        int numberOfPermissions = permissions.length;
        int numberOfPermissionsGranted = 0;
        boolean isLocationPermissionGranted;

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED) {
                numberOfPermissionsGranted = numberOfPermissionsGranted + 1;
            }
        }

        isLocationPermissionGranted = numberOfPermissionsGranted == numberOfPermissions;
        return isLocationPermissionGranted;
    }

    boolean checkPreciseAndApproximateLocationPermissions(String[] permissions) {
        int numberOfPermissions = permissions.length;
        int numberOfPermissionsGranted = 0;
        boolean isLocationPermissionGranted;

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PermissionChecker.PERMISSION_GRANTED) {
                numberOfPermissionsGranted = numberOfPermissionsGranted + 1;
            }
        }

        isLocationPermissionGranted = numberOfPermissionsGranted > 0;
        return isLocationPermissionGranted;
    }

    void askPermissions(Activity activity, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    boolean handlePermissionResult(int[] grantResults) {
        if (grantResults.length > 0) {
            return grantResults[0] != PackageManager.PERMISSION_DENIED;
        }
        return false;
    }

    boolean handleApproximateLocationPermissionResult(int[] grantResults) {

        if (grantResults.length > 0) {

            return grantResults[0] != PackageManager.PERMISSION_DENIED;
            //showPermissionRational(activity, requestCode);
        }
        return false;
    }

    boolean handlePreciseAndApproximateLocationPermissionsResult(int[] grantResults) {

        if (grantResults.length > 0) {

            return grantResults[0] != PackageManager.PERMISSION_DENIED || grantResults[1] != PackageManager.PERMISSION_DENIED;
            //showPermissionRational(activity, requestCode);
        }
        return false;
    }

    boolean handlePreciseLocationPermissionsResult(int[] grantResults) {

        if (grantResults.length > 0) {

            return grantResults[0] != PackageManager.PERMISSION_DENIED;
            //showPermissionRational(activity, requestCode);
        }
        return false;
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