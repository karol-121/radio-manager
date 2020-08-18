package org.fileHandler;

import javafx.collections.ObservableList;
import org.app.RadioStation;

import java.io.UnsupportedEncodingException;

public class RadioStationFormatter {

    public static String booleanConverter(Boolean isFavorite) {
        if (isFavorite) {
            return "1";
        } else {
            return "0";
        }
    }

    public static String inputConverter(String input) throws UnsupportedEncodingException {
        byte[] bytes = input.getBytes("UTF-8");
        return new String (bytes);
    }

    public static String formatRadioStation(RadioStation radioStation) {
        return radioStation.getUrl() + "|" + radioStation.getName() + "|" + radioStation.getGenre() + "|" + radioStation.getLanguage() + "|" + radioStation.getBitrate() + "|" + booleanConverter(radioStation.getFavorite());
    }

    public static String formatRadioStationArray (ObservableList radioStations) {
        StringBuilder returnString = new StringBuilder();
        returnString.append(" stream_data: " + radioStations.size() + "\n");

        for (int i = 0; i < radioStations.size(); i++) {
            returnString.append(" stream_data[" + i + "]: \"");
            String s = formatRadioStation((RadioStation) radioStations.get(i));
            returnString.append(s);
            returnString.append("\"\n");
        }

        try {
            return inputConverter(returnString.toString());
        } catch (UnsupportedEncodingException e) {
            //coverting is skipped if coverter fails to convert
            return returnString.toString();
        }


    }
}
