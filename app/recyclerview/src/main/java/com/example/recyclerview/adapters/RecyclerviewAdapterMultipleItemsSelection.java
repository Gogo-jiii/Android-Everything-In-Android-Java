package com.example.recyclerview.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RowItemMultipleItemsSelectionBinding;
import com.example.recyclerview.models.RecyclerviewModelMultipleItemsSelection;

import java.util.ArrayList;

public class RecyclerviewAdapterMultipleItemsSelection extends RecyclerView.Adapter<RecyclerviewAdapterMultipleItemsSelection.ViewHolder> {

    private final ArrayList<RecyclerviewModelMultipleItemsSelection> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterMultipleItemsSelection(ArrayList<RecyclerviewModelMultipleItemsSelection> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowItemMultipleItemsSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelMultipleItemsSelection model = data.get(position);
        holder.binding.textView.setText(model.getItemName());

        if (model.isSelected()) {
            holder.itemView.setBackgroundColor(holder.itemView.getResources().getColor(R.color.highlight));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemMultipleItemsSelectionBinding binding;

        public ViewHolder(@NonNull RowItemMultipleItemsSelectionBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> {
                recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition());
                setMultipleSelection(getAdapterPosition());
            });
        }
    }

    private void setMultipleSelection(int adapterPosition) {
        //If row is already selected then unselect it, otherwise select it.
        data.get(adapterPosition).setSelected(!data.get(adapterPosition).isSelected());
        notifyItemChanged(adapterPosition);
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}