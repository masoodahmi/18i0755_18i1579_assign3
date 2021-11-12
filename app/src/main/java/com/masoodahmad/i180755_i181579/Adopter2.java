package com.masoodahmad.i180755_i181579;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(ls.get(position).getName());
        holder.phnum.setText(ls.get(position).getNumber());
        String imgpath = ls.get(position).getImg().toString();
        if (imgpath.substring(0, 5).equals("https")) {
            Picasso.get().load(ls.get(position).getImg()).into(holder.civ);
        }
        else{
            holder.civ.setImageURI(ls.get(position).getImg());
        }
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, chatting.class);
                intent.putExtra("userid", ls.get(holder.getAdapterPosition()).getEmail());
                intent.putExtra("username", ls.get(holder.getAdapterPosition()).getName());
                intent.putExtra("userpic", ls.get(holder.getAdapterPosition()).getImg());
                c.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phnum;
        CircleImageView civ;
        LinearLayout ll;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            phnum=itemView.findViewById(R.id.phnum);
            civ = itemView.findViewById(R.id.pic);
            ll = itemView.findViewById(R.id.ll);


        }
    }
}
