package com.example.recyclerview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonmodule.ToolbarManager;
import com.example.recyclerview.R;
import com.example.recyclerview.adapters.RecyclerviewAdapterSwipeToRefresh;
import com.example.recyclerview.databinding.FragmentRecyclerviewSwipeToRefreshBinding;
import com.example.recyclerview.models.RecyclerviewModelSwipeToRefresh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RecyclerviewSwipeToRefreshFragment extends Fragment implements RecyclerviewAdapterSwipeToRefresh.OnRecyclerviewItemClickListener {

    FragmentRecyclerviewSwipeToRefreshBinding binding;
    ArrayList<RecyclerviewModelSwipeToRefresh> list = new ArrayList<>();
    String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private RecyclerviewAdapterSwipeToRefresh myAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewSwipeToRefreshBinding.inflate(inflater, container, false);
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

        myAdapter = new RecyclerviewAdapterSwipeToRefresh(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        swipeToRefresh();
    }

    private void swipeToRefresh() {
        binding.swipeToRefreshLayout.setOnRefreshListener(() -> {
            binding.swipeToRefreshLayout.setRefreshing(false);
            resetRecyclerview();
        });
    }

    private void resetRecyclerview() {
        //do some work...
        Toast.makeText(getContext(), "Refresh...", Toast.LENGTH_SHORT).show();
        Collections.shuffle(list, new Random(System.currentTimeMillis()));
        myAdapter = new RecyclerviewAdapterSwipeToRefresh(list, getActivity(), this);
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<RecyclerviewModelSwipeToRefresh> getData() {
        list.clear();

        for (String item : data) {
            list.add(new RecyclerviewModelSwipeToRefresh(item));
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewItemClick(int index) {
        Toast.makeText(getContext(), "Data: " + data[index], Toast.LENGTH_SHORT).show();
    }
}