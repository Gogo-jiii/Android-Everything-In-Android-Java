package com.example.recyclerview.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RowItemSwipeToRefreshBinding;
import com.example.recyclerview.models.RecyclerviewModelSwipeToRefresh;

import java.util.ArrayList;

public class RecyclerviewAdapterSwipeToRefresh extends RecyclerView.Adapter<RecyclerviewAdapterSwipeToRefresh.ViewHolder> {

    private final ArrayList<RecyclerviewModelSwipeToRefresh> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterSwipeToRefresh(ArrayList<RecyclerviewModelSwipeToRefresh> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemSwipeToRefreshBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelSwipeToRefresh model = data.get(position);
        holder.binding.textView.setText(model.getItemName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemSwipeToRefreshBinding binding;

        public ViewHolder(@NonNull RowItemSwipeToRefreshBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}