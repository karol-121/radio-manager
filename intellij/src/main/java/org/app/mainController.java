package org.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
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
    private TableColumn<RadioStation, String> tableViewColName, tableViewColGen, tableViewColLang, tableViewColUrl, tableViewColBit;
    @FXML
    private TableColumn<RadioStation, Boolean> tableViewColFav;

    @FXML
    private Label selectedTxtName;

    @FXML
    private Label selectedTxtGenre;

    @FXML
    private Label selectedTxtLang;

    @FXML
    private Label selectedTxtUrl;

    @FXML
    private Label selectedTxtBitrate;

    @FXML
    private Label selectedTxtFavorite;

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

    @FXML void open() {
        if (fileIsEdited) {
            Alert unsavedFileAlert = new Alert(Alert.AlertType.CONFIRMATION, "File contains unsaved changes that will be lost.", ButtonType.OK, ButtonType.CANCEL);
            unsavedFileAlert.showAndWait();

            if (unsavedFileAlert.getResult().getButtonData() != ButtonBar.ButtonData.OK_DONE) {
                return;
            }
        }
        PrimaryController primaryController = new PrimaryController();
        primaryController.chooseFile();
        initialize();
    }

    @FXML
    public void save() {
        FileSaver fileSaver = new FileSaver();
        Alert saveAsStateAlert = new Alert(null);
        try {
            fileSaver.saveRadioStations(currentFilePath, radioStationDataBase.getDatabase());
            saveAsStateAlert.setAlertType(Alert.AlertType.INFORMATION);
            saveAsStateAlert.setContentText("File has successfully been saved!");
            saveAsStateAlert.showAndWait();

            fileIsEdited = false;

        } catch (Exception e) {
            saveAsStateAlert.setAlertType(Alert.AlertType.ERROR);
            saveAsStateAlert.setContentText(e.getMessage());
            saveAsStateAlert.showAndWait();
        }

    }

    @FXML
    public void saveAs() {
        FileChooser fC = new FileChooser();
        fC.setTitle("Save file");
        fC.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Live streams","*.sii"));
        FileSaver fileSaver = new FileSaver();
        Alert saveStateAlert = new Alert(null);

        try {
            File selectedFile = fC.showSaveDialog(mainStage);
            fileSaver.saveRadioStations(selectedFile.toPath(), radioStationDataBase.getDatabase());
            saveStateAlert.setAlertType(Alert.AlertType.INFORMATION);
            saveStateAlert.setContentText("File has been successfully saved!");
            saveStateAlert.showAndWait();

            fileIsEdited = false;

        } catch (Exception e) {
            // TODO: 02.06.2020 handle different exception different, when closing save modal exception is thrown but does not need dialog box
            saveStateAlert.setAlertType(Alert.AlertType.ERROR);
            saveStateAlert.setContentText(e.getMessage());
            saveStateAlert.showAndWait();
        }
    }

    @FXML
    public void add() {
        Alert addStateAlert = new Alert(null);
        try {
            //find nicer way of getting this.window object
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalCreate", tableView.getScene().getWindow(), "Add new");
            fileIsEdited = true;
        } catch (IOException e ) {
            // TODO: 02.06.2020 make sure all exceptions are handled
            addStateAlert.setAlertType(Alert.AlertType.ERROR);
            addStateAlert.setContentText(e.getMessage());
            addStateAlert.showAndWait();
        }
    }

    @FXML
    public void edit() {
        Alert editStateAlert = new Alert(null);
        try {
            //find nicer way of getting this.window object
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalEdit", tableView.getScene().getWindow(), "Edit");
            fileIsEdited = true;
        } catch (IOException e ) {
            editStateAlert.setAlertType(Alert.AlertType.ERROR);
            editStateAlert.setContentText(e.getMessage());
            editStateAlert.showAndWait();
        }
    }

    @FXML
    public void delete() {
        Alert deleteStateAlert = new Alert(null);
        try {
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalDelete", tableView.getScene().getWindow(), "Delete");
            fileIsEdited = true;
        } catch (IOException e ) {
            deleteStateAlert.setAlertType(Alert.AlertType.ERROR);
            deleteStateAlert.setContentText(e.getMessage());
            deleteStateAlert.showAndWait();
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
    void printCurrent(MouseEvent event) {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            selectedTxtName.setText("Name: " + tableView.getSelectionModel().getSelectedItem().getName());
            selectedTxtLang.setText("Language: " + tableView.getSelectionModel().getSelectedItem().getLanguage());
            selectedTxtGenre.setText("Genre: " + tableView.getSelectionModel().getSelectedItem().getGenre());
            selectedTxtUrl.setText("Url: " + tableView.getSelectionModel().getSelectedItem().getUrl());
            selectedTxtBitrate.setText("Bitrate: " + tableView.getSelectionModel().getSelectedItem().getBitrate());
            selectedTxtFavorite.setText("Favorite: " + tableView.getSelectionModel().getSelectedItem().getFavorite());

        }

    }

    @FXML
    private void exit() {
        if (fileIsEdited) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "File contains unsaved changes that will be lost.", ButtonType.OK, ButtonType.CANCEL );
            alert.showAndWait();

            if (alert.getResult().getButtonData() != ButtonBar.ButtonData.OK_DONE) {
                return;
            }
        }
        Platform.exit();
    }


}
