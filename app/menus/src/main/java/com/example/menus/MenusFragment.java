package com.example.menus;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.commonmodule.ToolbarManager;
import com.example.menus.databinding.FragmentMenusBinding;

public class MenusFragment extends Fragment {

    FragmentMenusBinding binding;
    private NavController navController;
    private ActionMode actionMode;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMenusBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);
        setupToolbar();

        binding.btnShowPopupMenu.setOnClickListener(this::showPopupMenu);

        binding.btnContextMenu.setOnClickListener(this::showContextMenu);

        binding.btnActionModeMenu.setOnLongClickListener(view -> {
            if (actionMode != null) {
                return false;
            }

            actionMode = requireActivity().startActionMode(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.action_mode_context_menu, menu);
                    mode.setTitle("Action Mode");
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {
                    int itemId = menuItem.getItemId();
                    if (itemId == R.id.two) {
                        Toast.makeText(getContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        mode.finish();
                    } else if (itemId == R.id.three) {
                        Toast.makeText(getContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                        mode.finish();
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    actionMode = null;
                }
            });
            return true;
        });

        binding.toolbar.setOnMenuItemClickListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.seven) {
                Toast.makeText(getContext(), "seven clicked.", Toast.LENGTH_SHORT).show();
                return true;
            } else if (itemId == R.id.bluetooth) {
                Toast.makeText(getContext(), "bluetooth clicked.",
                        Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        return binding.getRoot();
    }


    private void showContextMenu(View view) {
        registerForContextMenu(view);
        requireActivity().openContextMenu(view);
    }

    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(item -> {
            Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        });
        popupMenu.show();
    }

    private void setupToolbar() {
        ToolbarManager toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = requireActivity().getMenuInflater();
        menuInflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select Item");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.four) {
            Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.five) {
            Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.six) {
            Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }

}