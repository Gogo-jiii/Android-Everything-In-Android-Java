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
import com.example.recyclerview.databinding.RowItemHorizontalLayoutBinding;
import com.example.recyclerview.models.RecyclerviewModelHorizontalLayout;

import java.util.ArrayList;

public class RecyclerviewAdapterHorizontalLayout extends RecyclerView.Adapter<RecyclerviewAdapterHorizontalLayout.ViewHolder> {

    private final ArrayList<RecyclerviewModelHorizontalLayout> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterHorizontalLayout(ArrayList<RecyclerviewModelHorizontalLayout> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemHorizontalLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelHorizontalLayout model = data.get(position);
        holder.binding.textView.setText(model.getItemName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemHorizontalLayoutBinding binding;

        public ViewHolder(@NonNull RowItemHorizontalLayoutBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}