package com.example.toast;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toast.databinding.RowItemBinding;

import java.util.ArrayList;

public class ToastAdapter extends RecyclerView.Adapter<ToastAdapter.ViewHolder> {

    private final ArrayList<ToastModel> data;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public ToastAdapter(ArrayList<ToastModel> data, Activity activity, Fragment context) {
        this.data = data;
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ToastModel model = data.get(position);
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

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));
        }
    }

    interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int toastType);
    }
}