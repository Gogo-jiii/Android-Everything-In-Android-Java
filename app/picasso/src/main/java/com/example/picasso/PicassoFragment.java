package com.example.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.picasso.databinding.FragmentPicassoBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PicassoFragment extends Fragment {

    FragmentPicassoBinding binding;
    private NavController navController;
    private ToolbarManager toolbarManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPicassoBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnLoadImage.setOnClickListener(v -> loadImage());


        binding.btnLoadImageAsBitmap.setOnClickListener(v -> loadImageAsBitmap());

        binding.btnClearImageview.setOnClickListener(v -> binding.imageView.setImageDrawable(null));

        return binding.getRoot();
    }

    private void loadImageAsBitmap() {
        Target target = new Target() {
            @Override public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                binding.imageView.setImageBitmap(bitmap);
            }

            @Override public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                binding.imageView.setImageDrawable(errorDrawable);
            }

            @Override public void onPrepareLoad(Drawable placeHolderDrawable) {
                binding.imageView.setImageDrawable(placeHolderDrawable);
            }
        };

        Picasso
                .get()
                .load("https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                        "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                        "=format&fit=crop&w=750&q=80")
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_error_24)
                .tag("tag")
                .into(target);
    }

    private void loadImage() {
        binding.progressBar.setVisibility(View.VISIBLE);

        Picasso
                .get()
                .load("https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                        "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                        "=format&fit=crop&w=750&q=80")
                .resize(200, 200)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_error_24)
                .tag("tag")
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_STORE)
                .noFade()
                .into(binding.imageView, new Callback() {
                    @Override public void onSuccess() {
                        binding.progressBar.setVisibility(View.GONE);
                    }

                    @Override public void onError(Exception e) {
                        binding.progressBar.setVisibility(View.GONE);
                    }
                });
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