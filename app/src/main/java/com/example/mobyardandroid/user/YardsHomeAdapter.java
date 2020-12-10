package com.example.mobyardandroid.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobyardandroid.R;

import java.util.ArrayList;

public class YardsHomeAdapter extends
        RecyclerView.Adapter<YardsHomeAdapter.YardsHomeViewHolder> {

    ArrayList<HomeAdapter> homeAdapters;

    public YardsHomeAdapter(ArrayList<HomeAdapter> homeAdapters) {
        this.homeAdapters = homeAdapters;
    }


    @NonNull
    @Override
    public YardsHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()
        ).inflate(
                R.layout.yard_table_layout,
                parent,
                false
        );

        YardsHomeViewHolder yardsHomeViewHolder = new YardsHomeViewHolder(view);

        return yardsHomeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YardsHomeViewHolder holder, int position) {
        HomeAdapter yardsHomeAdapter = homeAdapters.get( position );

        holder.image.setImageResource(yardsHomeAdapter.getImage());
        holder.desc.setText(yardsHomeAdapter.getDescription());
        holder.title.setText(yardsHomeAdapter.getTitle());
        holder.id.setText(yardsHomeAdapter.getId());
    }

    @Override
    public int getItemCount() {
        return homeAdapters.size();
    }

    public static class YardsHomeViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc, id;

        public YardsHomeViewHolder(@NonNull View itemView){
            super(itemView);

            // Hooks
            image = itemView.findViewById(R.id.yard_image);
            id = itemView.findViewById(R.id.yard_id);
            desc = itemView.findViewById(R.id.yard_desc);
            title = itemView.findViewById(R.id.yard_heading);
        }
    }

}
