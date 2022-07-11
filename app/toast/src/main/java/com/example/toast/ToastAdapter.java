package com.example.toast;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toast.databinding.RowItemBinding;

import java.util.ArrayList;

public class ToastAdapter extends RecyclerView.Adapter<ToastAdapter.ViewHolder> {

    enum ToastType {
        NORMAL_TOAST,
        CUSTOM_TOAST
    }

    private final ArrayList<ToastModel> data;
    OnRecyclerviewItemClickListener onRecyclerviewItemClick;

    public ToastAdapter(ArrayList<ToastModel> data, ToastFragment context) {
        this.data = data;
        onRecyclerviewItemClick = (OnRecyclerviewItemClickListener) context;
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

            itemView.setOnClickListener(v -> {
                switch (getAdapterPosition()) {
                    case 0:
                        onRecyclerviewItemClick.onRecyclerviewItemClick(ToastType.NORMAL_TOAST);
                        break;
                    case 1:
                        onRecyclerviewItemClick.onRecyclerviewItemClick(ToastType.CUSTOM_TOAST);
                        break;
                }
            });
        }
    }

    interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(ToastType toastType);
    }
}