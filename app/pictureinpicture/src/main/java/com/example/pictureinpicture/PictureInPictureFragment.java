package com.example.pictureinpicture;

import android.app.PictureInPictureParams;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Rational;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.pictureinpicture.databinding.FragmentPictureInPictureBinding;

public class PictureInPictureFragment extends Fragment {

    FragmentPictureInPictureBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPictureInPictureBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_account_circle_24));

        binding.imageView.setOnClickListener(v -> Toast.makeText(requireContext(), "clicked.", Toast.LENGTH_SHORT).show());

        binding.btnPictureInPicture.setOnClickListener(v -> startPictureInPicture());

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.O) private void startPictureInPicture() {
        Display d = requireActivity().getWindowManager()
                .getDefaultDisplay();
        Point p = new Point();
        d.getSize(p);
        int width = p.x;
        int height = p.y;

        Rational ratio = new Rational(width, height);

        PictureInPictureParams.Builder builder = new PictureInPictureParams.Builder();
        builder.setAspectRatio(ratio);
        requireActivity().enterPictureInPictureMode(builder.build());
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        if(isInPictureInPictureMode){
            // Hide the full-screen UI (controls, etc.) while in picture-in-picture mode.
            Log.d("TAG","isInPictureInPictureMode");
        }else {
            // Restore the full-screen UI.
            Log.d("TAG","is NOT InPictureInPictureMode");
        }
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
}