package com.fitness.fitness.utils;

import android.util.Log;

import com.fitness.fitness.model.ExerciseInfo;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by pbelous on 15.10.2015.
 */
public class ExerciseParser {
    public static List<ExerciseInfo> parse(InputStream is) {
        List<ExerciseInfo> info = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();

            ExerciseParserSaxHandler saxHandler = new ExerciseParserSaxHandler();

            xmlReader.setContentHandler(saxHandler);

            xmlReader.parse(new InputSource(is));

            info = saxHandler.getExerciseInfo();

        } catch (Exception ex) {
            Log.d("XML", "SAXXMLParser: parse() failed");
        }

        return info;
    }
}
