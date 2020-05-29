package org.fileHandler;

import javafx.collections.ObservableList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.app.App.liveStreamDef;

public class FileSaver {

    public void saveRadioStations(Path path, ObservableList array) throws IOException {
        StringBuilder stringToSave = new StringBuilder();
        stringToSave.append("SiiNunit\n{\nlive_stream_def : "+ liveStreamDef + " {\n");
        stringToSave.append(RadioStationFormatter.formatRadioStationArray(array));
        stringToSave.append("}\n\n}");

        Files.write(path, stringToSave.toString().getBytes());

    }
}
