package com.fitness.fitness.utils;

import android.util.Log;

import com.fitness.fitness.model.ExerciseInfo;
import com.fitness.fitness.model.ExerciseInfoRecord;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ExerciseParserSaxHandler extends DefaultHandler {

    private List<ExerciseInfoRecord> info;
    //private String tempVal;
    private ExerciseInfoRecord tempEmp;

    private final StringBuilder mStringBuilder = new StringBuilder();
    boolean element = false;

    public ExerciseParserSaxHandler() {
        info = new ArrayList<ExerciseInfoRecord>();
    }

    public List<ExerciseInfoRecord> getExerciseInfo() {
        return info;
    }

    boolean checkHtmlTag(String qName)
    {
        /*
        if (qName.equalsIgnoreCase("b") ||
                qName.equalsIgnoreCase("li") ||
                qName.equalsIgnoreCase("font") ||
                qName.equalsIgnoreCase("br") ||
                qName.equalsIgnoreCase("u") ||
                qName.equalsIgnoreCase("pre") ||
                qName.equalsIgnoreCase("ul") ||
                qName.equalsIgnoreCase("html") ||
                qName.equalsIgnoreCase("i"))

        {
            return true;
        }
        */
        return false;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // reset
        //tempVal = "";
        if (qName.equalsIgnoreCase("exercise")) {
            // create a new instance of employee
            tempEmp = new ExerciseInfoRecord();
        }

        if (checkHtmlTag(qName)) {
            Log.i("--", "START uri="+uri+" localName=" + localName + " qName=" + qName);
            if (attributes.getLength() > 0)
                Log.i("--", " attr=Name=" + attributes.getLocalName(0) + " attr=Val=" + attributes.getValue(0));

            if (element) {
                mStringBuilder.append("<" + qName);

                if (attributes.getLength() > 0)
                {
                    mStringBuilder.append(" " + attributes.getQName(0) + "=\"" + attributes.getValue(0) + "\"");
                }
                mStringBuilder.append(">");
            }

            return;
        }

        element = true;
        mStringBuilder.setLength(0);
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {

       // tempVal = new String(ch, start, length);
        if (element)
            mStringBuilder.append(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (checkHtmlTag(qName)) {
            Log.i("--", "END uri="+uri+" localName=" + localName + " qName=" + qName);

            if (element)
                mStringBuilder.append("</" + qName + ">");
            return;
        }

        if (qName.equalsIgnoreCase("exercise")) {
            // add it to the list
            info.add(tempEmp);
        } else if (qName.equalsIgnoreCase("title")) {
            tempEmp.setName(mStringBuilder.toString());
        } else if (qName.equalsIgnoreCase("type")) {
            tempEmp.setType(mStringBuilder.toString());
        } else if (qName.equalsIgnoreCase("html")) {
            tempEmp.setPath(mStringBuilder.toString());
        }

        mStringBuilder.setLength(0);
        element = false;
    }
}
