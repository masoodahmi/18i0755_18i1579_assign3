package com.masoodahmad.i180755_i181579;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adopter1 extends RecyclerView.Adapter<Adopter1.MyViewHolder> {
    List<homee> ls;
    Context c;

    public Adopter1(List<homee> ls, Context c) {
        this.c=c;
        this.ls=ls;

    }


    @NonNull
    @Override
    public Adopter1.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.row1,parent,false);

        return new Adopter1.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(ls.get(position).getName());
        holder.text.setText(ls.get(position).getText());
        holder.time.setText(ls.get(position).getTime());
        Picasso.get().load(ls.get(position).getPic()).into(holder.pic);
        holder.openchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(c, chatting.class);

                c.startActivity(intent );
            }
        });


    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,text,time;
        CircleImageView pic;
        RelativeLayout openchat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            openchat=itemView.findViewById(R.id.openchat);

            name=itemView.findViewById(R.id.name);
            text=itemView.findViewById(R.id.text);
            time=itemView.findViewById(R.id.time);
            pic=itemView.findViewById(R.id.pic);

        }
    }
}
