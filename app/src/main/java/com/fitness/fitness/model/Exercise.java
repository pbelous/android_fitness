package com.fitness.fitness.model;

import android.content.Context;
import android.content.res.Resources;

import com.fitness.fitness.R;
import com.fitness.fitness.utils.Utils;

import java.util.ArrayList;

public class Exercise {
    public String name;
    public String type;
    public String path;
    public int id;
    public String icon;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type) { this.type = type; }

    public void setPath(String path)
    {
        this.path = path;
    }

    public void setIcon(String icon) { this.icon = icon; }

    public void setId(int id)
    {
        this.id = id;
    }
}
