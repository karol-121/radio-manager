package org.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.fileHandler.FileSaver;

import java.io.File;
import java.io.IOException;

import static org.app.App.*;

public class MainController {

    //Window used for file opener
    private Window mainStage;

    @FXML
    private MenuItem rbMenuEdit;

    @FXML
    private MenuItem rbMenuDelete;

    @FXML
    private TableView<RadioStation> tableView;

    @FXML
    private TableColumn<RadioStation, String> tableViewColName, tableViewColGen, tableViewColLang, tableViewColUrl, tableViewColBit, tableViewColFav;

    @FXML
    public void initialize() {
        tableViewColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableViewColGen.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        tableViewColLang.setCellValueFactory(new PropertyValueFactory<>("Language"));
        tableViewColUrl.setCellValueFactory(new PropertyValueFactory<>("Url"));
        tableViewColBit.setCellValueFactory(new PropertyValueFactory<>("Bitrate"));
        tableViewColFav.setCellValueFactory(new PropertyValueFactory<>("Favorite"));

        tableView.setItems(radioStationDataBase.getDatabase());
    }

    @FXML
    public void save() {
        FileSaver fileSaver = new FileSaver();
        try {
            fileSaver.saveRadioStations(currentFilePath, radioStationDataBase.getDatabase());
            //prompt message about successful save
            fileIsEdited = false;

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @FXML
    public void saveAs() {
        FileChooser fC = new FileChooser();
        fC.setTitle("Save file");
        fC.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Live streams","*.sii"));
        FileSaver fileSaver = new FileSaver();

        try {
            File selectedFile = fC.showSaveDialog(mainStage);
            fileSaver.saveRadioStations(selectedFile.toPath(), radioStationDataBase.getDatabase());
            //prompt message about successful save
            fileIsEdited = false;

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void add() {
        try {
            //find nicer way of getting this.window object
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalCreate", tableView.getScene().getWindow(), "Add new");
            fileIsEdited = true;
        } catch (IOException e ) {
            System.err.println(e);
        }
    }

    @FXML
    public void edit() {
        try {
            //find nicer way of getting this.window object
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalEdit", tableView.getScene().getWindow(), "Edit");
            fileIsEdited = true;
        } catch (IOException e ) {
            System.err.println(e);
        }
    }

    @FXML
    public void delete() {
        try {
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalDelete", tableView.getScene().getWindow(), "Delete");
            fileIsEdited = true;
        } catch (IOException e ) {
            System.err.println(e);
        }
    }

    @FXML
    public void rbMenuCheckIfAllowed() {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            rbMenuEdit.setDisable(false);
            rbMenuDelete.setDisable(false);
        } else {
            rbMenuEdit.setDisable(true);
            rbMenuDelete.setDisable(true);
        }

    }

    @FXML
    private void exit() {
        if (fileIsEdited) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "File contains unsaved changes that will be lost.\nDo you want to close this program anyway?", ButtonType.OK, ButtonType.CANCEL );
            alert.showAndWait();

            if (alert.getResult().getButtonData() != ButtonBar.ButtonData.OK_DONE) {
                return;
            }
        }
        Platform.exit();
    }


}
