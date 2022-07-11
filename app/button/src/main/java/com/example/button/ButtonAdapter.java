package com.example.button;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.button.databinding.RowItemBinding;

import java.util.ArrayList;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {

    private final ArrayList<ButtonModel> data;
    private final NavController navController;

    public ButtonAdapter(ArrayList<ButtonModel> data, Activity activity) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ButtonModel model = data.get(position);
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
                        navController.navigate(R.id.action_buttonFragment_to_buttonOnClickListenerFragment);
                        break;
                    case 1:
                        navController.navigate(R.id.action_buttonFragment_to_multipleButtonsOnClickListenerFragment);
                        break;
                }
            });
        }
    }

}