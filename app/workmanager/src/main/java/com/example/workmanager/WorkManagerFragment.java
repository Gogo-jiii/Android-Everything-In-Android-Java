package com.example.workmanager;

import android.content.Context;
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
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import com.example.commonmodule.ToolbarManager;
import com.example.workmanager.databinding.FragmentWorkManagerBinding;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class WorkManagerFragment extends Fragment {

    FragmentWorkManagerBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private OneTimeWorkRequest oneTimeWorkRequest;
    private PeriodicWorkRequest periodicWorkRequest;
    private WorkRequest foregroundWorkRequest;
    private Context context;
    private Constraints constraints;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWorkManagerBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        this.context = requireContext();

        binding.btnStartOneTimeWork.setOnClickListener(v -> {
            Log.d("TAG", "Starting the work!");
            Toast.makeText(context, "Starting one time work!", Toast.LENGTH_SHORT).show();

            Data data = new Data.Builder().putString("key", "IT wala").build();

            constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
//                .setRequiresCharging(false)
//                .setRequiresStorageNotLow(true)
//                .setRequiresBatteryNotLow(true)

            oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyOneTimeWork.class)
                    .addTag("onetimework" + System.currentTimeMillis())
                    .setConstraints(constraints)
                    .setInputData(data)
                    .setInitialDelay(1, TimeUnit.SECONDS)
                    .setBackoffCriteria(BackoffPolicy.LINEAR,
                            OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.SECONDS)
                    .build();
            WorkManager.getInstance(context).enqueue(oneTimeWorkRequest);
        });

        binding.btnStopOneTimeWork.setOnClickListener(v -> {
            Operation operation =
                    WorkManager.getInstance(context).cancelWorkById(oneTimeWorkRequest.getId());
            MyOneTimeWork.isStopped = true;
            Log.d("TAG", "Stopping one time work!");
            Toast.makeText(context, "Stopping one time work!" + operation.getState(),
                    Toast.LENGTH_SHORT).show();
        });

        binding.btnGetOneTimeWorkUpdates.setOnClickListener(v -> {
            try {
                WorkInfo workInfo =
                        WorkManager.getInstance(context).getWorkInfoById(oneTimeWorkRequest.getId())
                                .get();

                Log.d("TAG", workInfo.toString());
                String updates =
                        "\nState: " + workInfo.getState() + "\nProgress: " + workInfo.getProgress()
                                + "\nOutputData: " + workInfo.getOutputData();
                binding.txtOneTimeWorkResult.setText("One time work updates: " + updates);

            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        binding.btnStartPeriodicWork.setOnClickListener(v -> {
            Log.d("TAG", "Starting periodic work!");
            Toast.makeText(context, "Starting periodic work!", Toast.LENGTH_SHORT).show();

            Data data = new Data.Builder().putString("key", "IT wala periodic work").build();

            periodicWorkRequest = new PeriodicWorkRequest.Builder(MyPeriodicWork.class, 15,
                    TimeUnit.MINUTES)
                    .addTag("periodicwork" + System.currentTimeMillis())
                    .setBackoffCriteria(BackoffPolicy.LINEAR,
                            PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                    .setInputData(data)
                    .build();

            WorkManager.getInstance(context).enqueue(periodicWorkRequest);
        });

        binding.btnGetPeriodicWorkUpdates.setOnClickListener(v -> {
            try {
                WorkInfo workInfo =
                        WorkManager.getInstance(context).getWorkInfoById(periodicWorkRequest.getId())
                                .get();

                Log.d("TAG", workInfo.toString());
                String updates =
                        "\nState: " + workInfo.getState() + "\nProgress: " + workInfo.getProgress()
                                + "\nOutputData: " + workInfo.getOutputData();
                binding.txtPeriodicWorkResult.setText("Periodic work updates: " + updates);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        binding.btnStopPeriodicWork.setOnClickListener(v -> {
            Operation operation =
                    WorkManager.getInstance(context).cancelWorkById(periodicWorkRequest.getId());
            MyPeriodicWork.isStopped = true;
            Log.d("TAG", "Stopping periodic work!");
            Toast.makeText(context, "Stopping periodic work!" + operation.getState(),
                    Toast.LENGTH_SHORT).show();
        });

        binding.btnStartForegroundWork.setOnClickListener(v -> {
            Toast.makeText(context, "Starting foreground work!", Toast.LENGTH_SHORT).show();
            foregroundWorkRequest = new OneTimeWorkRequest.Builder(MyForegroundWork.class)
                    .addTag("foregroundwork" + System.currentTimeMillis())
                    .setBackoffCriteria(BackoffPolicy.LINEAR,
                            OneTimeWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.SECONDS)
                    .build();
            WorkManager.getInstance(context).enqueue(foregroundWorkRequest);
        });

        binding.btnStopForegroundWork.setOnClickListener(v -> {
            MyForegroundWork.isStopped = true;
            Operation operation =
                    WorkManager.getInstance(context).cancelWorkById(foregroundWorkRequest.getId());
            Log.d("TAG", "Stopping foreground time work!");
            Toast.makeText(context, "Stopping foreground work!" + operation.getState(),
                    Toast.LENGTH_SHORT).show();
        });

        binding.btnGetForegroundWorkUpdates.setOnClickListener(v -> {
            WorkInfo workInfo;
            try {
                workInfo =
                        WorkManager.getInstance(context).getWorkInfoById(foregroundWorkRequest.getId()).get();
                Log.d("TAG", workInfo.toString());
                String updates =
                        "\nState: " + workInfo.getState() + "\nProgress: " + workInfo.getProgress()
                                + "\nOutputData: " + workInfo.getOutputData();
                binding.txtForegroundWorkResult.setText("Periodic work updates: " + updates);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.workmanagerToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}