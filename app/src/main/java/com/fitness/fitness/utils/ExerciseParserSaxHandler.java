package com.fitness.fitness.utils;

import com.fitness.fitness.model.ExerciseInfo;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ExerciseParserSaxHandler extends DefaultHandler {

    private List<ExerciseInfo> info;
    private String tempVal;
    private ExerciseInfo tempEmp;

    public ExerciseParserSaxHandler() {
        info = new ArrayList<ExerciseInfo>();
    }

    public List<ExerciseInfo> getExerciseInfo() {
        return info;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("exercise")) {
            // create a new instance of employee
            tempEmp = new ExerciseInfo();
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("exercise")) {
            // add it to the list
            info.add(tempEmp);
        } else if (qName.equalsIgnoreCase("name")) {
            tempEmp.setName(tempVal);
        } else if (qName.equalsIgnoreCase("icon")) {
            tempEmp.setIcon(tempVal);
        } else if (qName.equalsIgnoreCase("description")) {
            tempEmp.setDescription(tempVal);
        }
    }
}
