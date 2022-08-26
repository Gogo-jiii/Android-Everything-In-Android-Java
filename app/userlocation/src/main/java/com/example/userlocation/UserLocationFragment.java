package com.example.userlocation;

import android.Manifest;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.work.BackoffPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.commonmodule.ToolbarManager;
import com.example.userlocation.databinding.FragmentUserLocationBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class UserLocationFragment extends Fragment {

    FragmentUserLocationBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private PermissionManager permissionManager;
    private LocationManager locationManager;
    private WorkRequest foregroundWorkRequest;

    private final String[] foreground_location_permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    private final String[] background_location_permissions = {Manifest.permission.ACCESS_BACKGROUND_LOCATION};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUserLocationBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        permissionManager = PermissionManager.getInstance(requireContext());
        locationManager = LocationManager.getInstance(requireContext());

        binding.btnGetLocation.setOnClickListener(v -> {
            if (!permissionManager.checkPreciseAndApproximateLocationPermissions(foreground_location_permissions)) {
                permissionManager.askPermissions(requireActivity(),
                        foreground_location_permissions, 100);
            } else {
                getLocation();
            }
        });

        binding.btnGetAddress.setOnClickListener(v -> {
            if (!permissionManager.checkPreciseAndApproximateLocationPermissions(foreground_location_permissions)) {
                permissionManager.askPermissions(requireActivity(),
                        foreground_location_permissions, 100);
            } else {
                getAddress();
            }
        });

        binding.btnGetBackgroundLocation.setOnClickListener(v -> {
            if (!permissionManager.checkPermissions(background_location_permissions)) {
                permissionManager.askPermissions(requireActivity(),
                        background_location_permissions, 200);
            } else {
                getBackgroundLocation();
            }
        });
        return binding.getRoot();
    }

    private void getBackgroundLocation() {
        foregroundWorkRequest = new OneTimeWorkRequest.Builder(BackgroundLocationWork.class)
                .addTag("LocationWork")
                .setBackoffCriteria(BackoffPolicy.LINEAR,
                        OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance(requireActivity()).enqueue(foregroundWorkRequest);
    }

    private void getAddress() {
        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
        Location location = locationManager.getLastLocation();
        if (location != null) {
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude()
                        , location.getLongitude(), 1);

                Address address = addresses.get(0);

                String strAddress = "Addressline: " + address.getAddressLine(0) + "\n" +
                        "Admin Area: " + address.getAdminArea() + "\n" +
                        "Country Name: " + address.getCountryName() + "\n" +
                        "Feature Name: " + address.getFeatureName() + "\n" +
                        "Locality: " + address.getLocality() + "\n";

                binding.txtResult.setText(strAddress);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(requireContext(), "Please try again!", Toast.LENGTH_SHORT).show();
        }
    }

    private void getLocation() {
        if (locationManager.isLocationEnabled()) {
            Location location = locationManager.getLastLocation();
            if (location != null) {
                binding.txtResult.setText(new StringBuilder().append("Location: \n").append("Lat: ").append(location.getLatitude()).append(", ").append("Long: ").append(location.getLongitude()).toString());
            } else {
                Toast.makeText(requireActivity(), "Could not fetch location. Please Try again!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            locationManager.createLocationRequest();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && permissionManager.handlePreciseAndApproximateLocationPermissionsResult(grantResults)) {
            getLocation();
        }else if (requestCode == 200 && permissionManager.handlePermissionResult(grantResults)) {
            getBackgroundLocation();
        }
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.userlocationToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}