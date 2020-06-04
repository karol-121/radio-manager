package org.fileHandler;

import org.app.RadioStation;
import org.inputValidation.RadioAttributesValidation;

public class RadioStationParser {

    private static Boolean booleanConverter(String value) {
        if (value.matches("0")) {
            return false;
        } else if (value.matches("1")) {
            return true;
        } else {
            throw new IllegalArgumentException("Favorite attribute contains illegal value");
        }

    }

    //this parser is created to handle only lines with radio attributes. File opener does detect if given line does contain radio attributes or not.
    //example of supported line: stream_data[0]: "http://205.164.62.22:7800|1.FM - Absolutely Country Hits|Country|EN|128|1"
    public static RadioStation parseRadioStation(String line) throws IllegalArgumentException {

        //this will throw exceptions if provided line is wrong syntax.
        //the parser assume that line is going to be split to 7 parts
        //there should also be some kind of validator to detect if data are correct
        String[] parts = line.split("((\")|(\\|))");

        if (parts.length != 7) {
            // TODO: 26.05.2020 later find better exception to be throw
            throw new IllegalArgumentException("Illegal attribute amount (check attribute separator \"|\")");
        }

        //here validate attributes, throws illegalArgumentException if input is invalid
        RadioAttributesValidation.validateRadioLanguage(parts[4]);
        RadioAttributesValidation.validateRadioBitrate(parts[5]);

        RadioStation radioStation = new RadioStation(parts[1],parts[2], parts[3], parts[4], parts[5], booleanConverter(parts[6]));

        //toString if needed
        /*for(String part : parts) {
            System.out.println(part);
        }*/

        return radioStation;
    }
}
