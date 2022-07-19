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
import com.example.recyclerview.adapters.RecyclerviewAdapterViewType;
import com.example.recyclerview.databinding.FragmentRecyclerviewViewTypeBinding;
import com.example.recyclerview.models.RecyclerviewModelViewType;

import java.util.ArrayList;

public class RecyclerviewViewTypeFragment extends Fragment implements RecyclerviewAdapterViewType.OnRecyclerviewItemClickListener{

    FragmentRecyclerviewViewTypeBinding binding;
    ArrayList<RecyclerviewModelViewType> list = new ArrayList<>();
    String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private RecyclerviewAdapterViewType myAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewViewTypeBinding.inflate(inflater, container, false);
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

        myAdapter = new RecyclerviewAdapterViewType(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }


    private ArrayList<RecyclerviewModelViewType> getData() {
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[0], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[1], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[2], R.drawable.ic_baseline_android_24));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[3], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[4], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[5], R.drawable.ic_baseline_android_24));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[6], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[7], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[8], 0));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[9], R.drawable.ic_baseline_android_24));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[10], R.drawable.ic_baseline_android_24));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[11], 0));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[12], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[13], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[14], 0));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[15], R.drawable.ic_baseline_android_24));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[16], R.drawable.ic_baseline_android_24));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[17], 0));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[18], R.drawable.ic_baseline_android_24));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[19], R.drawable.ic_baseline_android_24));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[20], 0));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[21], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_TEXT, data[22], 0));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[23], R.drawable.ic_baseline_android_24));

        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[24], R.drawable.ic_baseline_android_24));
        list.add(new RecyclerviewModelViewType(RecyclerviewModelViewType.TYPE_IMAGE, data[25], R.drawable.ic_baseline_android_24));

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