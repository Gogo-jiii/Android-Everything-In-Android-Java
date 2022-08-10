package com.example.retrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.retrofit.databinding.FragmentRetrofitBinding;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitFragment extends Fragment {

    FragmentRetrofitBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    RetrofitBuilder retrofitBuilder = RetrofitBuilder.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRetrofitBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnGetRequest.setOnClickListener(v -> getRequest());

        binding.btnPostRequest.setOnClickListener(v -> postRequest());

        return binding.getRoot();
    }

    private void postRequest() {
        binding.progressBar.setVisibility(View.VISIBLE);

        retrofitBuilder.build(retrofitBuilder.BASE_URL_POST);

        PostRequestModel postRequestModel = new PostRequestModel("IT wala", "Mumbai");
        Call<PostResponseModel> call = retrofitBuilder.callApi().postHeros(postRequestModel);

        call.enqueue(new Callback<PostResponseModel>() {
            @Override public void onResponse(@NonNull Call<PostResponseModel> call,
                                             @NonNull Response<PostResponseModel> response) {
                binding.txtResultValue.setText(Objects.requireNonNull(response.body()).toString());
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override public void onFailure(@NonNull Call<PostResponseModel> call, @NonNull Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void getRequest() {
        binding.progressBar.setVisibility(View.VISIBLE);

        retrofitBuilder.build(retrofitBuilder.BASE_URL_GET);

        Call<List<GetRequestModel>> call =
                retrofitBuilder.callApi().getHeros();

        call.enqueue(new Callback<List<GetRequestModel>>() {
            @Override public void onResponse(@NonNull Call<List<GetRequestModel>> call,
                                             @NonNull Response<List<GetRequestModel>> response) {
                binding.txtResultValue.setText(Objects.requireNonNull(response.body()).toString());
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override public void onFailure(@NonNull Call<List<GetRequestModel>> call, @NonNull Throwable t) {
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