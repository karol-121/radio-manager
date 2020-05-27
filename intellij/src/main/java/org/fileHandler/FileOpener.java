package org.fileHandler;

import org.app.RadioStation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileOpener {

    public ArrayList<RadioStation> readRadioStations(Path path) throws IOException {
        ArrayList<RadioStation> radioStationlist = new ArrayList<>();

        try (var reader = Files.newBufferedReader(path)) {
            String line;

            while((line = reader.readLine()) != null) {

                if (line.contains("stream_data[")) {
                    RadioStation radioStation = RadioStationParser.parseRadioStation(line);
                    radioStationlist.add(radioStation);
                }
            }
        }

        return radioStationlist;
    }

    public String readLiveStreamDef(Path path) throws IOException {
        String liveStreamDef = "";

        try (var reader = Files.newBufferedReader(path)){
            String line;

            while((line = reader.readLine()) != null) {

                if (line.contains("live_stream_def")) {
                    liveStreamDef = LiveStreamDefParser.parseLiveStreamDef(line);
                }
            }
        }

        //there is possibility for method to return empty string upon failure that can cause problems later in program
        // TODO: 28.05.2020 rewrite this or make sure htat this will not cause problems later on
        return liveStreamDef;
    }
}
