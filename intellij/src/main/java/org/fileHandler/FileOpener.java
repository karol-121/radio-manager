package org.fileHandler;

import org.app.RadioStation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileOpener {
    private ArrayList<RadioStation> radioStationlist = new ArrayList<>();
    private int lineCounter = 0;

    public ArrayList<RadioStation> readRadioStations(Path path) throws IOException {

        try (var reader = Files.newBufferedReader(path)) {
            String line;

            // TODO: 04.06.2020 do a check and print error if no stream data was found
            while((line = reader.readLine()) != null) {

                //given that we count lines from 1 and not from 0
                ++lineCounter;

                if (line.contains("stream_data[")) {
                    RadioStation radioStation = RadioStationParser.parseRadioStation(line);
                    radioStationlist.add(radioStation);
                }
            }
        }

        if(!radioStationlist.isEmpty()) {
            return radioStationlist;
        } else {
            throw new IOException("No radio stations was found in this file.");
        }
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

        if(!liveStreamDef.isEmpty()) {
            return liveStreamDef;
        } else {
            throw new IOException("Live stream definition value was not found in this file.");
        }
    }

    public int getLineCounter(){
        return lineCounter;
    }
}
