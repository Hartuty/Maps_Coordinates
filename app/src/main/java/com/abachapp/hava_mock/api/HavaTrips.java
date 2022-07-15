package com.abachapp.hava_mock.api;

import com.abachapp.hava_mock.model.HavaTrip;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HavaTrips {
    @GET("recent.json")
    Call<HavaTrip> getTrips();
}
