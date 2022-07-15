package com.abachapp.hava_mock.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abachapp.hava_mock.R;
import com.abachapp.hava_mock.model.Trip;
import com.abachapp.hava_mock.views.Details;
import com.abachapp.hava_mock.views.Result;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<Trip> datalist;
    private Context context;
    private int num;

    public CustomAdapter() {
    }

    public CustomAdapter(List<Trip> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
        this.num=getItemCount();
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.one_trip,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.textView.setText(datalist.get(position).getRequestDate());
        holder.textView1.setText(datalist.get(position).getDropoffLocation());
        holder.textView2.setText(datalist.get(position).getPickupLocation());
        holder.textView3.setText(datalist.get(position).getCost().toString()+" KES");
        holder.textView4.setText(datalist.get(position).getDriverRating().toString());
        holder.textView5.setTextColor(Color.GREEN);
        holder.textView5.setText(datalist.get(position).getStatus());

       holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, Details.class);
            intent.putExtra("pickupdate",datalist.get(position).getPickupDate());
            intent.putExtra("amount",datalist.get(position).getCost().toString());
            intent.putExtra("drop",datalist.get(position).getDropoffLocation());
           intent.putExtra("pick",datalist.get(position).getPickupLocation());
           intent.putExtra("dropofftime",datalist.get(position).getDropoffDate());
           intent.putExtra("distance",datalist.get(position).getDistance().toString());
           intent.putExtra("duration",datalist.get(position).getDuration().toString());
           intent.putExtra("carimage",datalist.get(position).getCarPic());
           intent.putExtra("driverimage",datalist.get(position).getDriverPic());
           intent.putExtra("drivername",datalist.get(position).getDriverName());
           intent.putExtra("carname",datalist.get(position).getCarModel());
           intent.putExtra("startlat",datalist.get(position).getPickupLat().toString());
           intent.putExtra("droplat",datalist.get(position).getDropoffLat().toString());
           intent.putExtra("startlng",datalist.get(position).getPickupLng().toString());
           intent.putExtra("droplng",datalist.get(position).getDropoffLng().toString());

           context.startActivity(intent);
        });

    }

    public void updateList(List<Trip> list){
        datalist = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView textView;
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        TextView textView5;
        TextView textView6;

        public CustomViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView=mView.findViewById(R.id.timedate);
            textView1=mView.findViewById(R.id.destination);
            textView2=mView.findViewById(R.id.source);
            textView3=mView.findViewById(R.id.amount);
            textView4=mView.findViewById(R.id.rating);
            textView5=mView.findViewById(R.id.status);
        }
    }
}
