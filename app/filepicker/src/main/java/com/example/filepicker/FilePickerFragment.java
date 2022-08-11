package com.example.filepicker;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.filepicker.databinding.FragmentFilePickerBinding;

import java.io.File;

public class FilePickerFragment extends Fragment {

    FragmentFilePickerBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilePickerBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnFilePicker.setOnClickListener(v -> showFileChooser());

        return binding.getRoot();
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 100);
        } catch (Exception exception) {
            Toast.makeText(requireContext(), "Please install a file manager.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override public void onActivityResult(int requestCode, int resultCode,
                                              @Nullable @org.jetbrains.annotations.Nullable Intent data) {

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            String path = uri.getPath();
            File file = new File(path);

           binding.txtResult.setText(new StringBuilder().append("Path: ").append(path).append("\n").append("\n").append("File name: ").append(file.getName()).toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.filepickerToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}