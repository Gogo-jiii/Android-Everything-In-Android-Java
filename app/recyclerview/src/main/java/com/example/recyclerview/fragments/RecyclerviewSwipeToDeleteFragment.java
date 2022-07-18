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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonmodule.ToolbarManager;
import com.example.recyclerview.R;
import com.example.recyclerview.adapters.RecyclerviewAdapterSwipeToDelete;
import com.example.recyclerview.databinding.FragmentRecyclerviewSwipeToDeleteBinding;
import com.example.recyclerview.models.RecyclerviewModelSwipeToDelete;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class RecyclerviewSwipeToDeleteFragment extends Fragment implements RecyclerviewAdapterSwipeToDelete.OnRecyclerviewItemClickListener{

    FragmentRecyclerviewSwipeToDeleteBinding binding;
    ArrayList<RecyclerviewModelSwipeToDelete> list = new ArrayList<>();
    String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private RecyclerviewAdapterSwipeToDelete myAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewSwipeToDeleteBinding.inflate(inflater, container, false);
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

        myAdapter = new RecyclerviewAdapterSwipeToDelete(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

        setSwipeToDelete();
    }

    private void setSwipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                RecyclerviewModelSwipeToDelete modelClass = list.get(viewHolder.getAdapterPosition());

                int position = viewHolder.getAdapterPosition();

                list.remove(viewHolder.getAdapterPosition());

                myAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Snackbar.make(recyclerView, modelClass.getItemName(), Snackbar.LENGTH_LONG).setAction("Undo", v -> {
                    list.add(position, modelClass);

                    myAdapter.notifyItemInserted(position);
                }).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private ArrayList<RecyclerviewModelSwipeToDelete> getData() {
        list.clear();

        for (String item : data) {
            list.add(new RecyclerviewModelSwipeToDelete(item));
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