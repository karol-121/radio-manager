package org.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.fileHandler.FileSaver;

import java.io.File;
import java.io.IOException;
import static org.app.App.radioStationDataBase;

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
    public void saveAs() {
        FileChooser fC = new FileChooser();
        fC.setTitle("Save file");
        fC.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Live streams","*.sii"));

        File selectedFile = fC.showSaveDialog(mainStage);

        FileSaver fileSaver = new FileSaver();

        try {
            fileSaver.saveRadioStations(selectedFile.toPath(), radioStationDataBase.getDatabase());

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    public void add() {
        try {
            //find nicer way of getting this.window object
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalCreate", tableView.getScene().getWindow(), "Add new");
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
        } catch (IOException e ) {
            System.err.println(e);
        }
    }

    @FXML
    public void delete() {
        try {
            App.currentIndex = tableView.getSelectionModel().getSelectedIndex();
            App.openModal("modalDelete", tableView.getScene().getWindow(), "Delete");
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
        Platform.exit();
    }


}
