package com.example.modalbottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.modalbottomsheet.databinding.ModalBottomSheetViewBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class ModalBottomSheetDialogFragment extends BottomSheetDialogFragment {

    ModalBottomSheetViewBinding binding;
    private OnModalBottomSheetItemClickCallback callback;
    private ArrayList<String> data;
    private Fragment context;

    public ModalBottomSheetDialogFragment(Fragment context, ArrayList<String> data) {
        this.data = data;
        this.context = context;
        callback = (OnModalBottomSheetItemClickCallback) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ModalBottomSheetViewBinding.inflate(inflater, container, false);

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1
                , data);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            dismiss();
            callback.onModalBottomSheetItemClicked(position);
        });

        return binding.getRoot();
    }

    public interface OnModalBottomSheetItemClickCallback {
        void onModalBottomSheetItemClicked(int index);
    }
}
