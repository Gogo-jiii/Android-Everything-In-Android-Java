package com.example.recyclerview.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RowItemNestedParentBinding;
import com.example.recyclerview.models.RecyclerviewModelNestedChild;
import com.example.recyclerview.models.RecyclerviewModelNestedParent;

import java.util.ArrayList;

public class RecyclerviewAdapterNestedParent extends RecyclerView.Adapter<RecyclerviewAdapterNestedParent.ViewHolder> {

    private final ArrayList<RecyclerviewModelNestedParent> arrayListParent;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    public static int parentPosition = -1;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;
    private final Activity activity;
    private final Fragment fragment;

    public RecyclerviewAdapterNestedParent(ArrayList<RecyclerviewModelNestedParent> arrayListParent, Activity activity, Fragment fragment) {
        this.arrayListParent = arrayListParent;
        this.fragment = fragment;
        this.activity = activity;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemNestedParentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelNestedParent modelNestedParent = arrayListParent.get(position);
        holder.binding.textViewParent.setText(modelNestedParent.getParent());

        ArrayList<RecyclerviewModelNestedChild> arrayListChild = modelNestedParent.getArrayListChild();

        //Child Recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.binding.recyclerviewChild.getContext(),
                LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setInitialPrefetchItemCount(arrayListChild.size());

        RecyclerviewAdapterNestedChild recyclerviewAdapterNestedChild = new RecyclerviewAdapterNestedChild(arrayListParent, arrayListChild, activity, fragment);

        holder.binding.recyclerviewChild.setLayoutManager(linearLayoutManager);
        holder.binding.recyclerviewChild.setAdapter(recyclerviewAdapterNestedChild);
        holder.binding.recyclerviewChild.setRecycledViewPool(viewPool);

        if (modelNestedParent.isChildVisible()) {
            holder.binding.recyclerviewChild.setVisibility(View.VISIBLE);
        } else {
            holder.binding.recyclerviewChild.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return arrayListParent.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemNestedParentBinding binding;

        public ViewHolder(@NonNull RowItemNestedParentBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> {
                parentPosition = getAdapterPosition();

                recyclerviewItemClickListener.onRecyclerviewParentItemClick(getAdapterPosition());

                RecyclerviewModelNestedParent recyclerviewModelNestedParent = arrayListParent.get(getAdapterPosition());
                recyclerviewModelNestedParent.setChildVisible(!recyclerviewModelNestedParent.isChildVisible());
                notifyItemChanged(getAdapterPosition());
            });
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewParentItemClick(int type);
    }

}