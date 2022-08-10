package com.example.gson;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.gson.databinding.FragmentGsonBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonFragment extends Fragment {

    FragmentGsonBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private String json;
    private Gson gson;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGsonBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        gson = new Gson();

        binding.btnModelToJson.setOnClickListener(v -> doModelToJson());

        binding.btnJsonToModel.setOnClickListener(v -> doJsonToModel());

        binding.btnArraylistToAndFromList.setOnClickListener(v -> doGsonConversionToAndFromArrayList());

        return binding.getRoot();
    }

    private void doModelToJson() {
        UserModel userModel = new UserModel("IT wala");
        json = gson.toJson(userModel);

        binding.txtResult.setText(json);
    }

    private void doJsonToModel() {
        if (!TextUtils.isEmpty(json)) {
            try {
                UserModel model = gson.fromJson(json, UserModel.class);
                binding.txtResult.setText(model.toString());
            } catch (Exception exception) {
                Toast.makeText(requireContext(), "Click model to json first.",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireContext(), "Click model to json first.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void doGsonConversionToAndFromArrayList() {
        String result;

        Type listType = new TypeToken<List<UserModel>>() {
        }.getType();

        ArrayList<UserModel> list = new ArrayList<>();
        list.add(new UserModel("A"));
        list.add(new UserModel("B"));
        list.add(new UserModel("C"));

        json = gson.toJson(list, listType);

        ArrayList<UserModel> list1 = gson.fromJson(json, listType);

        result = "Json: " + json + "\n" +
                "List: " + list1.toString();

        binding.txtResult.setText(result);
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