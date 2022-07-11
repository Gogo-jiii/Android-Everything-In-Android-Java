package com.example.toast;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.toast.databinding.CustomToastLayoutBinding;
import com.example.toast.databinding.FragmentToastBinding;

import java.util.ArrayList;


public class ToastFragment extends Fragment implements ToastAdapter.OnRecyclerviewItemClickListener {

    FragmentToastBinding binding;
    ArrayList<ToastModel> list = new ArrayList<>();
    String[] data = new String[]{"Show Toast", "Custom Toast"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentToastBinding.inflate(inflater, container, false);
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

        ToastAdapter myAdapter = new ToastAdapter(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<ToastModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new ToastModel(item));
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
        switch (ToastType.getType(type)) {
            case NORMAL_TOAST:
                showNormaltoast();
                break;
            case CUSTOM_TOAST:
                showCustomToast();
                break;
        }
    }

    private void showCustomToast() {
        @NonNull CustomToastLayoutBinding view = CustomToastLayoutBinding.inflate(LayoutInflater.from(getContext()), binding.getRoot(), false);
        ConstraintLayout root = view.getRoot();

        View customView = getLayoutInflater().inflate(R.layout.custom_toast_layout, root);

        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(customView);
        toast.show();
    }

    private void showNormaltoast() {
        Toast.makeText(getContext(), "This is a normal toast...", Toast.LENGTH_SHORT).show();
    }

    public enum ToastType {
        NORMAL_TOAST,
        CUSTOM_TOAST;

        private static final ToastType[] list = ToastType.values();

        public static ToastType getType(int i) {
            return list[i];
        }
    }

}