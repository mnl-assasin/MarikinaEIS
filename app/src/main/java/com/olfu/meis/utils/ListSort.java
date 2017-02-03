package com.olfu.meis.utils;

import java.util.Date;

/**
 * Created by mykelneds on 03/02/2017.
 */

public class ListSort implements Comparable<ListSort>{
    private Date dateTime;

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date datetime) {
        this.dateTime = datetime;
    }

    @Override
    public int compareTo(ListSort o) {
        return getDateTime().compareTo(o.getDateTime());
    }
}
