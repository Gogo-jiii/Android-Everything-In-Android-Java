package com.example.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.commonmodule.ToolbarManager;
import com.example.dashboard.databinding.FragmentDashboardBinding;

import java.util.ArrayList;


public class DashboardFragment extends Fragment implements DashboardAdapter.OnRecyclerviewItemClickListener {

    FragmentDashboardBinding binding;
    ArrayList<DashboardModel> list = new ArrayList<>();
    String[] data = new String[]{"Logs", "Toast", "Button", "Edit Text", "Text Watcher", "Snackbar", "Checkbox",
            "Radio Button", "Toggle Button", "Autocomplete Textview", "Spinner", "Alert Dialog", "Ratings Bar", "Seekbar", "Progress Dialog",
            "Webview", "Timepicker Dialog", "Datepicker Dialog"};
    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerView);

        setupToolbar();
        prepareRecyclerView();
        return binding.getRoot();
    }

    private void setupToolbar() {
        toolbarManager = ToolbarManager.getInstance();
        toolbarManager.setupToolbar(getActivity(), navController, null, binding.toolbar,
                false);
    }

    private void prepareRecyclerView() {
        recyclerView = binding.recyclerView;

        DashboardAdapter myAdapter = new DashboardAdapter(getData(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private ArrayList<DashboardModel> getData() {
        list.clear();

        for (String item : data) {
            list.add(new DashboardModel(item));
        }
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewItemClick(int type) {
        switch (DashboardType.getType(type)) {
            case LOGS:
                navController.navigate(R.id.action_dashboardFragment_to_logs_nav_graph);
                break;
            case TOAST:
                navController.navigate(R.id.action_dashboardFragment_to_toast_nav_graph);
                break;
            case BUTTON:
                navController.navigate(R.id.action_dashboardFragment_to_button_nav_graph);
                break;
            case TEXT_WATCHER:
                navController.navigate(R.id.action_dashboardFragment_to_textwatcher_nav_graph);
                break;
            case SNAKCBAR:
                navController.navigate(R.id.action_dashboardFragment_to_snackbar_nav_graph);
                break;
            case CHECKBOX:
                navController.navigate(R.id.action_dashboardFragment_to_checkbox_nav_graph);
                break;
            case RADIO_BUTTON:
                navController.navigate(R.id.action_dashboardFragment_to_radiobutton_nav_graph);
                break;
            case TOGGLE_BUTTON:
                navController.navigate(R.id.action_dashboardFragment_to_togglebutton_nav_graph);
                break;
            case EDIT_TEXT:
                navController.navigate(R.id.action_dashboardFragment_to_edit_text_nav_graph);
                break;
            case AUTO_COMPLETE_TEXTVIEW:
                navController.navigate(R.id.action_dashboardFragment_to_autocomplete_textview_nav_graph);
                break;
            case SPINNER:
                navController.navigate(R.id.action_dashboardFragment_to_spinner_nav_graph);
                break;
            case ALERT_DIALOG:
                navController.navigate(R.id.action_dashboardFragment_to_alert_dialog_nav_graph);
                break;
            case RATINGS_BAR:
                navController.navigate(R.id.action_dashboardFragment_to_ratingbar_nav_graph);
                break;
            case SEEKBAR:
                navController.navigate(R.id.action_dashboardFragment_to_seekbar_nav_graph);
                break;
            case PROGRESS_DIALOG:
                navController.navigate(R.id.action_dashboardFragment_to_progress_dialog_nav_graph);
                break;
            case WEBVIEW:
                navController.navigate(R.id.action_dashboardFragment_to_webview_nav_graph);
                break;
            case TIME_PICKER_DIALOG:
                navController.navigate(R.id.action_dashboardFragment_to_timepicker_dialog_nav_graph);
                break;
            case DATE_PICKER_DIALOG:
                navController.navigate(R.id.action_dashboardFragment_to_datepicker_dialog_nav_graph);
                break;
        }
    }

    public enum DashboardType {
        LOGS,
        TOAST,
        BUTTON,
        EDIT_TEXT,
        TEXT_WATCHER,
        SNAKCBAR,
        CHECKBOX,
        RADIO_BUTTON,
        TOGGLE_BUTTON,
        AUTO_COMPLETE_TEXTVIEW,
        SPINNER,
        ALERT_DIALOG,
        RATINGS_BAR,
        SEEKBAR,
        PROGRESS_DIALOG,
        WEBVIEW,
        TIME_PICKER_DIALOG,
        DATE_PICKER_DIALOG;

        private static DashboardFragment.DashboardType[] list = DashboardFragment.DashboardType.values();

        public static DashboardFragment.DashboardType getType(int i) {
            return list[i];
        }
    }
}