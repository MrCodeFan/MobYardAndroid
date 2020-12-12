package com.example.mobyardandroid.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobyardandroid.R;
import com.example.mobyardandroid.yard.YardInfoActivity;

import java.util.ArrayList;

public class YardsHomeAdapter extends
        RecyclerView.Adapter<YardsHomeAdapter.YardsHomeViewHolder> {

    ArrayList<HomeAdapter> homeAdapters;
    public SharedPreferences infoPref;

    public YardsHomeAdapter(ArrayList<HomeAdapter> homeAdapters, SharedPreferences infoPref) {
        this.homeAdapters = homeAdapters;
        this.infoPref = infoPref;
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
        holder.num.setText(yardsHomeAdapter.getCardNumStr());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = infoPref.edit();
                editor.putString("yard_id", yardsHomeAdapter.getId() );


                // Toast.makeText(v.getContext(), yardsHomeAdapter.getId(), Toast.LENGTH_LONG);

                Intent intent = new Intent(v.getContext(), YardInfoActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return homeAdapters.size();
    }

    public static class YardsHomeViewHolder extends RecyclerView.ViewHolder{


        ImageView image;
        TextView title, desc, id, num;

        public YardsHomeViewHolder(@NonNull View itemView){
            super(itemView);

            // Hooks
            image = itemView.findViewById(R.id.yard_image);
            id = itemView.findViewById(R.id.yard_id);
            desc = itemView.findViewById(R.id.yard_desc);
            title = itemView.findViewById(R.id.yard_heading);
            num = itemView.findViewById(R.id.yard_num);
        }
    }


}
