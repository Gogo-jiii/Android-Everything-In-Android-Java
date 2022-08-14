package com.example.room;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.room.databinding.FragmentRoomBinding;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RoomFragment extends Fragment implements View.OnClickListener {

    FragmentRoomBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private DatabaseManager database;
    private ExecutorService executorService;
    private Handler handler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRoomBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnInsertData.setOnClickListener(this);
        binding.btnRetrieveData.setOnClickListener(this);
        binding.btnUpdateData.setOnClickListener(this);
        binding.btnDeleteData.setOnClickListener(this);

        database = DatabaseManager.getInstance(requireContext());
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

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
        int id = v.getId();
        if (id == R.id.btnInsertData) {
            insert();
        } else if (id == R.id.btnRetrieveData) {
            retrieve();
        } else if (id == R.id.btnUpdateData) {
            update();
        } else if (id == R.id.btnDeleteData) {
            delete();
        }
    }

    private void delete() {
        final boolean[] isDeleted = {false};

        executorService.execute(() -> {
            //task
            UserModel userToBeDeleted = null;
            String nameToBeDeleted = Objects.requireNonNull(binding.tilDataToBeDeleted.getEditText()).getText().toString();

            for (int i = 0; i < database.userDao().getAllUsers().size(); i++) {
                if (database.userDao().getAllUsers().get(i).getName().equals(nameToBeDeleted)) {
                    userToBeDeleted = database.userDao().getAllUsers().get(i);
                    break;
                }
            }

            if (userToBeDeleted != null) {
                database.userDao().deleteUser(userToBeDeleted);
                isDeleted[0] = true;
            }

            handler.post(() -> {
                //update ui
                if (isDeleted[0]) {
                    Toast.makeText(requireActivity(), "Data deleted.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireActivity(), "Operation failed!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void update() {
        final boolean[] isUpdated = {false};

        executorService.execute(() -> {
            //task
            String oldName = Objects.requireNonNull(binding.tilDataToBeUpdated.getEditText()).getText().toString();
            String newName = Objects.requireNonNull(binding.tilUpdatedData.getEditText()).getText().toString();
            int id = -1;

            for (int i = 0; i < database.userDao().getAllUsers().size(); i++) {
                if (database.userDao().getAllUsers().get(i).getName().equals(oldName)) {
                    id = database.userDao().getAllUsers().get(i).getId();
                    break;
                }
            }

            UserModel userModel = database.userDao().getUser(id);
            if (userModel != null) {
                userModel.setName(newName);
                database.userDao().updateUser(userModel);
                isUpdated[0] = true;
            }

            handler.post(() -> {
                //update ui
                if (isUpdated[0]) {
                    Toast.makeText(requireActivity(), "Data updated.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireActivity(), "Operation failed!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private void retrieve() {
        executorService.execute(() -> {
            //task
            List<UserModel> allUsers = database.userDao().getAllUsers();

            handler.post(() -> {
                //update ui
                Toast.makeText(requireActivity(), allUsers.toString(),
                        Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void insert() {
        final boolean[] isInserted = {false};

        executorService.execute(() -> {
            //task
            String name = Objects.requireNonNull(binding.tilInsert.getEditText()).getText().toString();
            database.userDao().insert(new UserModel(name));

            for (int i = 0; i < database.userDao().getAllUsers().size(); i++) {
                if (database.userDao().getAllUsers().get(i).getName().equals(name)) {
                    isInserted[0] = true;
                }
            }


            handler.post(() -> {
                if (isInserted[0]) {
                    Toast.makeText(requireActivity(), "Data inserted.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireActivity(), "Operation failed!",
                            Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}