package com.example.recyclerview.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RowItemSingleItemSelectionBinding;
import com.example.recyclerview.models.RecyclerviewModelSingleItemSelection;

import java.util.ArrayList;

public class RecyclerviewAdapterSingleItemSelection extends RecyclerView.Adapter<RecyclerviewAdapterSingleItemSelection.ViewHolder> {

    private final ArrayList<RecyclerviewModelSingleItemSelection> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;
    int single_item_selection_position = -1;

    public RecyclerviewAdapterSingleItemSelection(ArrayList<RecyclerviewModelSingleItemSelection> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowItemSingleItemSelectionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelSingleItemSelection model = data.get(position);
        holder.binding.textView.setText(model.getItemName());

        if (single_item_selection_position == position) {
            holder.itemView.setBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.highlight));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemSingleItemSelectionBinding binding;

        public ViewHolder(@NonNull RowItemSingleItemSelectionBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> {
                Log.d("TAG", String.valueOf(getAdapterPosition()));
                setSingleSelection(getAdapterPosition());
                recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition());
            });
        }
    }

    private void setSingleSelection(int adapterPosition) {
        if (adapterPosition == RecyclerView.NO_POSITION) return;

        notifyItemChanged(single_item_selection_position);
        single_item_selection_position = adapterPosition;
        notifyItemChanged(single_item_selection_position);
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}