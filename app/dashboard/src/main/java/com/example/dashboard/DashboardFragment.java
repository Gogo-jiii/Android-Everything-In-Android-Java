package com.example.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity.FirstActivity;
import com.example.commonmodule.ToolbarManager;
import com.example.dashboard.databinding.FragmentDashboardBinding;
import com.example.fragment.FragmentsActivity;
import com.example.navigationdrawer.NavigationDrawerActivity;

import java.util.ArrayList;


public class DashboardFragment extends Fragment implements DashboardAdapter.OnRecyclerviewItemClickListener {

    FragmentDashboardBinding binding;
    ArrayList<DashboardModel> list = new ArrayList<>();

    String[] data = new String[]{"Logs", "Toast", "Button", "Edit Text", "Text Watcher", "Snackbar", "Checkbox",
            "Radio Button", "Toggle Button", "Autocomplete Textview", "Spinner", "Alert Dialog", "Ratings Bar", "Seekbar", "Progress Dialog",
            "Webview", "Timepicker Dialog", "Datepicker Dialog", "Imageview", "TextInput Layout", "Shared Preference", "Activity", "Fragment",
            "Recyclerview", "System Bars", "Keyboard", "Interface Callback", "Chips and Chips-Group", "Clipboard", "Toolbar", "Floating Action Button", "Bottom App Bar",
            "Bottom Navigation", "Persistent Bottom Sheet", "Modal Bottom Sheet", "View Pager", "Tab Layout", "Navigation Drawer", "Menu", "Runtime Permissions",
            "Validation", "Notifications", "Retrofit", "Volley", "GSON", "Picasso", "Glide", "String Formatting", "Storage", "File Picker", "Share Data",
            "Biometric Auth", "Picture In Picture", "Pick Image From Gallery", "Encryption", "Camera", "SQLite", "Room", "Service",
    "Broadcast Receiver", "Job Scheduler", "Work Manager", "User Location", "Google Sign In"};

    RecyclerView recyclerView;
    private ToolbarManager toolbarManager;
    private NavController navController;
    private SearchView searchView;
    private DashboardAdapter myAdapter;

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

