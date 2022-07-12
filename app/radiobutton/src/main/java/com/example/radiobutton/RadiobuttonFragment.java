package com.example.radiobutton;

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
import com.example.radiobutton.databinding.FragmentRadiobuttonBinding;


public class RadiobuttonFragment extends Fragment implements View.OnClickListener {

    FragmentRadiobuttonBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRadiobuttonBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        binding.radioButtonA.setOnClickListener(this);
        binding.radioButtonB.setOnClickListener(this);
        binding.radioButtonC.setOnClickListener(this);
        binding.radioButtonD.setOnClickListener(this);

        setupToolbar();
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
    public void onClick(View view) {

        if (view == binding.radioButtonA) {
            Toast.makeText(getContext(), "A checked.",
                    Toast.LENGTH_SHORT).show();
        } else if (view == binding.radioButtonB) {
            Toast.makeText(getContext(), "B checked.",
                    Toast.LENGTH_SHORT).show();
        } else if (view == binding.radioButtonC) {
            Toast.makeText(getContext(), "C checked.",
                    Toast.LENGTH_SHORT).show();
        } else if (view == binding.radioButtonD) {
            Toast.makeText(getContext(), "D checked.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}