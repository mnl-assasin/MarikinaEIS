package com.olfu.meis.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mykelneds on 08/12/2016.
 */

public class TimeHelper2 {

    public static String getTimeStamp(Calendar cal2) {

        if (isToday(cal2)) {
            return getToday(cal2);
        } else if (isYesterday(cal2)) {
            return getYesterday(cal2);
        } else {
            return standardDateFormat(cal2);
        }

    }

    public static boolean isToday(Calendar cal2) {
        Calendar current = Calendar.getInstance();
        Log.d("Time", standardDateFormat(current));

        return current.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                current.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                current.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isYesterday(Calendar cal2) {
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DAY_OF_MONTH, -1);
        return current.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                current.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
                current.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    public static String getToday(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        return "Today at " + sdf.format(calendar.getTime());
    }

    public static String getYesterday(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        return "Yesterday at " + sdf.format(calendar.getTime());
    }


    public static String standardDateFormat(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM 'at' HH:mm a");
        return sdf.format(calendar.getTime());

    }

    public static String dateWithGMT(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM/dd 'at' HH:mm a");
        return sdf.format(calendar.getTime()) + " PHT";
    }

    public static Calendar setTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return c;
    }

    public static String convertDate(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
        return sdf.format(c.getTime());

    }

    public static Date getDate(String time){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
