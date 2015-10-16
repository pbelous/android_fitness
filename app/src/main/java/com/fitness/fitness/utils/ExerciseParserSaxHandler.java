package com.fitness.fitness.utils;

import com.fitness.fitness.model.ExerciseInfo;
import com.fitness.fitness.model.ExerciseInfoRecord;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ExerciseParserSaxHandler extends DefaultHandler {

    private List<ExerciseInfoRecord> info;
    private String tempVal;
    private ExerciseInfoRecord tempEmp;

    public ExerciseParserSaxHandler() {
        info = new ArrayList<ExerciseInfoRecord>();
    }

    public List<ExerciseInfoRecord> getExerciseInfo() {
        return info;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("exercise")) {
            // create a new instance of employee
            tempEmp = new ExerciseInfoRecord();
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
