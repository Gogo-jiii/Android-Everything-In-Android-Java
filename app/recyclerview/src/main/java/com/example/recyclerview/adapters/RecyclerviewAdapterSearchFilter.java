package com.example.recyclerview.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;
import com.example.recyclerview.databinding.RowItemSearchFilterBinding;
import com.example.recyclerview.models.RecyclerviewModelSearchFilter;

import java.util.ArrayList;

public class RecyclerviewAdapterSearchFilter extends RecyclerView.Adapter<RecyclerviewAdapterSearchFilter.ViewHolder> implements Filterable {

    private ArrayList<RecyclerviewModelSearchFilter> data;
    private ArrayList<RecyclerviewModelSearchFilter> tempData;
    private ArrayList<RecyclerviewModelSearchFilter> filteredData = new ArrayList<>();
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public RecyclerviewAdapterSearchFilter(ArrayList<RecyclerviewModelSearchFilter> data, Activity activity, Fragment fragment) {
        this.data = data;
        tempData = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemSearchFilterBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecyclerviewModelSearchFilter model = data.get(position);
        holder.binding.textView.setText(model.getItemName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            String query = constraint.toString();

            if (query.isEmpty()) {
                //tempList ha apla backup ahe. jevha search query empty hoil tevha hya backup madhun
                //original list baher kadhaychi which is the Full proof list.
                results.values = tempData;
            } else {
                //filtered list la empty karaycha after each typed or deleted character and punha
                //filtered list loop laun populate karaychi as done below.
                filteredData.clear();

                for (RecyclerviewModelSearchFilter item : tempData) {
                    if (item.getItemName().toLowerCase().contains(query.toLowerCase().trim())) {
                        filteredData.add(item);
                    }
                }
                //finally arraylist ch publish hote, so filtered list arraylist madhe takaychi.
                results.values = filteredData;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data = (ArrayList<RecyclerviewModelSearchFilter>) results.values;
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemSearchFilterBinding binding;

        public ViewHolder(@NonNull RowItemSearchFilterBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(getAdapterPosition(), filteredData));
        }
    }

    public interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(int type, ArrayList<RecyclerviewModelSearchFilter> filteredData);
    }

}