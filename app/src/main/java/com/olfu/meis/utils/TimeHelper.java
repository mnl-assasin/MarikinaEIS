package com.olfu.meis.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mykelneds on 08/12/2016.
 */

public class TimeHelper {

    public static Date getDate(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTimeStamp(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("MMM. dd, yyyy 'at' hh:mm aa");
//        SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy 'at' HH:mm a");
        return sdf.format(getDate(time));

    }


}
