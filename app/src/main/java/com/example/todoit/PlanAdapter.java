package com.example.todoit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MyViewHolder> {
    Context context;
    ArrayList<myPlan> MyPlan;

    public PlanAdapter(Context c, ArrayList<myPlan> p){
        context = c;
        MyPlan = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.itemlist,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {

        holder.titleplan.setText(MyPlan.get(i).getTitleplan());
        holder.descplan.setText(MyPlan.get(i).getDescplan());
        holder.dateplan.setText(MyPlan.get(i).getDateplan());

        final String getTitleplan = MyPlan.get(i).getTitleplan();
        final String getDescplan = MyPlan.get(i).getDescplan();
        final String getDateplan = MyPlan.get(i).getDateplan();
        final String getkey = MyPlan.get(i).getkey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context, EditActivity.class);
                aa.putExtra("titleplan", getTitleplan);
                aa.putExtra("descplan", getDescplan);
                aa.putExtra("dateplan", getDateplan);
                aa.putExtra("key", getkey);
                context.startActivity(aa);

            }
        });

    }

    @Override
    public int getItemCount() {
        return MyPlan.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView titleplan, descplan, dateplan, key;
        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            titleplan = (TextView) itemView.findViewById(R.id.titleplan);
            descplan = (TextView) itemView.findViewById(R.id.descplan);
            dateplan = (TextView) itemView.findViewById(R.id.dateplan);



        }
    }
}
