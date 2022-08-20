package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.broadcastreceiver.databinding.FragmentBroadcastReceiverBinding;
import com.example.commonmodule.ToolbarManager;

public class BroadcastReceiverFragment extends Fragment {

    FragmentBroadcastReceiverBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private IntentFilter implicitIntentFilter, localBroadcastIntentFilter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBroadcastReceiverBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        implicitIntentFilter = new IntentFilter();
        implicitIntentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);

        localBroadcastIntentFilter = new IntentFilter();
        localBroadcastIntentFilter.addAction("local_broadcast");

        binding.btnSendLocalBroadcast.setOnClickListener(v -> {
            Intent intent = new Intent("local_broadcast");
            intent.putExtra("key", "IT wala");
            LocalBroadcastManager.getInstance(requireActivity()).sendBroadcast(intent);
        });


        return binding.getRoot();
    }

    @Override public void onResume() {
        super.onResume();
        requireActivity().registerReceiver(implicitBroadCastReceiver, implicitIntentFilter);
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(localBroadCastReceiver,
                localBroadcastIntentFilter);
    }

    @Override public void onStop() {
        requireActivity().unregisterReceiver(implicitBroadCastReceiver);
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(localBroadCastReceiver);
        super.onStop();
    }

    BroadcastReceiver implicitBroadCastReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            String log = "Action: " + intent.getAction() + "\n" +
                    "URI: " + intent.toUri(Intent.URI_INTENT_SCHEME) + "\n";
            Log.d("TAG", log);
            Toast.makeText(context, log, Toast.LENGTH_LONG).show();
            binding.txtResult.setText(log);
        }
    };

    BroadcastReceiver localBroadCastReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            String name = intent.getStringExtra("key");
            Log.d("TAG", name);
            Toast.makeText(context, name, Toast.LENGTH_LONG).show();
            binding.txtResult.setText(name);
        }
    };

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.broadcastreceiverToolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}