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
import com.example.recyclerview.adapters.RecyclerviewAdapterNestedChild;
import com.example.recyclerview.adapters.RecyclerviewAdapterNestedParent;
import com.example.recyclerview.databinding.FragmentRecyclerviewNestedfragmentBinding;
import com.example.recyclerview.models.RecyclerviewModelNestedChild;
import com.example.recyclerview.models.RecyclerviewModelNestedParent;

import java.util.ArrayList;

public class RecyclerviewNestedfragment extends Fragment implements RecyclerviewAdapterNestedParent.OnRecyclerviewItemClickListener, RecyclerviewAdapterNestedChild.OnRecyclerviewItemClickListener {

    ArrayList<RecyclerviewModelNestedParent> arrayListParent = new ArrayList<>();
    ArrayList<ArrayList<RecyclerviewModelNestedChild>> arrayListChild = new ArrayList<>();
    ArrayList<RecyclerviewModelNestedChild> arrayListChildA = new ArrayList<>();
    ArrayList<RecyclerviewModelNestedChild> arrayListChildB = new ArrayList<>();
    ArrayList<RecyclerviewModelNestedChild> arrayListChildC = new ArrayList<>();

    String[] arrayParent = new String[]{"a", "b", "c"};
    String[] arrayChildA = new String[]{"a1", "a2", "a3"};
    String[] arrayChildB = new String[]{"b1", "b2", "b3"};
    String[] arrayChildC = new String[]{"c1", "c2", "c3"};

    FragmentRecyclerviewNestedfragmentBinding binding;
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewNestedfragmentBinding.inflate(inflater, container, false);
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

        RecyclerviewAdapterNestedParent myAdapter = new RecyclerviewAdapterNestedParent(getParentData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<RecyclerviewModelNestedParent> getParentData() {
        arrayListChild = getChildData();

        for (int i = 0; i < arrayParent.length; i++) {

            String parent = arrayParent[i];
            ArrayList<RecyclerviewModelNestedChild> child = arrayListChild.get(i);

            arrayListParent.add(new RecyclerviewModelNestedParent(parent, child, false));
        }

        return arrayListParent;
    }

    private ArrayList<ArrayList<RecyclerviewModelNestedChild>> getChildData() {
        for (String s : arrayChildA) {
            arrayListChildA.add(new RecyclerviewModelNestedChild(s));
        }

        for (String s : arrayChildB) {
            arrayListChildB.add(new RecyclerviewModelNestedChild(s));
        }

        for (String s : arrayChildC) {
            arrayListChildC.add(new RecyclerviewModelNestedChild(s));
        }

        arrayListChild.add(arrayListChildA);
        arrayListChild.add(arrayListChildB);
        arrayListChild.add(arrayListChildC);

        return arrayListChild;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewParentItemClick(int index) {
        Toast.makeText(getContext(), "Parent: " + arrayListParent.get(index).getParent(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecyclerviewChildItemClick(int parentPosition, int childPosition) {
        RecyclerviewModelNestedParent recyclerviewModelNestedParent = arrayListParent.get(parentPosition);
        ArrayList<RecyclerviewModelNestedChild> arrayListChild = recyclerviewModelNestedParent.getArrayListChild();
        RecyclerviewModelNestedChild recyclerviewModelNestedChild = arrayListChild.get(childPosition);

        Toast.makeText(getContext(), "Parent: " + recyclerviewModelNestedParent.getParent() + "_and_" + "Child: " + recyclerviewModelNestedChild.getChild(), Toast.LENGTH_SHORT).show();
    }
}