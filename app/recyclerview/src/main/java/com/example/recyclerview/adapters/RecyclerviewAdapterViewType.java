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
import com.example.recyclerview.databinding.RowItemViewTypeImageBinding;
import com.example.recyclerview.databinding.RowItemViewTypeTextBinding;
import com.example.recyclerview.models.RecyclerviewModelViewType;

import java.util.ArrayList;

public class RecyclerviewAdapterViewType extends RecyclerView.Adapter {

    private final ArrayList<RecyclerviewModelViewType> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterViewType(ArrayList<RecyclerviewModelViewType> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case RecyclerviewModelViewType.TYPE_TEXT:
                return new TextTypeViewHolder(RowItemViewTypeTextBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

            case RecyclerviewModelViewType.TYPE_IMAGE:
                return new ImageTypeViewHolder(RowItemViewTypeImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }
        return new TextTypeViewHolder(RowItemViewTypeTextBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerviewModelViewType model = data.get(position);

        if (model != null) {
            switch (model.getType()) {
                case RecyclerviewModelViewType.TYPE_TEXT:
                    TextTypeViewHolder textTypeViewHolder = (TextTypeViewHolder) holder;
                    textTypeViewHolder.binding.textView.setText(model.getText());
                    break;
                case RecyclerviewModelViewType.TYPE_IMAGE:
                    ImageTypeViewHolder imageTypeViewHolder = (ImageTypeViewHolder) holder;
                    imageTypeViewHolder.binding.textView2.setText(model.getText());
                    imageTypeViewHolder.binding.imageView.setImageResource(model.getImage());
                    break;
            }
        }
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (data.get(position).getType()) {
            case 1:
                return RecyclerviewModelViewType.TYPE_TEXT;
            case 2:
                return RecyclerviewModelViewType.TYPE_IMAGE;
            default:
                return -1;
        }
    }

    public class TextTypeViewHolder extends RecyclerView.ViewHolder {
        RowItemViewTypeTextBinding binding;

        public TextTypeViewHolder(@NonNull RowItemViewTypeTextBinding view) {
            super(view.getRoot());
            binding = view;
            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));
        }
    }

    public class ImageTypeViewHolder extends RecyclerView.ViewHolder {
        RowItemViewTypeImageBinding binding;

        public ImageTypeViewHolder(@NonNull RowItemViewTypeImageBinding view) {
            super(view.getRoot());
            binding = view;
            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}