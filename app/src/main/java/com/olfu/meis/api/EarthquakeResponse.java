package com.olfu.meis.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mykelneds on 11/01/2017.
 */

public class EarthquakeResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("address")
    private String address;

    @SerializedName("date_time")
    private String dateTime; // YYYY-MM-DD HH:MM:SS

    @SerializedName("magnitude")
    private double magnitude;

//    @SerializedName("intensity")
//    private

    @SerializedName("depth")
    private double depth;


    public EarthquakeResponse(int id, double latitude, double longitude, String address, String dateTime, double magnitude, double depth) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.dateTime = dateTime;
        this.magnitude = magnitude;
        this.depth = depth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
