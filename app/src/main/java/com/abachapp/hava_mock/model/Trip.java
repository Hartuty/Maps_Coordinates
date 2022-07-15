package com.abachapp.hava_mock.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("request_date")
    @Expose
    private String requestDate;
    @SerializedName("pickup_lat")
    @Expose
    private Double pickupLat;
    @SerializedName("pickup_lng")
    @Expose
    private Double pickupLng;
    @SerializedName("pickup_location")
    @Expose
    private String pickupLocation;
    @SerializedName("dropoff_lat")
    @Expose
    private Double dropoffLat;
    @SerializedName("dropoff_lng")
    @Expose
    private Double dropoffLng;
    @SerializedName("dropoff_location")
    @Expose
    private String dropoffLocation;
    @SerializedName("pickup_date")
    @Expose
    private String pickupDate;
    @SerializedName("dropoff_date")
    @Expose
    private String dropoffDate;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("driver_id")
    @Expose
    private Integer driverId;
    @SerializedName("driver_name")
    @Expose
    private String driverName;
    @SerializedName("driver_rating")
    @Expose
    private Integer driverRating;
    @SerializedName("driver_pic")
    @Expose
    private String driverPic;
    @SerializedName("car_make")
    @Expose
    private String carMake;
    @SerializedName("car_model")
    @Expose
    private String carModel;
    @SerializedName("car_number")
    @Expose
    private String carNumber;
    @SerializedName("car_year")
    @Expose
    private Integer carYear;
    @SerializedName("car_pic")
    @Expose
    private String carPic;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("duration_unit")
    @Expose
    private String durationUnit;
    @SerializedName("distance")
    @Expose
    private Double distance;
    @SerializedName("distance_unit")
    @Expose
    private String distanceUnit;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("cost_unit")
    @Expose
    private String costUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public Double getPickupLat() {
        return pickupLat;
    }

    public void setPickupLat(Double pickupLat) {
        this.pickupLat = pickupLat;
    }

    public Double getPickupLng() {
        return pickupLng;
    }

    public void setPickupLng(Double pickupLng) {
        this.pickupLng = pickupLng;
    }

    public String getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(String pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public Double getDropoffLat() {
        return dropoffLat;
    }

    public void setDropoffLat(Double dropoffLat) {
        this.dropoffLat = dropoffLat;
    }

    public Double getDropoffLng() {
        return dropoffLng;
    }

    public void setDropoffLng(Double dropoffLng) {
        this.dropoffLng = dropoffLng;
    }

    public String getDropoffLocation() {
        return dropoffLocation;
    }

    public void setDropoffLocation(String dropoffLocation) {
        this.dropoffLocation = dropoffLocation;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(String dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getDriverRating() {
        return driverRating;
    }

    public void setDriverRating(Integer driverRating) {
        this.driverRating = driverRating;
    }

    public String getDriverPic() {
        return driverPic;
    }

    public void setDriverPic(String driverPic) {
        this.driverPic = driverPic;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Integer getCarYear() {
        return carYear;
    }

    public void setCarYear(Integer carYear) {
        this.carYear = carYear;
    }

    public String getCarPic() {
        return carPic;
    }

    public void setCarPic(String carPic) {
        this.carPic = carPic;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDurationUnit() {
        return durationUnit;
    }

    public void setDurationUnit(String durationUnit) {
        this.durationUnit = durationUnit;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getDistanceUnit() {
        return distanceUnit;
    }

    public void setDistanceUnit(String distanceUnit) {
        this.distanceUnit = distanceUnit;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getCostUnit() {
        return costUnit;
    }

    public void setCostUnit(String costUnit) {
        this.costUnit = costUnit;
    }
}
