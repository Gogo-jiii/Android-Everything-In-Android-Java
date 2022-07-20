package com.example.recyclerview.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RowItemExpandableBinding;
import com.example.recyclerview.models.RecyclerviewModelExpandable;

import java.util.ArrayList;

public class RecyclerviewAdapterExpandable extends RecyclerView.Adapter<RecyclerviewAdapterExpandable.ViewHolder> {

    private final ArrayList<RecyclerviewModelExpandable> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterExpandable(ArrayList<RecyclerviewModelExpandable> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemExpandableBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelExpandable model = data.get(position);
        holder.binding.textView.setText(model.getItemName());

        if (model.isShouldExpand()) {
            holder.binding.imageView.setVisibility(View.VISIBLE);
        } else {
            holder.binding.imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemExpandableBinding binding;

        public ViewHolder(@NonNull RowItemExpandableBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));

            binding.imageViewArrow.setOnClickListener(v -> {
                data.get(getAdapterPosition()).setShouldExpand(!data.get(getAdapterPosition()).isShouldExpand());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}