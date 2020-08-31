package org.app;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.fileHandler.FileSaver;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.stream.Collectors;

import static org.app.App.*;

public class MainController {

    //Window used for file opener
    private Window mainStage;

    @FXML
    private MenuItem rbMenuEdit;

    @FXML
    private MenuItem rbMenuDelete;

    @FXML
    private Button toolbarEditBtn;

    @FXML
    private Button toolbarDeleteBtn;

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
    private TextField searchValue;

    @FXML
    private Hyperlink selectedHyperUrl;

    @FXML
    private Label selectedTxtBitrate;

    @FXML
    private Label selectedTxtFavorite;

    @FXML
    private CheckMenuItem advancedMenuValidationToggle;

    @FXML
    public void initialize() {
        advancedMenuValidationToggle.setSelected(toggleInputValidation);
        fileIsEdited = false;

        tableViewColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableViewColGen.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        tableViewColLang.setCellValueFactory(new PropertyValueFactory<>("Language"));
        tableViewColUrl.setCellValueFactory(new PropertyValueFactory<>("Url"));
        tableViewColBit.setCellValueFactory(new PropertyValueFactory<>("Bitrate"));
        tableViewColFav.setCellValueFactory(new PropertyValueFactory<>("Favorite"));

        tableView.setItems(radioStationDataBase.getDatabase());

        //observer located in app class that updates upon closed modal
        observer = this::updateSelection;

    }

    @FXML void open() {
        if (fileIsEdited) {
            Alert unsavedFileAlert = new Alert(Alert.AlertType.CONFIRMATION, "File contains unsaved changes that will be lost.", ButtonType.OK, ButtonType.CANCEL);
            unsavedFileAlert.setHeaderText("Do you want to open a new file?");
            unsavedFileAlert.showAndWait();

            if (unsavedFileAlert.getResult().getButtonData() != ButtonBar.ButtonData.OK_DONE) {
                return;
            }
        }

        PrimaryController primaryController = new PrimaryController();
        primaryController.openFile(advancedMenuValidationToggle.isSelected());
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

        } catch (IOException e) {
            saveStateAlert.setAlertType(Alert.AlertType.ERROR);
            saveStateAlert.setContentText(e.getMessage());
            saveStateAlert.showAndWait();
        } catch (Exception e) {
            //exceptions that nobody cares about
        }
    }

    @FXML
    public void add() {
        toggleInputValidation = advancedMenuValidationToggle.isSelected();

        Alert addStateAlert = new Alert(null);
        try {
            //find nicer way of getting this.window object
            //App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalCreate", tableView.getScene().getWindow(), "Add new");
        } catch (IOException e ) {
            // TODO: 02.06.2020 make sure all exceptions are handled
            addStateAlert.setAlertType(Alert.AlertType.ERROR);
            addStateAlert.setContentText(e.getMessage());
            addStateAlert.showAndWait();
        }
    }

    @FXML
    public void edit() {
        //updating input validation setting according to choice in menubar
        toggleInputValidation = advancedMenuValidationToggle.isSelected();

        Alert editStateAlert = new Alert(null);
        try {
            //find nicer way of getting this.window object
            currentIndex = radioStationDataBase.getIndex(tableView.getSelectionModel().getSelectedItem());
            App.openModal("modalEdit", tableView.getScene().getWindow(), "Edit");
            updateSelection();
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
            App.currentIndex = radioStationDataBase.getIndex(tableView.getSelectionModel().getSelectedItem());
            App.openModal("modalDelete", tableView.getScene().getWindow(), "Delete");
            fileIsEdited = true;
        } catch (IOException e ) {
            deleteStateAlert.setAlertType(Alert.AlertType.ERROR);
            deleteStateAlert.setContentText(e.getMessage());
            deleteStateAlert.showAndWait();
        }
    }

    @FXML
    void search() {
        String inputValue = searchValue.getText();
        tableView.setItems(radioStationDataBase.getDatabase().stream().filter(radioStation -> radioStation.getAttributesStream().toUpperCase().contains(inputValue.toUpperCase())).collect(Collectors.toCollection(FXCollections::observableArrayList)));
    }

    @FXML
    void openUrlHyperlink() {
        //Add some sort of validation so it does not open when url is not selected
        //this will not work on other os than windows and it is possible to make it work on all system i guess
        try {
            String cmd = "cmd.exe /c start " + selectedHyperUrl.getText();
            //check if url value is not default, opening default would not break program but would introduce misinformation
            if (!selectedHyperUrl.getText().equals("http://")) {
                Runtime.getRuntime().exec(cmd);
            }

        } catch (IOException e) {
            Alert urlAlert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            urlAlert.showAndWait();
        }

    }

    @FXML
    void inputValidationStatusChange() {
        if (!advancedMenuValidationToggle.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Input validation is turned off");
            alert.showAndWait();
        }

    }


    @FXML
    void updateSelection() {
        rbMenuCheckIfAllowed();
        printCurrent();
    }

    void printCurrent() {
        //rbMenuCheckIfAllowed();

        if(tableView.getSelectionModel().getSelectedItem() != null) {
            selectedTxtName.setText("Name: " + tableView.getSelectionModel().getSelectedItem().getName());
            selectedTxtLang.setText("Language: " + tableView.getSelectionModel().getSelectedItem().getLanguage());
            selectedTxtGenre.setText("Genre: " + tableView.getSelectionModel().getSelectedItem().getGenre());
            selectedHyperUrl.setText(tableView.getSelectionModel().getSelectedItem().getUrl());
            selectedTxtBitrate.setText("Bitrate: " + tableView.getSelectionModel().getSelectedItem().getBitrate());
            selectedTxtFavorite.setText("Favorite: " + tableView.getSelectionModel().getSelectedItem().getFavorite());

        } else {
            //print default values if nothing is selected
        }

    }

    public void rbMenuCheckIfAllowed() {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            rbMenuEdit.setDisable(false);
            rbMenuDelete.setDisable(false);
            toolbarEditBtn.setDisable(false);
            toolbarDeleteBtn.setDisable(false);
        } else {
            rbMenuEdit.setDisable(true);
            rbMenuDelete.setDisable(true);
            toolbarEditBtn.setDisable(true);
            toolbarDeleteBtn.setDisable(true);
        }

    }

    @FXML
    private void exit() {
        if (fileIsEdited) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "File contains unsaved changes that will be lost.", ButtonType.OK, ButtonType.CANCEL);
            alert.setHeaderText("Do you want to exit?");
            alert.showAndWait();

            if (alert.getResult().getButtonData() != ButtonBar.ButtonData.OK_DONE) {
                return;
            }
        }
        Platform.exit();
    }


}
