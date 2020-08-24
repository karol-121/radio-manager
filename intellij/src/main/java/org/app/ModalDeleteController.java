package org.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static org.app.App.*;

public class ModalDeleteController {

    @FXML
    private Label txtInformation;

    @FXML
    public void initialize() {
        txtInformation.setText("Do you want to delete " + radioStationDataBase.getRadio(currentIndex).getName() + " radio?");
    }

    @FXML
    void cancel(ActionEvent event) {
        App.closeModal(txtInformation.getScene().getWindow());
    }

    @FXML
    void proceed(ActionEvent event) {
        radioStationDataBase.removeRadio(radioStationDataBase.getRadio(currentIndex));
        fileIsEdited = true;
        App.closeModal(txtInformation.getScene().getWindow());

    }
}
