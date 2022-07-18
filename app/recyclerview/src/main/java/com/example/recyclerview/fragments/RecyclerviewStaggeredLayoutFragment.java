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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.commonmodule.ToolbarManager;
import com.example.recyclerview.R;
import com.example.recyclerview.adapters.RecyclerviewAdapterStaggeredLayout;
import com.example.recyclerview.databinding.FragmentRecyclerviewStaggeredLayoutBinding;
import com.example.recyclerview.models.RecyclerviewModelStaggeredLayout;

import java.util.ArrayList;

public class RecyclerviewStaggeredLayoutFragment extends Fragment implements RecyclerviewAdapterStaggeredLayout.OnRecyclerviewItemClickListener{

    FragmentRecyclerviewStaggeredLayoutBinding binding;
    ArrayList<RecyclerviewModelStaggeredLayout> list = new ArrayList<>();
    String[] data = new String[]{"a\na\na", "b", "c", "d", "e\ne\ne", "f", "g", "h\nh\nh", "i", "j\nj\nj", "k", "l", "m", "n", "o", "p\np\np", "q", "r\nr\nr", "s", "t", "u", "v", "w", "x", "y", "z\nz\nz"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private RecyclerviewAdapterStaggeredLayout myAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewStaggeredLayoutBinding.inflate(inflater, container, false);
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

        myAdapter = new RecyclerviewAdapterStaggeredLayout(getData(), getActivity(), this);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);

    }

    private ArrayList<RecyclerviewModelStaggeredLayout> getData() {
        list.clear();

        for (String item : data) {
            list.add(new RecyclerviewModelStaggeredLayout(item));
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