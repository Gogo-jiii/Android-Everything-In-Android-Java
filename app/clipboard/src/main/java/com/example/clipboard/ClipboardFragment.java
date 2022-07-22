package com.example.clipboard;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
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

import com.example.clipboard.databinding.FragmentClipboardBinding;
import com.example.commonmodule.ToolbarManager;


public class ClipboardFragment extends Fragment {

    FragmentClipboardBinding binding;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private StringBuilder textToCopy;
    private StringBuilder result;
    private final String CLIPBOARD_LABEL = "CLIPBOARD_LABEL";
    private ClipboardManager clipboardManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentClipboardBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        textToCopy = new StringBuilder();
        result = new StringBuilder();
        clipboardManager = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);

        binding.btnCopyToClipboard.setOnClickListener(v -> copyTheTextToClipboard());
        return binding.getRoot();
    }

    private void copyTheTextToClipboard() {
        textToCopy.setLength(0);
        textToCopy.append(binding.editText.getText().toString());

        if (!TextUtils.isEmpty(textToCopy)) {
            ClipData clip = ClipData.newPlainText(CLIPBOARD_LABEL, textToCopy);
            clipboardManager.setPrimaryClip(clip);

            getTextFromClipboard();
        } else {
            Toast.makeText(getContext(), "Filed is empty.", Toast.LENGTH_SHORT).show();
        }
    }

    private void getTextFromClipboard() {
        result.setLength(0);

        for (int i = 0; i < clipboardManager.getPrimaryClip().getItemCount(); i++) {
            result.append(clipboardManager.getPrimaryClip().getItemAt(i).getText()).append("\n");
        }

        Toast.makeText(getContext(), "Copied: " + result.toString(), Toast.LENGTH_SHORT).show();
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