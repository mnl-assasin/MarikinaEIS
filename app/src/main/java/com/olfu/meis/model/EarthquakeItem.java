package com.olfu.meis.model;

import java.util.ArrayList;

/**
 * Created by mykelneds on 05/12/2016.
 */

public class EarthquakeItem {

    double magnitude;
    String location;
    double distance;
    double latitude;
    double longitude;
    String depth;
    String timeStamp;


    public EarthquakeItem(double magnitude, String location, double latitude, double longitude, String depth, String timeStamp) {
        this.magnitude = magnitude;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.timeStamp = timeStamp;
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

    public double getDistance() {
        return distance;
    }

    public static ArrayList<EarthquakeItem> getList() {

        ArrayList<EarthquakeItem> item = new ArrayList<>();
        item.add(new EarthquakeItem(3.5, "35 Buffalo Street, Marikina, 1811 Metro Manila", 14.647307, 121.120855, "10 m", "12/08/2016 08:30"));
        item.add(new EarthquakeItem(2.5, "24 Highland Street, Marikina, 1811 Metro Manila", 14.646102, 121.118667, "8 m", "12/08/2016 11:30"));
        item.add(new EarthquakeItem(3.7, "150 Gen. Ordoñez Ave. Marikina", 14.657189, 121.120177, "18 m", "12/07/2016 18:30"));
        item.add(new EarthquakeItem(6.3, "95 Ipil, Marikina 1810", 14.647515, 121.119227, "32 m", "12/06/2016 12:46"));
        item.add(new EarthquakeItem(1.1, "102 Mahogany Park 1, Marikina, 1810", 14.647774, 121.119206, "19 m", "12/03/2016 20:30"));
        item.add(new EarthquakeItem(7.1, "Marikina Heights", 14.648783, 121.120786, "80 m", "12/02/2016 01:30"));
        item.add(new EarthquakeItem(6.5, "6 Hackney Street, Marikina 1811 ", 14.645960, 121.121957, "90 m", "12/02/2016 12:30"));
        return item;
    }
}
