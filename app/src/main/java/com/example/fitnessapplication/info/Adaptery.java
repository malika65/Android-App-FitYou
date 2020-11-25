package com.example.fitnessapplication.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fitnessapplication.R;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private Context mContext;
    private List<Info> infoList;

    public Adaptery(Context mContext, List<Info> infoList) {
        this.mContext = mContext;
        this.infoList = infoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.info_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(infoList.get(position).getId());
        holder.text.setText(infoList.get(position).getName());

        Glide.with(mContext)
                .load(infoList.get(position).getImage())
                .into(holder.img);


    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        TextView id;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.txtView);
            id = itemView.findViewById(R.id.txtViewId);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
