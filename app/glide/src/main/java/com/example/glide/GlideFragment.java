package com.example.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.commonmodule.ToolbarManager;
import com.example.glide.databinding.FragmentGlideBinding;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlideFragment extends Fragment {

    FragmentGlideBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGlideBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnLoadImage.setOnClickListener(v -> loadImage());

        binding.btnLoadImageAsBitmap.setOnClickListener(v -> loadImageAsBitmap());

        binding.btnClearImageview.setOnClickListener(v -> clearImageview());

        return binding.getRoot();
    }

    private void clearImageview() {
        Glide.with(requireContext()).clear(binding.imageView);
    }

    private void loadImageAsBitmap() {
        FutureTarget<Bitmap> target = Glide.with(requireContext())
                .asBitmap()
                .load("https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                        "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                        "=format&fit=crop&w=750&q=80")
                .submit();

        //background task
        final Bitmap[] bitmap = {null};
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(() -> {
            try {
                bitmap[0] = target.get();

                handler.post(() -> binding.imageView.setImageBitmap(bitmap[0]));
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadImage() {
        binding.progressBar.setVisibility(View.VISIBLE);

        Glide.with(requireContext())
                .load("https://images.unsplash.com/photo-1607252650355-f7fd0460ccdb?ixlib" +
                        "=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto" +
                        "=format&fit=crop&w=750&q=80")
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .error(R.drawable.ic_baseline_error_24)
                .fitCenter()
                .centerCrop()
                .override(500, Target.SIZE_ORIGINAL)
                .transform(new CircleCrop())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e,
                                                Object model,
                                                Target<Drawable> target,
                                                boolean isFirstResource) {
                        Log.d("TAG", "Error loading image");
                        binding.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource,
                                                   Object model,
                                                   Target<Drawable> target,
                                                   DataSource dataSource,
                                                   boolean isFirstResource) {
                        binding.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(binding.imageView);
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