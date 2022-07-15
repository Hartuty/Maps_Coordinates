package com.abachapp.hava_mock.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HavaTrip {
    @SerializedName("trips")
    @Expose
    private List<Trip> trips = null;

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
