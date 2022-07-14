package com.example.datepickerdialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.datepickerdialog.databinding.FragmentDatePickerDialogBinding;

import java.util.Calendar;


public class DatePickerDialogFragment extends Fragment {

    FragmentDatePickerDialogBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private DatePickerDialog datePickerDialog;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDatePickerDialogBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();

        binding.editText.requestFocus();
        binding.editText.setOnClickListener(view -> openDatepicker());

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

    private void openDatepicker() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        datePickerDialog = new DatePickerDialog(getContext(), (view, year1, monthOfYear, dayOfMonth) -> {
            int month1 = monthOfYear + 1;
            String strMonth = "" + month1;
            String strDayOfMonth = "" + dayOfMonth;

            if (month1 < 10) {
                strMonth = "0" + strMonth;
            }
            if (dayOfMonth < 10) {
                strDayOfMonth = "0" + strDayOfMonth;
            }
            String date = strDayOfMonth + "/" + strMonth + "/" + year1;
            binding.editText.setText(date);
        }, year, month, day);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }

}