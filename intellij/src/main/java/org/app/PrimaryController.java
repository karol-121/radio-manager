package org.app;

import java.io.IOException;
import javafx.fxml.FXML;

import static org.app.App.radioStationDataBase;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {

        RadioStation radioStation = new RadioStation("193.185.0.10", "fm 1", "Country", "EN", "192", "1");
        radioStationDataBase.addRadio(radioStation);
    }
}