        binding.toolbar.addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu);
                setupSearchview(menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        });
    }

    private void setupSearchview(Menu menu) {
        searchView = (SearchView) menu.findItem(R.id.searchview).getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                myAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                myAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private void prepareRecyclerView() {
        recyclerView = binding.recyclerView;

        myAdapter = new DashboardAdapter(getData(), getActivity(), this);
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
    public void onPause() {
        super.onPause();
        searchView.setIconified(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onRecyclerviewItemClick(String itemName) {
        searchView.setIconified(true);
        int index = DashboardModel.getIndexOfItem(list, itemName);

        switch (DashboardType.getType(index)) {
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
            case IMAGEVIEW:
                navController.navigate(R.id.action_dashboardFragment_to_imageview_nav_graph);
                break;
            case TEXT_INPUT_LAYOUT:
                navController.navigate(R.id.action_dashboardFragment_to_textinput_layout_nav_graph);
                break;
            case SHARED_PREFERENCE:
                navController.navigate(R.id.action_dashboardFragment_to_shared_pref_nav_graph);
                break;
            case ACTIVITY:
                startActivity(new Intent(getContext(), FirstActivity.class));
                break;
            case FRAGMENT:
                startActivity(new Intent(getContext(), FragmentsActivity.class));
                break;
            case RECYCLER_VIEW:
                navController.navigate(R.id.action_dashboardFragment_to_recyclerview_nav_graph);
                break;
            case SYSTEM_BARS:
                navController.navigate(R.id.action_dashboardFragment_to_system_bars_nav_graph);
                break;
            case KEYBOARD:
                navController.navigate(R.id.action_dashboardFragment_to_keyboard_nav_graph);
                break;
            case INTERFACE_CALLBACK:
                navController.navigate(R.id.action_dashboardFragment_to_interface_callback_nav_graph);
                break;
            case CHIPS:
                navController.navigate(R.id.action_dashboardFragment_to_chips_navigation);
                break;
            case CLIPBOARD:
                navController.navigate(R.id.action_dashboardFragment_to_clipboard_nav_graph);
                break;
            case TOOLBAR:
                navController.navigate(R.id.action_dashboardFragment_to_toolbar_nav_graph);
                break;
            case FAB:
                navController.navigate(R.id.action_dashboardFragment_to_fab_nav_graph);
                break;
            case BOTTOM_APP_BAR:
                navController.navigate(R.id.action_dashboardFragment_to_bottom_action_bar_nav_graph);
                break;
            case BOTTOM_NAVIGATION:
                navController.navigate(R.id.action_dashboardFragment_to_bottom_navigation_nav_graph);
                break;
            case PERSISTENT_BOTTOM_SHEET:
                navController.navigate(R.id.action_dashboardFragment_to_persistent_bottom_sheet_nav_graph);
                break;
            case MODAL_BOTTOM_SHEET:
                navController.navigate(R.id.action_dashboardFragment_to_modal_bottom_sheet_nav_graph);
                break;
            case VIEW_PAGER:
                navController.navigate(R.id.action_dashboardFragment_to_view_pager_nav_graph);
                break;
            case TAB_LAYOUT:
                navController.navigate(R.id.action_dashboardFragment_to_tab_layout_nav_graph);
                break;
            case NAVIGATION_DRAWER:
                startActivity(new Intent(getContext(), NavigationDrawerActivity.class));
                break;
            case MENU:
                navController.navigate(R.id.action_dashboardFragment_to_menus_nav_graph);
                break;
            case RUNTIME_PERMISSIONS:
                navController.navigate(R.id.action_dashboardFragment_to_permissions_manager_nav_graph);
                break;
            case VALIDATION:
                navController.navigate(R.id.action_dashboardFragment_to_validation_nav_graph);
                break;
            case NOTIFICATIONS:
                navController.navigate(R.id.action_dashboardFragment_to_notificaiton_nav_graph);
                break;
            case RETROFIT:
                navController.navigate(R.id.action_dashboardFragment_to_retrofit_nav_graph);
                break;
            case VOLLEY:
                navController.navigate(R.id.action_dashboardFragment_to_volley_nav_graph);
                break;
            case GSON:
                navController.navigate(R.id.action_dashboardFragment_to_gson_nav_graph);
                break;
            case PICASSO:
                navController.navigate(R.id.action_dashboardFragment_to_picasso_nav_graph);
                break;
            case GLIDE:
                navController.navigate(R.id.action_dashboardFragment_to_glide_nav_graph);
                break;
            case STRING_FORMATTING:
                navController.navigate(R.id.action_dashboardFragment_to_string_formatting_nav_graph);
                break;
            case STORAGE:
                navController.navigate(R.id.action_dashboardFragment_to_storage_nav_graph);
                break;
            case FILE_PICKER:
                navController.navigate(R.id.action_dashboardFragment_to_file_picker_nav_graph);
                break;
            case SHARE_DATA:
                navController.navigate(R.id.action_dashboardFragment_to_share_data_nav_graph);
                break;
            case BIOMETRIC_AUTH:
                navController.navigate(R.id.action_dashboardFragment_to_biometric_auth_nav_graph);
                break;
            case PICTURE_IN_PICTURE:
                navController.navigate(R.id.action_dashboardFragment_to_pip_nav_graph);
                break;
            case PICK_IMAGE_FROM_GALLERY:
                navController.navigate(R.id.action_dashboardFragment_to_pick_image_from_gallery_nav_graph);
                break;
            case ENCRYPTION:
                navController.navigate(R.id.action_dashboardFragment_to_encryption_nav_graph);
                break;
            case CAMERA:
                navController.navigate(R.id.action_dashboardFragment_to_camera_nav_graph);
                break;
            case SQLITE:
                navController.navigate(R.id.action_dashboardFragment_to_sqlite_nav_graph);
                break;
            case ROOM:
                navController.navigate(R.id.action_dashboardFragment_to_room_nav_graph);
                break;
            case SERVICE:
                navController.navigate(R.id.action_dashboardFragment_to_service_nav_graph);
                break;
            case BROADCAST_RECEIVER:
                navController.navigate(R.id.action_dashboardFragment_to_broadcast_receiver_nav_graph);
                break;
            case JOB_SCHEDULER:
                navController.navigate(R.id.action_dashboardFragment_to_job_schedular_nav_graph);
                break;
            case WORK_MANAGER:
                navController.navigate(R.id.action_dashboardFragment_to_work_manager_nav_graph);
                break;
            case USER_LOCATION:
                navController.navigate(R.id.action_dashboardFragment_to_user_location_nav_graph);
                break;
            case GOOGLE_SIGN_IN:
                navController.navigate(R.id.action_dashboardFragment_to_google_signin_nav_graph);
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
        DATE_PICKER_DIALOG,
        IMAGEVIEW,
        TEXT_INPUT_LAYOUT,
        SHARED_PREFERENCE,
        ACTIVITY,
        FRAGMENT,
        RECYCLER_VIEW,
        SYSTEM_BARS,
        KEYBOARD,
        INTERFACE_CALLBACK,
        CHIPS,
        CLIPBOARD,
        TOOLBAR,
        FAB,
        BOTTOM_APP_BAR,
        BOTTOM_NAVIGATION,
        PERSISTENT_BOTTOM_SHEET,
        MODAL_BOTTOM_SHEET,
        VIEW_PAGER,
        TAB_LAYOUT,
        NAVIGATION_DRAWER,
        MENU,
        RUNTIME_PERMISSIONS,
        VALIDATION,
        NOTIFICATIONS,
        RETROFIT,
        VOLLEY,
        GSON,
        PICASSO,
        GLIDE,
        STRING_FORMATTING,
        STORAGE,
        FILE_PICKER,
        SHARE_DATA,
        BIOMETRIC_AUTH,
        PICTURE_IN_PICTURE,
        PICK_IMAGE_FROM_GALLERY,
        ENCRYPTION,
        CAMERA,
        SQLITE,
        ROOM,
        SERVICE,
        BROADCAST_RECEIVER,
        JOB_SCHEDULER,
        WORK_MANAGER,
        USER_LOCATION,
        GOOGLE_SIGN_IN;

        private static DashboardFragment.DashboardType[] list = DashboardFragment.DashboardType.values();

        public static DashboardFragment.DashboardType getType(int i) {
            return list[i];
        }
    }
}