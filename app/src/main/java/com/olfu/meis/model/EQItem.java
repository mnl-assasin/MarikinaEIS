package com.olfu.meis.model;

import android.support.annotation.NonNull;

import com.olfu.meis.utils.TimeHelper2;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by mykelneds on 02/02/2017.
 */

public class EQItem implements Comparable<EQItem> {

    double magnitude;
    String location;
    double latitude;
    double longitude;
    String depth;
    String timeStamp;
    Date time;

    public EQItem(double magnitude, String location, double latitude, double longitude, String depth, String timeStamp) {
        this.magnitude = magnitude;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.timeStamp = timeStamp;
        time = TimeHelper2.getDate(timeStamp);

    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDepth() {
        return depth;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public Date getTime() {
        return time;
    }

    @Override
    public int compareTo(@NonNull EQItem eqItem) {
        return eqItem.getTime().compareTo(getTime());
    }

    public static ArrayList<EQItem> latestMapItem = new ArrayList<>();
    public static ArrayList<EQItem> forecastMapItem = new ArrayList<>();
    public static ArrayList<EQItem> aftershockMapItem = new ArrayList<>();
}
