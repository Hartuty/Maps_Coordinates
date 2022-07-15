package com.abachapp.hava_mock.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.abachapp.hava_mock.Adapter.CustomAdapter;
import com.abachapp.hava_mock.R;
import com.abachapp.hava_mock.api.ApiClient;
import com.abachapp.hava_mock.api.HavaTrips;
import com.abachapp.hava_mock.model.HavaTrip;
import com.abachapp.hava_mock.model.Trip;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Result extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    List<Trip> result,temp1,temp2,temp3,temp4;
    TextView resultnum;
    boolean checked;
    String rdgrp1,rdgrp2;
    String word;
    MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        recyclerView=findViewById(R.id.recyclerview);
        resultnum=findViewById(R.id.resultnum);
        button=findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        checked=intent.getBooleanExtra("checked",false);
        word=intent.getStringExtra("word");
        rdgrp1=intent.getStringExtra("grp1");
        rdgrp2=intent.getStringExtra("grp2");
        Log.d("Checked",word);
        Log.d("rdgrp1",rdgrp1);
        Log.d("rdgrp2",rdgrp2);
        getresults();
    }

    private void getresults()
    {

        HavaTrips havaTrips = ApiClient.getRetrofit().create(HavaTrips.class);
        Call<HavaTrip> call=havaTrips.getTrips();
        call.enqueue(new Callback<HavaTrip>() {
            @Override
            public void onResponse(Call<HavaTrip> call, Response<HavaTrip> response) {
                result=response.body().getTrips();

                //generatedatalist(result);
                filterkeyword(result);
                //Log.d("Result",response.body().getTrips().get(1).getDriverName());
            }

            @Override
            public void onFailure(Call<HavaTrip> call, Throwable t) {
                new MaterialAlertDialogBuilder(getApplicationContext())
                        .setTitle("Warning")
                        .setMessage("Check your Internet Connection")
                        .setPositiveButton("Retry", (dialog, which) -> getresults()).show();
            }
        });


    }

    private void generatedatalist(List<Trip> h)
    {
        adapter= new CustomAdapter(h,this);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public List<Trip> get_trips()
    {
        return result;
    }


    void filterkeyword(List<Trip> result){
        temp1=new ArrayList<Trip>();
        temp2=new ArrayList<Trip>();
        temp3=new ArrayList<Trip>();
        temp4=new ArrayList<Trip>();
        if(word.equals("1"))
        {

           temp1.addAll(result);
        }
        else
        {
            for(Trip d: result){
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches
                if(d.getStatus().contains(word)){
                    temp1.add(d);
                }
                if(d.getCarMake().contains(word)){
                    temp1.add(d);
                }
                if(d.getCarModel().contains(word)){
                    temp1.add(d);
                }
                if(d.getCarNumber().contains(word)){
                    temp1.add(d);
                }
                if(d.getCost().toString().contains(word)){
                    temp1.add(d);
                }
                if(d.getDistance().toString().contains(word)){
                    temp1.add(d);
                }
                if(d.getDriverName().contains(word)){
                    temp1.add(d);
                }
                if(d.getDropoffLocation().contains(word)){
                    temp1.add(d);
                }
                if(d.getPickupLocation().contains(word)){
                    temp1.add(d);
                }
            }
        }
        //check for status checked

        if(checked){

            temp2.addAll(temp1);
        }
        else{
            for(Trip d: temp1){
                if(d.getStatus().equals("COMPLETED"))
                {
                    temp2.add(d);
                }
            }

        }
        //radio buttons distance

        switch (rdgrp1){
            case "1":
                temp3.addAll(temp2);
                break;
            case "2":
                for(Trip d: temp2){
                    if(d.getDistance()<3)
                    {
                        temp3.add(d);
                    }
                }
                break;

            case "3":
                for(Trip d: temp2){
                    if(d.getDistance()>=3&&d.getDistance()<=8)
                    {
                        temp3.add(d);
                    }
                }
                break;

            case "4":
                for(Trip d: temp2){
                    if(d.getDistance()>8&&d.getDistance()<=15)
                    {
                        temp3.add(d);
                    }
                }
                break;

            case "5":
                for(Trip d: temp2){
                    if(d.getDistance()>15)
                    {
                        temp3.add(d);
                    }
                }
                break;
        }

        //radio button time
        switch (rdgrp2){
            case "1":
                temp4.addAll(temp3);
                break;
            case "2":
                for(Trip d: temp3){
                    if(d.getDuration()<5)
                    {
                        temp4.add(d);
                    }
                }
                break;

            case "3":
                for(Trip d: temp3){
                    if(d.getDuration()>=5&&d.getDuration()<=10)
                    {
                        temp4.add(d);
                    }
                }
                break;

            case "4":
                for(Trip d: temp3){
                    if(d.getDistance()>10&&d.getDistance()<=20)
                    {
                        temp4.add(d);
                    }
                }
                break;

            case "5":
                for(Trip d: temp3){
                    if(d.getDistance()>20)
                    {
                        temp4.add(d);
                    }
                }
                break;
        }
        //display result
        resultnum.setText(" ("+String.valueOf(temp4.size())+")");
        generatedatalist(temp4);
    }

}