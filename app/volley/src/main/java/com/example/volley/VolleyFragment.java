package com.example.volley;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.commonmodule.ToolbarManager;
import com.example.volley.databinding.FragmentVolleyBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class VolleyFragment extends Fragment {

    FragmentVolleyBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private StringRequest getRequest, postRequest;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVolleyBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        requestQueue = Volley.newRequestQueue(requireContext());

        binding.btnGetRequest.setOnClickListener(v -> getRequest());

        binding.btnPostRequest.setOnClickListener(v -> postRequest());
        return binding.getRoot();
    }

    private void getRequest() {
        binding.progressBar.setVisibility(View.VISIBLE);

        String url = "https://simplifiedcoding.net/demos/marvel";

        getRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    binding.txtResultValue.setText(response);

                    binding.progressBar.setVisibility(View.GONE);
                }, error -> {
                    binding.txtResultValue.setText(R.string.something_went_wrong);
                    binding.progressBar.setVisibility(View.GONE);
                });

        getRequest.setTag("getRequest");
        requestQueue.add(getRequest);
    }

    private void postRequest() {
        binding.progressBar.setVisibility(View.VISIBLE);

        String url = "https://reqres.in/api/users";
        String requestBody;
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("name", "IT wala");
            requestBody = jsonBody.toString();

            postRequest = new StringRequest(Request.Method.POST, url,
                    response -> {
                        Log.d("TAG_Response", response);
                        binding.txtResultValue.setText(response);
                        binding.progressBar.setVisibility(View.GONE);
                    }, error -> {
                        Log.d("TAG_Error", error.toString());
                        binding.progressBar.setVisibility(View.GONE);
                    }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() {
                    return requestBody.getBytes(StandardCharsets.UTF_8);
                }

            };

            getRequest.setTag("postRequest");
            requestQueue.add(postRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    @Override
    public void onStop() {
        if (requestQueue != null) {
            requestQueue.cancelAll("getRequest");
            requestQueue.cancelAll("postRequest");
            binding.progressBar.setVisibility(View.GONE);
        }
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}