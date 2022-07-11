package com.example.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonmodule.ToolbarManager;
import com.example.snackbar.databinding.CustomSnackbarLayoutBinding;
import com.example.snackbar.databinding.FragmentSnackbarBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class SnackbarFragment extends Fragment implements SnackbarAdapter.OnRecyclerviewItemClickListener {

    FragmentSnackbarBinding binding;
    ArrayList<SnackbarModel> list = new ArrayList<>();
    String[] data = new String[]{"Normal Snackbar", "Custom Snackbar"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSnackbarBinding.inflate(inflater, container, false);
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

        SnackbarAdapter myAdapter = new SnackbarAdapter(getData(), getActivity(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<SnackbarModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new SnackbarModel(item));
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
        switch (SnackbarType.getType(type)) {
            case NORMAL_SNACKBAR:
                showNormalSnackbar();
                break;
            case CUSTOM_SNACKBAR:
                showCustomSnackbar();
                break;
        }
    }

    private void showNormalSnackbar() {
        Snackbar snackbar = Snackbar.make(binding.getRoot(), "Snackbar",
                Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("OK", view -> Toast.makeText(getContext(), "Clicked.",
                Toast.LENGTH_SHORT).show());
        snackbar.setActionTextColor(getResources().getColor(R.color.white));
        snackbar.setTextColor(getResources().getColor(R.color.white));
        snackbar.setBackgroundTint(getResources().getColor(R.color.purple_500));
        snackbar.show();
    }

    private void showCustomSnackbar() {
        @NonNull CustomSnackbarLayoutBinding view = CustomSnackbarLayoutBinding.inflate(LayoutInflater.from(getContext()), binding.getRoot(), false);
        ConstraintLayout root = view.getRoot();

        View customView = getLayoutInflater().inflate(R.layout.custom_snackbar_layout, root);

        Snackbar snackbar = Snackbar.make(binding.getRoot(), "", Snackbar.LENGTH_INDEFINITE);
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();

        Button button = customView.findViewById(R.id.button);
        button.setOnClickListener(view1 -> {
            Toast.makeText(getContext(), "Dismiss.", Toast.LENGTH_SHORT).show();
            snackbar.dismiss();
        });

        snackbarLayout.addView(customView, 0);
        snackbar.show();
    }

    public enum SnackbarType {
        NORMAL_SNACKBAR,
        CUSTOM_SNACKBAR;

        private static final SnackbarType[] list = SnackbarType.values();

        public static SnackbarType getType(int i) {
            return list[i];
        }
    }

}