package com.example.sqlite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.sqlite.databinding.FragmentSQLiteBinding;

import java.util.List;
import java.util.Objects;

public class SQLiteFragment extends Fragment implements View.OnClickListener {

    FragmentSQLiteBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private DatabaseManager databaseManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSQLiteBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        databaseManager = new DatabaseManager(requireContext(), DatabaseManager.DATABASE_NAME, null,
                DatabaseManager.VERSION);

        binding.btnInsertData.setOnClickListener(this);
        binding.btnRetrieveData.setOnClickListener(this);
        binding.btnUpdateData.setOnClickListener(this);
        binding.btnDeleteData.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        int rows;
        int id = -1;

        int vId = v.getId();
        if (vId == R.id.btnInsertData) {
            String nameToInsert = Objects.requireNonNull(binding.tilInsert.getEditText()).getText().toString();
            if (databaseManager.insert(nameToInsert)) {
                Toast.makeText(requireContext(), "Data inserted.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Operation Failed!", Toast.LENGTH_SHORT).show();
            }
        } else if (vId == R.id.btnRetrieveData) {
            List<UsersModel> users = databaseManager.getAllUsers();
            Toast.makeText(requireContext(), users.toString(), Toast.LENGTH_SHORT).show();
        } else if (vId == R.id.btnUpdateData) {
            String oldName = Objects.requireNonNull(binding.tilDataToBeUpdated.getEditText()).getText().toString();
            rows = databaseManager.getNumberOfRows();

            for (int i = 0; i < rows; i++) {
                if (oldName.equals(databaseManager.getAllUsers().get(i).getName())) {
                    id = databaseManager.getAllUsers().get(i).getID();
                    break;
                }
            }
            String newName = Objects.requireNonNull(binding.tilUpdatedData.getEditText()).getText().toString();

            if (id != -1) {
                if (databaseManager.update(id, newName)) {
                    Toast.makeText(requireContext(), "Data updated.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Operation Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (vId == R.id.btnDeleteData) {
            String nameToDelete = Objects.requireNonNull(binding.tilDataToBeDeleted.getEditText()).getText().toString();
            rows = databaseManager.getNumberOfRows();

            for (int i = 0; i < rows; i++) {
                if (nameToDelete.equals(databaseManager.getAllUsers().get(i).getName())) {
                    id = databaseManager.getAllUsers().get(i).getID();
                    break;
                }
            }

            if (id != -1) {
                if (databaseManager.delete(id) == 1) {
                    Toast.makeText(requireContext(), "Data deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Operation Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}