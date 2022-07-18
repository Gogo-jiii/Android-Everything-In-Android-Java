package com.example.recyclerview;

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
import com.example.recyclerview.databinding.FragmentRecyclerviewBinding;

import java.util.ArrayList;


public class RecyclerviewFragment extends Fragment implements RecyclerviewAdapter.OnRecyclerviewItemClickListener{

    FragmentRecyclerviewBinding binding;
    ArrayList<RecyclerviewModel> list = new ArrayList<>();
    String[] data = new String[]{"Simple Recycler View", "Recyclerview Animation", "Recyclerview Single Item Selection", "Recyclerview Multiple Items Selection"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false);
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

        RecyclerviewAdapter myAdapter = new RecyclerviewAdapter(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<RecyclerviewModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new RecyclerviewModel(item));
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
        switch (DashboardType.getType(type)) {
            case NORMAL_RECYCLER_VIEW:
                navController.navigate(R.id.action_recyclerviewFragment_to_simpleRecyclerviewFragment);
                break;
            case RECYCLER_VIEW_ANIMATION:
                navController.navigate(R.id.action_recyclerviewFragment_to_recyclerviewAnimationFragment);
                break;
            case RECYCLER_VIEW_SINGLE_ITEM_SELECTION:
                navController.navigate(R.id.action_recyclerviewFragment_to_singleSelectionRecyclerviewFragment);
                break;
            case RECYCLER_VIEW_MULTIPLE_ITEM_SELECTION:
                navController.navigate(R.id.action_recyclerviewFragment_to_multipleItemsSelectionRecyclerviewFragment);
                break;
        }
    }

    public enum DashboardType {
        NORMAL_RECYCLER_VIEW,
        RECYCLER_VIEW_ANIMATION,
        RECYCLER_VIEW_SINGLE_ITEM_SELECTION,
        RECYCLER_VIEW_MULTIPLE_ITEM_SELECTION;

        private static RecyclerviewFragment.DashboardType[] list = RecyclerviewFragment.DashboardType.values();

        public static RecyclerviewFragment.DashboardType getType(int i) {
            return list[i];
        }
    }

}