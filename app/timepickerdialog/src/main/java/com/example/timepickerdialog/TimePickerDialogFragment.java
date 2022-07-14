package com.example.timepickerdialog;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.timepickerdialog.databinding.FragmentTimePickerDialogBinding;

import java.util.Calendar;

public class TimePickerDialogFragment extends Fragment {

    FragmentTimePickerDialogBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private TimePickerDialog timePickerDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTimePickerDialogBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();

        binding.editText.requestFocus();
        binding.editText.setOnClickListener(view -> openTimepickerDialog());

        return binding.getRoot();
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

    private void openTimepickerDialog() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(getContext(), (view, hourOfDay, minuteOfHour) -> {
            String hour1 = "" + hourOfDay;
            String minute1 = "" + minuteOfHour;

            if (hourOfDay < 10) {
                hour1 = "0" + hour1;
            }
            if (minuteOfHour < 10) {
                minute1 = "0" + minute1;
            }
            String time = hour1 + ":" + minute1;
            binding.editText.setText(time);
        }, hour, minute, false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
}