package com.example.modalbottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.modalbottomsheet.databinding.FragmentModalBottomSheetBinding;

import java.util.ArrayList;

public class ModalBottomSheetFragment extends Fragment implements ModalBottomSheetDialogFragment.OnModalBottomSheetItemClickCallback {

    FragmentModalBottomSheetBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentModalBottomSheetBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        data.add("One");
        data.add("Two");
        data.add("Three");
        data.add("Four");
        data.add("Five");


        binding.btnShowModalBottomSheet.setOnClickListener(v -> {
            ModalBottomSheetDialogFragment fragment = new ModalBottomSheetDialogFragment(ModalBottomSheetFragment.this, data);
            fragment.show(getChildFragmentManager(), "Modal BottomSheet");
        });
        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onModalBottomSheetItemClicked(int index) {
        Toast.makeText(getContext(), "Item clicked: " + data.get(index), Toast.LENGTH_SHORT).show();
    }
}