package com.masoodahmad.i180755_i181579;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adopter3 extends RecyclerView.Adapter<Adopter3.MyViewHolder> {
    List<chatss> ls;
    Context c;
    SManager sManger;

    public Adopter3(List<chatss> ls, Context c) {
        this.ls = ls;
        this.c = c;
        this.sManger = new SManager(c);
       // setHasStableIds(true);
    }
    @NonNull
    @Override
    public Adopter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(c).inflate(R.layout.chatssrow,parent,false);

        return new Adopter3.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Adopter3.MyViewHolder holder, int position) {


        if (sManger.getUsername().equals(ls.get(position).getSrc())){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.sc.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.sc.setLayoutParams(params);
            holder.sc.setBackgroundResource(R.drawable.chatbckground);
            params = (RelativeLayout.LayoutParams)holder.rl.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.rl.setLayoutParams(params);
        }
        else{
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.rl.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.rl.setLayoutParams(params);
            params = (RelativeLayout.LayoutParams)holder.sc.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.sc.setLayoutParams(params);

            holder.sc.setBackgroundResource(R.drawable.chatbackground2);
        }
        if(ls.get(position).getText().equals("Screen shot has been taken")){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)holder.rl.getLayoutParams();
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            holder.rl.setLayoutParams(params);

            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams)holder.rl.getLayoutParams();
            params1.setMargins(0,0,320,0);
            holder.sc.setBackgroundResource(R.drawable.chatbackground3);
            holder.sc.setTextSize(10);



        }

        holder.sc.setText(ls.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sc;
        RelativeLayout rl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.rl);
            sc=itemView.findViewById(R.id.singlechat);




        }
    }
}
