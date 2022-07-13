package com.example.alertdialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alertdialog.databinding.FragmentAlertDialogBinding;
import com.example.commonmodule.ToolbarManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class AlertDialogFragment extends Fragment implements AlertDialogAdapter.OnRecyclerviewItemClickListener {

    FragmentAlertDialogBinding binding;
    ArrayList<AlertDialogModel> list = new ArrayList<>();
    String[] data = new String[]{"Simple Alert Dialog", "Single Choice Alert Dialog", "Multiple Choice Alert Dialog", "Custom Alert Dialog"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private AlertDialog.Builder builder;
    String[] single_choice_dialog_menu = {"a", "b", "c"};
    String[] multiple_choice_dialog_menu = {"a", "b", "c"};
    private List<String> selectedMultiChoiceItems;
    boolean[] initialStateOfMultiChoiceItems = new boolean[]{false, false, false};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAlertDialogBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();
        prepareRecyclerView();

        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    private void prepareRecyclerView() {
        recyclerView = binding.recyclerView;

        AlertDialogAdapter myAdapter = new AlertDialogAdapter(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<AlertDialogModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new AlertDialogModel(item));
        }
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewItemClick(int type) {
        switch (AlertDialogType.getType(type)) {
            case SIMPLE_ALERT_DIALOG:
                showSimpleAlertDialog();
                break;
            case SINGLE_CHOICE_ALERT_DIALOG:
                showSingleChoiceAlertDialog();
                break;
            case MULTIPLE_CHOICE_ALERT_DIALOG:
                showMultipleChoiceAlertDialog();
                break;
            case CUSTOM_ALERT_DIALOG:
                showCustomAlertDialog();
                break;
        }
    }

    private void showCustomAlertDialog() {
        AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_layout, null);
        builder.setView(dialogView);

        dialog = builder.create();
        dialog.show();

        Button button = dialogView.findViewById(R.id.button);
        AlertDialog finalDialog = dialog;
        button.setOnClickListener(v -> finalDialog.dismiss());
    }

    private void showMultipleChoiceAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Title");

        selectedMultiChoiceItems = Arrays.asList(multiple_choice_dialog_menu);

        builder.setMultiChoiceItems(single_choice_dialog_menu, initialStateOfMultiChoiceItems,
                (dialogInterface, which, isChecked) -> {
                    initialStateOfMultiChoiceItems[which] = isChecked;
                    String currentItem = selectedMultiChoiceItems.get(which);

                    Toast.makeText(getContext(), currentItem,
                            Toast.LENGTH_SHORT).show();
                });

        builder.setPositiveButton("Ok",
                (dialog, which) -> {
                    for (int i = 0; i < selectedMultiChoiceItems.size(); i++) {
                        if (initialStateOfMultiChoiceItems[i]) {
                            Toast.makeText(getContext(), "Selected : " + selectedMultiChoiceItems.get(i), Toast.LENGTH_SHORT).show();
                        }
                    }
                    dialog.dismiss();
                });

        builder.setNegativeButton("Cancel",
                (dialog, which) -> {
                    dialog.cancel();
                    Toast.makeText(getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSingleChoiceAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Title");

        builder.setSingleChoiceItems(single_choice_dialog_menu, 0,
                (dialog, which) -> Toast.makeText(getContext(), single_choice_dialog_menu[which], Toast.LENGTH_SHORT).show());

        builder.setPositiveButton("Ok",
                (dialog, which) -> {
                    Toast.makeText(getContext(), "Ok.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });

        builder.setNegativeButton("Cancel",
                (dialog, which) -> {
                    dialog.cancel();
                    Toast.makeText(getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showSimpleAlertDialog() {
        builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Title");
        builder.setMessage("This is message");

        builder.setPositiveButton("Ok",
                (dialog, id) -> {
                    Toast.makeText(getContext(), "Ok.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                });

        builder.setNegativeButton("Cancel",
                (dialog, id) -> {
                    dialog.cancel();
                    Toast.makeText(getContext(), "Cancelled.", Toast.LENGTH_SHORT).show();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public enum AlertDialogType {
        SIMPLE_ALERT_DIALOG,
        SINGLE_CHOICE_ALERT_DIALOG,
        MULTIPLE_CHOICE_ALERT_DIALOG,
        CUSTOM_ALERT_DIALOG;

        private static AlertDialogFragment.AlertDialogType[] list = AlertDialogFragment.AlertDialogType.values();

        public static AlertDialogFragment.AlertDialogType getType(int i) {
            return list[i];
        }
    }

}