package com.example.recyclerview.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonmodule.ToolbarManager;
import com.example.recyclerview.R;
import com.example.recyclerview.adapters.RecyclerviewAdapterSearchFilter;
import com.example.recyclerview.databinding.FragmentRecyclerviewSearchFilterBinding;
import com.example.recyclerview.models.RecyclerviewModelSearchFilter;

import java.util.ArrayList;

public class RecyclerviewSearchFilterFragment extends Fragment implements RecyclerviewAdapterSearchFilter.OnRecyclerviewItemClickListener {

    FragmentRecyclerviewSearchFilterBinding binding;
    ArrayList<RecyclerviewModelSearchFilter> list = new ArrayList<>();
    String[] data = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private SearchView searchView;
    private RecyclerviewAdapterSearchFilter myAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerviewSearchFilterBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();
        prepareRecyclerView();
        onBackpressed();

        return binding.getRoot();
    }

    private void setupSearchview(Menu menu) {
        searchView = (SearchView) menu.findItem(R.id.searchview).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                myAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);

        binding.toolbar.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu);
                setupSearchview(menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
    }

    private void prepareRecyclerView() {
        recyclerView = binding.recyclerView;

        myAdapter = new RecyclerviewAdapterSearchFilter(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<RecyclerviewModelSearchFilter> getData() {
        list.clear();

        for (String item : data) {
            list.add(new RecyclerviewModelSearchFilter(item));
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewItemClick(int index, ArrayList<RecyclerviewModelSearchFilter> filteredData) {
        Toast.makeText(getContext(), "Data: " + filteredData.get(index).getItemName(), Toast.LENGTH_SHORT).show();
    }

    private void onBackpressed() {
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                    return;
                }
                requireActivity().onBackPressed();
            }
        });
    }
}