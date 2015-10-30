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

    private static int themeId;
    public final static int THEME_1 = 0;
    public final static int THEME_2 = 1;
    public final static int THEME_3 = 2;


    public static void changeToTheme(Activity activity, int theme)
    {

        if (themeId != getCurrentThemeId(theme)) {
            themeId = getCurrentThemeId(theme);

            activity.finish();
            activity.startActivity(new Intent(activity, activity.getClass()));
        }
    }

    private static int getCurrentThemeId(int theme)
    {
        switch (theme)
        {
            case THEME_1:
                return R.style.activity_style1;
            case THEME_2:
                return R.style.activity_style2;
            case THEME_3:
            default:
                return R.style.activity_style3;
        }
    }

    public static int getCurrentTheme()
    {
        return themeId;
    }

    public static void onActivityCreateSetTheme(Activity activity)
    {
        activity.setTheme(themeId);
    }

    public static int getThemeId(Context ctx) {
        try {
            Class<?> wrapper = Context.class;
            Method method = wrapper.getMethod("getThemeResId");
            method.setAccessible(true);
            return (Integer) method.invoke(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
