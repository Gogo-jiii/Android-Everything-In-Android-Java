package com.example.recyclerview.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RowItemCheckboxBinding;
import com.example.recyclerview.models.RecyclerviewModelCheckbox;

import java.util.ArrayList;

public class RecyclerviewAdapterCheckbox extends RecyclerView.Adapter<RecyclerviewAdapterCheckbox.ViewHolder> {

    private final ArrayList<RecyclerviewModelCheckbox> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterCheckbox(ArrayList<RecyclerviewModelCheckbox> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemCheckboxBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelCheckbox model = data.get(position);
        holder.binding.textView.setText(model.getItemName());
        holder.binding.checkBox.setChecked(model.isSelected());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemCheckboxBinding binding;

        public ViewHolder(@NonNull RowItemCheckboxBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));


            binding.checkBox.setOnClickListener(v -> {
                boolean isChecked = ((CheckBox) v).isChecked();

                data.get(getAdapterPosition()).setSelected(isChecked);
                notifyItemChanged(getAdapterPosition());
            });

        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}