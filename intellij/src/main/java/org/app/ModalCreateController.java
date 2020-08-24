package org.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.inputValidation.RadioAttributesValidation;

import java.io.UnsupportedEncodingException;

import static org.app.App.*;

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
            //at this moment if input validation is turned off, the program does not check for "|" which while inputted wil corrupt file.

            if(toggleInputValidation) {
                RadioAttributesValidation.superValidateRadio(txtName.getText());
                RadioAttributesValidation.superValidateRadio(txtGenre.getText());
                RadioAttributesValidation.superValidateRadio(txtUrl.getText());
                RadioAttributesValidation.superValidateRadio(txtLanguage.getText());
                RadioAttributesValidation.superValidateRadio(txtBitrate.getText());
            }

            //string stripping is handled in radio class. This may not be the good tactic so change should be considered
            RadioStation newRadioStation = new RadioStation(
                    txtUrl.getText(), txtName.getText(), txtGenre.getText(), txtLanguage.getText(), txtBitrate.getText(), checkBoxFavorite.isSelected()
            );
            radioStationDataBase.addRadio(newRadioStation);
            fileIsEdited = true;
            App.closeModal(txtName.getScene().getWindow());

        } catch (IllegalArgumentException e) {
            Alert openStateAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            openStateAlert.showAndWait();
        } catch (Exception e) {

        }


    }
}
