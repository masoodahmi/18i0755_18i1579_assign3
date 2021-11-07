package com.masoodahmad.i180755_i181579;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyRvAdopter extends RecyclerView.Adapter<MyRvAdopter.MyViewHolder> {
    List<callhist> ls;
    Context c;
    public MyRvAdopter(List<callhist> ls, Context c) {
        this.c=c;
        this.ls=ls;

    }

    @NonNull
    @Override
    public MyRvAdopter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.row,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvAdopter.MyViewHolder holder, int position) {
        holder.name.setText(ls.get(position).getName());
        holder.time.setText(ls.get(position).getTime());
        holder.pic.setImageBitmap(ls.get(position).getPic());
        if(ls.get(holder.getAdapterPosition()).getArrow().equals("outbound")){
            holder.arro.setImageResource(R.drawable.outarrow);
        }
        else{
            holder.arro.setImageResource(R.drawable.missarrow);
        }

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,stat,time;
        ImageView arro;
        CircleImageView pic;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            stat=itemView.findViewById(R.id.stat);
            time=itemView.findViewById(R.id.time);
            arro=itemView.findViewById(R.id.arrow);
            pic=itemView.findViewById(R.id.pic);






        }
    }
}
