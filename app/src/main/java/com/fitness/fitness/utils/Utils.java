package com.fitness.fitness.utils;

import android.util.Log;

import java.util.GregorianCalendar;

public class Utils {

    public static String getCurrentDate()
    {

        GregorianCalendar month = (GregorianCalendar) GregorianCalendar.getInstance();
        String d = android.text.format.DateFormat.format("yyyy-MM-dd", month).toString();

        return d;
    }


}
