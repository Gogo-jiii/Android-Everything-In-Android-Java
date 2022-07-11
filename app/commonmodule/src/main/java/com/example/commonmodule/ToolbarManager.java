package com.example.commonmodule;

import android.content.Context;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class ToolbarManager {

    private static ToolbarManager instance;
    private AppBarConfiguration appBarConfiguration;

    private ToolbarManager() {
    }

    public static ToolbarManager getInstance() {
        if (instance == null) {
            instance = new ToolbarManager();
        }
        return instance;
    }

    public void setupToolbar(Context context, NavController navController,
                             AppBarConfiguration appBarConfiguration, Toolbar toolbar,
                             boolean isSetupBackButton) {

        this.appBarConfiguration = appBarConfiguration;
        if (appBarConfiguration == null) {
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        }
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        //title color
        toolbar.setTitleTextColor(context.getResources().getColor(R.color.white));

        //back arrow button
        if (isSetupBackButton) {
            setupBackButton(context, toolbar);
        } else {
            toolbar.setNavigationIcon(null);
        }
    }

    private void setupBackButton(Context context, Toolbar toolbar) {
        toolbar.setNavigationIcon(ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_baseline_arrow_back_24, context.getTheme()));
    }

}