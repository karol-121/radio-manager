package org.app;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.fileHandler.FileOpener;
import java.io.File;
import java.io.IOException;

import static org.app.App.*;

public class PrimaryController {

    //Window used for file opener
    private Window mainStage;

    @FXML
    private void chooseFile() {
        //file chooser
        FileChooser fC = new FileChooser();
        fC.setTitle("Open file");
        fC.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Live streams","live_streams.sii"));

        // file to be handled by file reader that will be implemented later
        File selectedFile = fC.showOpenDialog(mainStage);

        FileOpener fileOpener = new FileOpener();
        try {

            radioStationDataBase.setDatabase(fileOpener.readRadioStations(selectedFile.toPath()));
            liveStreamDef = fileOpener.readLiveStreamDef(selectedFile.toPath());
            System.out.println(liveStreamDef);

            //here: send to next scene
            setRoot("main");

            //System.out.println(radioStationDataBase.toString());

        } catch (IOException e) {
            System.err.println(e);
        }

    }

    @FXML
    private void exit() {
        Platform.exit();
    }
}
