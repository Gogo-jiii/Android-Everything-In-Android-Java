package com.example.jobschedular;

import static android.content.Context.JOB_SCHEDULER_SERVICE;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.jobschedular.databinding.FragmentJobSchedularBinding;

public class JobSchedularFragment extends Fragment {

    FragmentJobSchedularBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentJobSchedularBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnScheduleJob.setOnClickListener(v -> {
            ComponentName componentName = new ComponentName(requireActivity(),
                    MyJobService.class);
            JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(1, componentName);
//                jobInfoBuilder.setRequiresCharging(true);
//                jobInfoBuilder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
//                jobInfoBuilder.setPeriodic(15 * 60 * 1000);
//                jobInfoBuilder.setPersisted(true);

            JobInfo jobInfo = jobInfoBuilder.build();

            JobScheduler jobScheduler = (JobScheduler) requireActivity().getSystemService(JOB_SCHEDULER_SERVICE);
            int resultCode = jobScheduler.schedule(jobInfo);
            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.d("TAG", "Job scheduled");
            } else {
                Log.d("TAG", "Job failed.");
            }
        });

        binding.btnCancelJob.setOnClickListener(v -> {
            JobScheduler jobScheduler = (JobScheduler) requireActivity().getSystemService(JOB_SCHEDULER_SERVICE);
            jobScheduler.cancel(1);
            Log.d("TAG", "Job Cancelled");
        });

        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.jobschedularToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("job");
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override public void onStop() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            String result = intent.getStringExtra("key");
            binding.txtResult.setText(result);
        }
    };
}