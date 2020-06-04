package org.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import static org.app.App.radioStationDataBase;

public class ModalCreateController {
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtLanguage;

    @FXML
    private TextField txtUrl;

    @FXML
    private TextField txtBitrate;

    @FXML
    private CheckBox checkBoxFavorite;

    @FXML
    void add(ActionEvent event) {
        RadioStation newRadioStation = new RadioStation(
                txtUrl.getText(), txtName.getText(), txtGenre.getText(), txtLanguage.getText(), txtBitrate.getText(), checkBoxFavorite.isSelected()
        );
        radioStationDataBase.addRadio(newRadioStation);
        App.closeModal(txtName.getScene().getWindow());

    }
}
