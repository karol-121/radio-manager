package org.fileHandler;

public class LiveStreamDefParser {

    //this parser is created to handle only lines with live stream definition. File opener does detect if given line does contain live stream definition or not.
    //live_stream_def : _nameless.23c.de90.c710 {
    public static String parseLiveStreamDef(String liveStreamDef) {

        String[] parts = liveStreamDef.split(" ");

        //for now this provides syntax validation
        // TODO: 28.05.2020 learn more about def syntax and create validation for this
        if (parts.length != 4) {
            // TODO: 26.05.2020 later find better exception to be throw
            throw new IllegalArgumentException("provided radio station attributes are wrong format");
        }

        return parts[2];
    }
}
