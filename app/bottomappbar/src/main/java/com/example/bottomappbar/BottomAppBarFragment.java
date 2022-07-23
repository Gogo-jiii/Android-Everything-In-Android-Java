package com.example.bottomappbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.bottomappbar.databinding.FragmentBottomAppBarBinding;
import com.example.commonmodule.ToolbarManager;


public class BottomAppBarFragment extends Fragment {

    FragmentBottomAppBarBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBottomAppBarBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.bottomAppBar.setOnMenuItemClickListener(item -> {
            int itemID = item.getItemId();
            if(itemID == R.id.page_1){
                Toast.makeText(getContext(), "1st item clicked.", Toast.LENGTH_SHORT).show();
            }else if(itemID == R.id.page_2){
                Toast.makeText(getContext(), "2nd item clicked.", Toast.LENGTH_SHORT).show();
            }
            return false;
        });

        binding.bottomAppBar.setNavigationOnClickListener(v -> Toast.makeText(getContext(), "Navigation icon", Toast.LENGTH_SHORT).show());

        binding.floatingActionButton.setOnClickListener(v -> Toast.makeText(getContext(), "Floating Acton Button", Toast.LENGTH_SHORT).show());

        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar8,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}