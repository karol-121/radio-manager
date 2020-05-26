package org.fileHandler;

import org.app.RadioStation;

public class RadioStationParser {

    //this parser is created to handle only lines with radio attributes. File opener does detect if given line does contain radio attributes or not.
    //example of supported line: stream_data[0]: "http://205.164.62.22:7800|1.FM - Absolutely Country Hits|Country|EN|128|1"
    public static RadioStation parseRadioStation(String line) throws IllegalArgumentException {

        //this will throw exceptions if provided line is wrong syntax.
        //the parser assume that line is going to be splitted to atleast 7 parts
        //there should also be some kind of validator to detect if data are correct
        String[] parts = line.split("((\")|(\\|))");

        if (parts.length != 6) {
            // TODO: 26.05.2020 later find better exception to be throw
            throw new IllegalArgumentException("provided radio station attributes are wrong format");
        }

        // TODO: 26.05.2020 this parser need input validation, this is to be added if validation methods are created 
        RadioStation radioStation = new RadioStation(parts[1],parts[2], parts[3], parts[4], parts[5], parts[6]);

        //toString if needed
        /*for(String part : parts) {
            System.out.println(part);
        }*/

        return radioStation;
    }
}
