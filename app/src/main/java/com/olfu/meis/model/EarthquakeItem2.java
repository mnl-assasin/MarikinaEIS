package com.olfu.meis.model;

import java.util.ArrayList;

/**
 * Created by mykelneds on 05/12/2016.
 */

public class EarthquakeItem2 {

    double magnitude;
    String location;
    double latitude;
    double longitude;
    String depth;
    String timeStamp;
    boolean isControlVisible;

    public EarthquakeItem2(double magnitude, String location, double latitude, double longitude, String depth, String timeStamp, boolean isControlVisible) {
        this.magnitude = magnitude;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.timeStamp = timeStamp;
        this.isControlVisible = isControlVisible;
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

    public boolean isControlVisible() {
        return isControlVisible;
    }

    public void setControlVisible(boolean controlVisible) {
        isControlVisible = controlVisible;
    }

    public static ArrayList<EarthquakeItem2> getList() {

        ArrayList<EarthquakeItem2> item = new ArrayList<>();
        item.add(new EarthquakeItem2(3.5, "35 Buffalo Street, Marikina, 1811 Metro Manila", 14.647307, 121.120855, "10 m", "12/14/2016 08:30", false));
        item.add(new EarthquakeItem2(2.5, "24 Highland Street, Marikina, 1811 Metro Manila", 14.646102, 121.118667, "8 m", "12/14/2016 11:30", false));
        item.add(new EarthquakeItem2(3.7, "150 Gen. Ordoñez Ave. Marikina", 14.657189, 121.120177, "18 m", "12/13/2016 18:30", false));
        item.add(new EarthquakeItem2(6.3, "95 Ipil, Marikina 1810", 14.647515, 121.119227, "32 m", "12/13/2016 12:46", false));
        item.add(new EarthquakeItem2(1.1, "102 Mahogany Park 1, Marikina, 1810", 14.647774, 121.119206, "19 m", "12/12/2016 20:30", false));
        item.add(new EarthquakeItem2(7.1, "Marikina Heights", 14.648783, 121.120786, "80 m", "12/12/2016 01:30", false));
        item.add(new EarthquakeItem2(6.5, "6 Hackney Street, Marikina 1811 ", 14.645960, 121.121957, "90 m", "12/12/2016 12:30", false));
        return item;
    }

    public static ArrayList<EarthquakeItem2> getForecast() {

        ArrayList<EarthquakeItem2> item = new ArrayList<>();
        item.add(new EarthquakeItem2(6.2, "95 Ipil, Marikina 1810", 14.647515, 121.119227, "10 m", "12/20/2016 08:30", false));
        item.add(new EarthquakeItem2(1.5, "6 Hackney Street, Marikina 1811 ", 14.645960, 121.121957, "8 m", "12/20/2016 11:30", false));
        item.add(new EarthquakeItem2(4.1, "150 Gen. Ordoñez Ave. Marikina", 14.657189, 121.120177, "18 m", "12/19/2016 18:30", false));
        item.add(new EarthquakeItem2(2.3, "102 Mahogany Park 1, Marikina, 1810", 14.647774, 121.119206, "19 m", "12/18/2016 12:46", false));
        item.add(new EarthquakeItem2(2.2, "35 Buffalo Street, Marikina, 1811 Metro Manila", 14.647307, 121.120855, "32 m", "12/18/2016 20:30", false));
        item.add(new EarthquakeItem2(1.6, "Marikina Heights", 14.648783, 121.120786, "80 m", "12/18/2016 01:30", false));
        item.add(new EarthquakeItem2(3.9, "24 Highland Street, Marikina, 1811 Metro Manila", 14.646102, 121.118667, "90 m", "12/17/2016 12:30", false));
        return item;
    }

    public static ArrayList<EarthquakeItem2> mapItemsm = new ArrayList<>();
    public static ArrayList<EarthquakeItem2> mapItemForecast = new ArrayList<>();
}
