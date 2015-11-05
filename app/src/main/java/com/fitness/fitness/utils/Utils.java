package com.fitness.fitness.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;

import com.fitness.fitness.R;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {

    public static int lastSelectedCategory = 0;

    public static String getCurrentDate()
    {

        GregorianCalendar month = (GregorianCalendar) GregorianCalendar.getInstance();
        return android.text.format.DateFormat.format("yyyy-MM-dd", month).toString();
    }

    public static int getDrawableIdByName(Context context, String name)
    {
        Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(name, "drawable",
                context.getPackageName());
        return resourceId;
    }

    // -1 date1 < date2; 0 date1 = date2; 1 date1 < date2
    public static int compareDate(String d1, String d2)
    {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date date1 = formatter.parse(d1);

            Date date2 = formatter.parse(d2);

            return date1.compareTo(date2);

        }catch (Exception e1){
            e1.printStackTrace();
        }

        return 0;
    }

}
