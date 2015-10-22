package com.fitness.fitness.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import java.util.GregorianCalendar;

public class Utils {

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


}
