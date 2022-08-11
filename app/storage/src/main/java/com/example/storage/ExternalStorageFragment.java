package com.example.storage;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.storage.databinding.FragmentExternalStorageBinding;

import java.io.File;

public class ExternalStorageFragment extends Fragment {

    FragmentExternalStorageBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private ExternalStorageManager externalStorageManager = Storage.getInstance().getExternalStorage();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExternalStorageBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnCreateFile.setOnClickListener(v -> createFile());

        binding.btnRead.setOnClickListener(v -> readFile());

        binding.btnWrite.setOnClickListener(v -> writeFile());

        binding.btnCreateCacheFile.setOnClickListener(v -> createCacheFile());

        return binding.getRoot();
    }

    private void createCacheFile() {
        boolean isFileCreated3 = externalStorageManager.createCacheFile(requireContext(),
                "CacheFile");
        if (isFileCreated3) {
            Toast.makeText(requireContext(), "File created.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

        //write something
        File file3 = externalStorageManager.getCacheFile(requireContext(), "CacheFile");
        externalStorageManager.write(file3, "Cache Text.");

        //read it
        StringBuilder data3 = externalStorageManager.read(file3);
        Toast.makeText(requireContext(), data3.toString(), Toast.LENGTH_SHORT).show();
    }

    private void writeFile() {
        File file = externalStorageManager.getFile(requireContext(), "two");
        Storage.getInstance().getExternalStorage().write(file,
                "some text...");
        Toast.makeText(requireContext(), "File written.", Toast.LENGTH_SHORT).show();
    }

    private void readFile() {
        File file1 = Storage.getInstance().getExternalStorage().getFile(requireContext(), "two");
        StringBuilder data = Storage.getInstance().getExternalStorage().read(file1);
        Toast.makeText(requireContext(), data.toString(), Toast.LENGTH_SHORT).show();
    }

    private void createFile() {
        boolean isFileCreated = externalStorageManager.createFile(requireContext(), "two");
        if (isFileCreated) {
            Toast.makeText(requireContext(), "File created.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
        }

        Log.d("TAG", String.valueOf(externalStorageManager.getPrimaryExternalLocation(requireContext())));
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.externalStorageToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}