package com.example.pmd_l2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pmd_l2.R;
import com.example.pmd_l2.entity.Episode;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder2> {

    Context context;
    private Episode[] listData;


    public MyAdapter2(Context context, Episode[] listData) {
        this.context = context;
        this.listData = listData;
    }


    @NonNull
    @Override
    public MyAdapter2.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item2, parent, false);
        return new ViewHolder2(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter2.ViewHolder2 holder, int position) {
        holder.name.setText(listData[position].name);
        holder.air_date.setText(listData[position].air_date);
        holder.episode.setText(listData[position].episode);
    }

    @Override
    public int getItemCount() {
        return listData.length;
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView name;
        TextView air_date;
        TextView episode;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            air_date = itemView.findViewById(R.id.air_date);
            episode = itemView.findViewById(R.id.episode);
        }
    }

}
