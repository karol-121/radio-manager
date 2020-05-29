package org.fileHandler;

import javafx.collections.ObservableList;
import org.app.RadioStation;

public class RadioStationFormatter {

    public static String formatRadioStation(RadioStation radioStation) {
        return radioStation.getUrl() + "|" + radioStation.getName() + "|" + radioStation.getGenre() + "|" + radioStation.getLanguage() + "|" + radioStation.getBitrate() + "|" + radioStation.getFavorite();
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



        return  returnString.toString();
    }
}
