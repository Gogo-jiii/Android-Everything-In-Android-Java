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
import com.example.recyclerview.databinding.RowItemNestedChildBinding;
import com.example.recyclerview.models.RecyclerviewModelNestedChild;
import com.example.recyclerview.models.RecyclerviewModelNestedParent;

import java.util.ArrayList;

public class RecyclerviewAdapterNestedChild extends RecyclerView.Adapter<RecyclerviewAdapterNestedChild.ViewHolder> {

    private ArrayList<RecyclerviewModelNestedChild> arrayListChild;
    private ArrayList<RecyclerviewModelNestedParent> arrayListParent;

    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterNestedChild(ArrayList<RecyclerviewModelNestedParent> arrayListParent, ArrayList<RecyclerviewModelNestedChild> arrayListChild, Activity activity, Fragment fragment) {
        this.arrayListParent = arrayListParent;
        this.arrayListChild = arrayListChild;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemNestedChildBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelNestedChild model = arrayListChild.get(position);
        holder.binding.textViewChild.setText(model.getChild());
    }

    @Override
    public int getItemCount() {
        return arrayListChild.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemNestedChildBinding binding;

        public ViewHolder(@NonNull RowItemNestedChildBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewChildItemClick(RecyclerviewAdapterNestedParent.parentPosition, getAdapterPosition()));
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewChildItemClick(int parentPosition, int childPosition);
    }

}