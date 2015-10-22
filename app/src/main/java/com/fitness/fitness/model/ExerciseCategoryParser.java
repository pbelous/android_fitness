package com.fitness.fitness.model;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

public class ExerciseCategoryParser {
    private class ExerciseCategoryParserSaxHandler extends DefaultHandler {

        private List<ExerciseCategory> info;

        private ExerciseCategory tempCategory;
        private Exercise tempExercise;


        private final StringBuilder mStringBuilder = new StringBuilder();
        boolean element = false;

        public ExerciseCategoryParserSaxHandler() {
            info = new ArrayList<ExerciseCategory>();
        }

        public List<ExerciseCategory> getExerciseCategories() {
            return info;
        }

        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {

            if (qName.equalsIgnoreCase("category"))
            {
                tempCategory = new ExerciseCategory();
                tempCategory.setType(attributes.getValue("type"));
                tempCategory.setName(attributes.getValue("name"));
                tempCategory.setId(Integer.parseInt(attributes.getValue("id")));
                tempCategory.setIcon(attributes.getValue("icon"));
            }
            else if (qName.equalsIgnoreCase("exercise"))
            {
                tempExercise = new Exercise();
                tempExercise.setId(Integer.parseInt(attributes.getValue("id")));
                tempExercise.setIcon(attributes.getValue("icon"));
            }

            element = true;
            mStringBuilder.setLength(0);
        }

        public void characters(char[] ch, int start, int length)
                throws SAXException {

            if (element)
                mStringBuilder.append(ch, start, length);
        }

        public void endElement(String uri, String localName, String qName)
                throws SAXException {

            if (qName.equalsIgnoreCase("category"))
            {
                info.add(tempCategory);
            }
            else if (qName.equalsIgnoreCase("exercise"))
            {
                tempCategory.addExercise(tempExercise);
            }
            else if (qName.equalsIgnoreCase("title"))
            {
                tempExercise.setName(mStringBuilder.toString());
            }
            else if (qName.equalsIgnoreCase("html")) {
                tempExercise.setPath(mStringBuilder.toString());
            }

            mStringBuilder.setLength(0);
            element = false;
        }
    }

    public List<ExerciseCategory> parse(InputStream is)
    {
        List<ExerciseCategory> categories = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();

            ExerciseCategoryParserSaxHandler saxHandler = new ExerciseCategoryParserSaxHandler();

            xmlReader.setContentHandler(saxHandler);

            xmlReader.parse(new InputSource(is));

            categories = saxHandler.getExerciseCategories();

        } catch (Exception ex) {
            Log.d("XML", "SAXXMLParser: parse() failed");
            Log.d("XML", ex.getMessage());
        }

        return categories;
    }
}
