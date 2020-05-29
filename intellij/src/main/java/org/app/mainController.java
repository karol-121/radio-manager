package org.app;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import static org.app.App.radioStationDataBase;

public class MainController {

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
