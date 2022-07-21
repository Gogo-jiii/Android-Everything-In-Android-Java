package com.example.interfacecallback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.interfacecallback.databinding.SecondFragmentBinding;

public class SecondFragment extends Fragment {

    SecondFragmentBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private static SomeInterface someInterface;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = SecondFragmentBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnGoback.setOnClickListener(v -> {
            someInterface.callbackFromFragmentTwo();
            navController.popBackStack();
        });
        return binding.getRoot();
    }

    public static void setInstance(Fragment context) {
        someInterface = (SomeInterface) context;
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

    public interface SomeInterface {
        void callbackFromFragmentTwo();
    }
}