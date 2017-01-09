package com.olfu.meis.data;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by mykelneds on 13/12/2016.
 */

public class EZSharedPreferences {

    private final static String USER_PREFERENCES = "MarikinaEIS";
    
    public final static String KEY_MAGNITUDE = "Magnitude";
    public final static String KEY_DISTANCE = "Distance";
    public final static String KEY_TIME = "TimeFrame";
    public final static String KEY_SAVE_FILTER = "SaveFilterPref";

    public static SharedPreferences getSharedPref(Context ctx) {
        return ctx.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void dropSharedPref(Context ctx) {
        SharedPreferences.Editor editor = getSharedPref(ctx).edit();
        editor.clear();
        editor.apply();
    }

    /**
     * G E T T E R
     */

    public static int getMagnitudePref(Context ctx){
        return getSharedPref(ctx).getInt(KEY_MAGNITUDE, 0);
    }


    /**
     * S E T T E R
     */


}
