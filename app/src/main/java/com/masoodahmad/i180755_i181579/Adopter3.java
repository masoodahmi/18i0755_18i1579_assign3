package com.masoodahmad.i180755_i181579;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adopter3 extends RecyclerView.Adapter<Adopter3.MyViewHolder> {
    List<chatss> ls;
    Context c;

    public Adopter3(List<chatss> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public Adopter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.chatssrow,parent,false);

        return new Adopter3.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adopter3.MyViewHolder holder, int position) {
        holder.sc.setText(ls.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sc=itemView.findViewById(R.id.singlechat);




        }
    }
}
