package org.app;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.fileHandler.RadioStationParser;

import java.io.File;
import static org.app.App.radioStationDataBase;

public class PrimaryController {

    //Window used for file opener
    private Window mainStage;

    @FXML
    private void chooseFile() {
        //file chooser
        /*FileChooser fC = new FileChooser();
        fC.setTitle("Open file");
        fC.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Live streams","live_streams.sii"));

        // file to be handled by file reader that will be implemented later
        File selectedFile = fC.showOpenDialog(mainStage);*/

        RadioStationParser.parseRadioStation("stream_data[0]: \"http://205.164.62.22:7800|1.FM - Absolutely Country Hits|Country|EN|128|1\"");
    }

    @FXML
    private void exit() {
        Platform.exit();
    }
}
