package com.example.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonmodule.ToolbarManager;
import com.example.dashboard.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.Objects;


public class DashboardFragment extends Fragment {

    FragmentDashboardBinding binding;
    ArrayList<DashboardModel> list = new ArrayList<>();
    String[] data = new String[]{"Logs", "Toast"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();
        prepareRecyclerView();
        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                false);
    }

    private void prepareRecyclerView() {
        recyclerView = binding.recyclerView;

        DashboardAdapter myAdapter = new DashboardAdapter(getData(), getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<DashboardModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new DashboardModel(item));
        }
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}