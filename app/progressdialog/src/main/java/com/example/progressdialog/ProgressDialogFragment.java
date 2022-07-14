package com.example.progressdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.progressdialog.databinding.FragmentProgressDialogBinding;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;


public class ProgressDialogFragment extends Fragment {

    FragmentProgressDialogBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;
    CircularProgressIndicator circularProgressIndicator;
    LinearProgressIndicator linearProgressIndicator;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProgressDialogBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        circularProgressIndicator = binding.progressBarCircular;
        linearProgressIndicator = binding.progressBarLinear;

        setupProressbars();

        binding.btnShowProgressDialog.setOnClickListener(view -> {
            circularProgressIndicator.setVisibility(View.VISIBLE);
            linearProgressIndicator.setVisibility(View.VISIBLE);
        });
        return binding.getRoot();
    }

    private void setupProressbars() {
        circularProgressIndicator.setIndicatorDirection(CircularProgressIndicator.
                INDICATOR_DIRECTION_COUNTERCLOCKWISE);
        circularProgressIndicator.setIndicatorSize(150);
        circularProgressIndicator.setTrackThickness(10);
        circularProgressIndicator.setIndeterminate(true);
        circularProgressIndicator.setIndicatorColor(getResources().getColor(R.color.purple_500));


        linearProgressIndicator.setIndicatorDirection(LinearProgressIndicator.
                INDICATOR_DIRECTION_RIGHT_TO_LEFT);
        linearProgressIndicator.setIndicatorColor(getResources().getIntArray(R.array.progress_colors));
        linearProgressIndicator.setIndeterminate(true);
        linearProgressIndicator.setIndeterminateAnimationType(LinearProgressIndicator.
                INDETERMINATE_ANIMATION_TYPE_CONTIGUOUS);
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