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
import com.example.recyclerview.adapters.RecyclerviewAdapterPagination;
import com.example.recyclerview.databinding.FragmentRecyclerviewPaginationBinding;
import com.example.recyclerview.models.RecyclerviewModelPagination;

import java.util.ArrayList;

public class RecyclerviewPaginationFragment extends Fragment implements RecyclerviewAdapterPagination.OnRecyclerviewItemClickListener {

    FragmentRecyclerviewPaginationBinding binding;
    ArrayList<RecyclerviewModelPagination> list = new ArrayList<>();
    String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    String[] newData = new String[]{"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2", "i2"};
    private boolean loading = true;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private RecyclerviewAdapterPagination myAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewPaginationBinding.inflate(inflater, container, false);
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

        myAdapter = new RecyclerviewAdapterPagination(getData(), getActivity(), this);
        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        setupPagination();
    }

    private void setupPagination() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {

                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Toast.makeText(getContext(), "This is the last item!", Toast.LENGTH_SHORT).show();

                            getNewData();
                            myAdapter.notifyDataSetChanged();

                            loading = true;
                        }
                    }
                }
            }
        });
    }

    private ArrayList<RecyclerviewModelPagination> getData() {
        list.clear();

        for (String item : data) {
            list.add(new RecyclerviewModelPagination(item));
        }
        return list;
    }

    private ArrayList<RecyclerviewModelPagination> getNewData() {
        for (String item : newData) {
            list.add(new RecyclerviewModelPagination(item));
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
        Toast.makeText(getContext(), "Data: " + list.get(index), Toast.LENGTH_SHORT).show();
    }
}