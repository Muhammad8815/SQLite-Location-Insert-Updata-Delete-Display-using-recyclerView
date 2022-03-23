package com.example.addupdatelocationsqlite;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.MyViewHolder> {

    ArrayList<String> LocationNameArray;
    ArrayList<String> LocationDescriptionArry;
    ArrayList<Bitmap> bitmap;
    Context context;

    public Recycler_Adapter(ArrayList<String> locationNameArray, ArrayList<String> locationDescriptionArry, ArrayList<Bitmap> bitmap, Context context) {
        LocationNameArray = locationNameArray;
        LocationDescriptionArry = locationDescriptionArry;
        this.bitmap = bitmap;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.loname.setText(LocationNameArray.get(position));
        holder.lodesc.setText(LocationDescriptionArry.get(position));
        holder.limg.setImageBitmap(bitmap.get(position));
    }

    @Override
    public int getItemCount() {
        return LocationNameArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView limg;
        TextView loname,lodesc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            limg=itemView.findViewById(R.id.RImageView);
            loname=itemView.findViewById(R.id.RLName);
            lodesc=itemView.findViewById(R.id.RDesc);

        }
    }
}
