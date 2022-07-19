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
import com.example.recyclerview.databinding.RowItemRadioButtonBinding;
import com.example.recyclerview.models.RecyclerviewModelRadioButton;

import java.util.ArrayList;

public class RecyclerviewAdapterRadioButton extends RecyclerView.Adapter<RecyclerviewAdapterRadioButton.ViewHolder> {

    private final ArrayList<RecyclerviewModelRadioButton> data;
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;
    private boolean isNewRadioButtonChecked = false;
    private int lastCheckedPosition = -1;

    public RecyclerviewAdapterRadioButton(ArrayList<RecyclerviewModelRadioButton> data, Activity activity, Fragment fragment) {
        this.data = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemRadioButtonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelRadioButton model = data.get(position);
        holder.binding.textView.setText(model.getItemName());

        if (isNewRadioButtonChecked) {
            holder.binding.radioButton.setChecked(model.isSelected());
        } else {
            if (holder.getAdapterPosition() == 0) {
                holder.binding.radioButton.setChecked(true);
                lastCheckedPosition = 0;
            }
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemRadioButtonBinding binding;

        public ViewHolder(@NonNull RowItemRadioButtonBinding view) {
            super(view.getRoot());
            binding = view;

            binding.radioButton.setOnClickListener(v -> handleRadiobuttonChecks(getAdapterPosition()));

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition()));
        }
    }

    private void handleRadiobuttonChecks(int adapterPosition) {
        //Navin radiobutton check jhalay so junya button chi state false karaychi, navin button chi
        //state true karaychi ani lastCheckposition update karun current adapterposition thevaychi.

        isNewRadioButtonChecked = true;

        data.get(lastCheckedPosition).setSelected(false);
        notifyItemChanged(lastCheckedPosition);

        data.get(adapterPosition).setSelected(true);
        notifyItemChanged(adapterPosition);

        lastCheckedPosition = adapterPosition;
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type);
    }

}