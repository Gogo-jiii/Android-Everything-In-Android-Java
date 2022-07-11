package com.example.dashboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dashboard.databinding.RowItemBinding;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private final ArrayList<DashboardModel> data;
    private final NavController navController;

    public DashboardAdapter(ArrayList<DashboardModel> data, Activity activity) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DashboardModel model = data.get(position);
        holder.binding.textView.setText(model.getItemName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemBinding binding;

        public ViewHolder(@NonNull RowItemBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> {
                switch (getAdapterPosition()){
                    case 0 : navController.navigate(R.id.action_dashboardFragment_to_logs_nav_graph); break;
                    case 1 : navController.navigate(R.id.action_dashboardFragment_to_toast_nav_graph); break;
                    case 2 : navController.navigate(R.id.action_dashboardFragment_to_button_nav_graph); break;
                    case 3 : navController.navigate(R.id.action_dashboardFragment_to_textwatcher_nav_graph); break;
                }
            });
        }
    }
}