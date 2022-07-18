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
    String[] data = new String[]{"Simple Recycler View", "Animation", "Single Item Selection", "Multiple Items Selection",
    "Swipe To Delete Item", "Swipe To Delete Item With Icon", "Drag Drop Item", "Grid Layout", "Staggered Layout"};
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
            case RECYCLERVIEW_SWIPE_TO_DELETE_ITEM:
                navController.navigate(R.id.action_recyclerviewFragment_to_recyclerviewSwipeToDeleteFragment);
                break;
            case RECYCLERVIEW_SWIPE_TO_DELETE_ITEM_WITH_ICON:
                navController.navigate(R.id.action_recyclerviewFragment_to_recyclerviewSwipeToDeleteIconFragment);
                break;
            case RECYCLERVIEW_DRAG_DROP_ITEM:
                navController.navigate(R.id.action_recyclerviewFragment_to_recyclerviewDragDropItemFragment);
                break;
            case RECYCLERVIEW_GRID_LAYOUT:
                navController.navigate(R.id.action_recyclerviewFragment_to_recyclerviewGridLayoutFragment);
                break;
            case RECYCLERVIEW_STAGGERED_LAYOUT:
                navController.navigate(R.id.action_recyclerviewFragment_to_recyclerviewStaggeredLayoutFragment);
                break;
        }
    }

    public enum DashboardType {
        NORMAL_RECYCLER_VIEW,
        RECYCLER_VIEW_ANIMATION,
        RECYCLER_VIEW_SINGLE_ITEM_SELECTION,
        RECYCLER_VIEW_MULTIPLE_ITEM_SELECTION,
        RECYCLERVIEW_SWIPE_TO_DELETE_ITEM,
        RECYCLERVIEW_SWIPE_TO_DELETE_ITEM_WITH_ICON,
        RECYCLERVIEW_DRAG_DROP_ITEM,
        RECYCLERVIEW_GRID_LAYOUT,
        RECYCLERVIEW_STAGGERED_LAYOUT;

        private static RecyclerviewFragment.DashboardType[] list = RecyclerviewFragment.DashboardType.values();

        public static RecyclerviewFragment.DashboardType getType(int i) {
            return list[i];
        }
    }

}