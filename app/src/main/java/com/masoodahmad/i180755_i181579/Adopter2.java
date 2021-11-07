package com.masoodahmad.i180755_i181579;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adopter2 extends RecyclerView.Adapter<Adopter2.MyViewHolder> {
    List<contacts> ls;
    Context c;

    public Adopter2(List<contacts> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public Adopter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.contactsrow,parent,false);

        return new Adopter2.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adopter2.MyViewHolder holder, int position) {
        holder.name.setText(ls.get(position).getName());
        holder.phnum.setText(ls.get(position).getNumber());




    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phnum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            phnum=itemView.findViewById(R.id.phnum);

        }
    }
}
