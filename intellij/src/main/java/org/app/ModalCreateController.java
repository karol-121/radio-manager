package org.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.inputValidation.RadioAttributesValidation;

import static org.app.App.radioStationDataBase;
import static org.app.App.toggleInputValidation;

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

        try {
            //checking if any of input values does contain | (character used to separate values in file)
            //this validation should not be possible to turn of as injecting it will cause the file to be corrupted
            RadioAttributesValidation.superValidateRadio(txtName.getText()+txtGenre.getText()+txtLanguage.getText()+txtUrl.getText()+txtBitrate.getText());

            if(toggleInputValidation) {
                RadioAttributesValidation.validateRadioLanguage(txtLanguage.getText());
                RadioAttributesValidation.validateRadioBitrate(txtBitrate.getText());
            }

            RadioStation newRadioStation = new RadioStation(
                    txtUrl.getText(), txtName.getText(), txtGenre.getText(), txtLanguage.getText(), txtBitrate.getText(), checkBoxFavorite.isSelected()
            );
            radioStationDataBase.addRadio(newRadioStation);
            App.closeModal(txtName.getScene().getWindow());

        } catch (IllegalArgumentException e) {
            Alert openStateAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            openStateAlert.showAndWait();
        }


    }
}
