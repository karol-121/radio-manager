package org.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.inputValidation.RadioAttributesValidation;

import static org.app.App.*;

public class ModalEditController {
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
    public void initialize(){

        RadioStation currentRadioStation = radioStationDataBase.getRadio(currentIndex);
        txtName.setText(currentRadioStation.getName());
        txtGenre.setText(currentRadioStation.getGenre());
        txtLanguage.setText(currentRadioStation.getLanguage());
        txtUrl.setText(currentRadioStation.getUrl());
        txtBitrate.setText(currentRadioStation.getBitrate());
        checkBoxFavorite.setSelected(currentRadioStation.getFavorite());

    }

    @FXML
    void edit(ActionEvent event) {

        try {
            //checking if any of input values does contain | (character used to separate values in file)
            //this validation should not be possible to turn of as injecting it will cause the file to be corrupted
            RadioAttributesValidation.superValidateRadio(txtName.getText()+txtGenre.getText()+txtLanguage.getText()+txtUrl.getText()+txtBitrate.getText());

            if(toggleInputValidation) {
                RadioAttributesValidation.validateRadioLanguage(txtLanguage.getText());
                RadioAttributesValidation.validateRadioBitrate(txtBitrate.getText());
            }

            //this can use set parameters from radioStation in order to edit radioStation object
            RadioStation changedRadioStation = new RadioStation(
                    txtUrl.getText(), txtName.getText(), txtGenre.getText(), txtLanguage.getText(), txtBitrate.getText(), checkBoxFavorite.isSelected()
            );
            radioStationDataBase.setRadio(currentIndex, changedRadioStation);
            App.closeModal(txtName.getScene().getWindow());

        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }



    }
}
