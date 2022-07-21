package com.example.dashboard;

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

import com.example.dashboard.databinding.RowItemBinding;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> implements Filterable {

    private ArrayList<DashboardModel> data;
    private ArrayList<DashboardModel> tempData;
    private ArrayList<DashboardModel> filteredData = new ArrayList<>();
    private final NavController navController;
    OnRecyclerviewItemClickListener recyclerviewItemClickListener;

    public DashboardAdapter(ArrayList<DashboardModel> data, Activity activity, Fragment fragment) {
        this.data = data;
        tempData = data;
        navController = Navigation.findNavController(activity, R.id.fragmentContainerView);
        recyclerviewItemClickListener = (OnRecyclerviewItemClickListener) fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(RowItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DashboardModel model = data.get(position);
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

                for (DashboardModel item : tempData) {
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
            data = (ArrayList<DashboardModel>) results.values;
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        RowItemBinding binding;

        public ViewHolder(@NonNull RowItemBinding view) {
            super(view.getRoot());
            binding = view;

            itemView.setOnClickListener(v -> recyclerviewItemClickListener.onRecyclerviewItemClick(data.get(getAdapterPosition()).getItemName()));

        }
    }

    interface OnRecyclerviewItemClickListener {
        void onRecyclerviewItemClick(String itemName);
    }

}