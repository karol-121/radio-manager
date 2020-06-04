package org.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.fileHandler.FileOpener;
import java.io.File;
import static org.app.App.*;

public class PrimaryController {

    //Window used for file opener
    private Window mainStage;

    @FXML
    private CheckMenuItem advancedMenuValidationToggle;

    @FXML
    public void initialize() {
        advancedMenuValidationToggle.setSelected(toggleInputValidation);
    }

    @FXML
    public void chooseFile() {
        openFile(advancedMenuValidationToggle.isSelected());
    }

    public void openFile(boolean inputValidation) {
        //updating input validation setting according to choice in menubar
        toggleInputValidation = inputValidation;
        //file chooser
        FileChooser fC = new FileChooser();
        fC.setTitle("Open file");
        FileChooser.ExtensionFilter specificSiiFilter = new FileChooser.ExtensionFilter("Default radio streams file", "live_streams.sii");
        FileChooser.ExtensionFilter globalSiiFilter = new FileChooser.ExtensionFilter("sii files", "*.sii");
        fC.getExtensionFilters().addAll(specificSiiFilter, globalSiiFilter);

        // creating new object for opening files
        FileOpener fileOpener = new FileOpener();

        try {
            File selectedFile = fC.showOpenDialog(mainStage);
            radioStationDataBase.setDatabase(fileOpener.readRadioStations(selectedFile.toPath()));
            liveStreamDef = fileOpener.readLiveStreamDef(selectedFile.toPath());
            //save file path to be used in save
            currentFilePath = selectedFile.toPath();

            //here: send to next scene
            setRoot("main");

        } catch (NullPointerException e) {
            //do nothing as this exceptions is thrown when open dialog is canceled
        } catch (IllegalArgumentException e) {
            //for exceptions throw for invalid format
            Alert openStateAlert = new Alert(Alert.AlertType.ERROR,"Error on line: " + fileOpener.getLineCounter() + " - " + e.getMessage());
            openStateAlert.showAndWait();

        } catch (Exception e) {
            // TODO: 02.06.2020 handle exceptions individually
            Alert openStateAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            openStateAlert.showAndWait();
        }

    }

    @FXML
    private void exit() {
        Platform.exit();
    }
}
