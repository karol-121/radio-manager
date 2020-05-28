package org.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import static org.app.App.radioStationDataBase;

public class mainController {
    ObservableList<RadioStation> radioStationObservableList = FXCollections.observableArrayList(radioStationDataBase.getDatabase());

    @FXML
    private TableView<RadioStation> tableView;

    @FXML
    private TableColumn<RadioStation, String> tableViewColName, tableViewColGen, tableViewColLang, tableViewColUrl, tableViewColBit, tableViewColFav;

    @FXML
    public void initialize() throws IOException {
        tableViewColName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        tableViewColGen.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        tableViewColLang.setCellValueFactory(new PropertyValueFactory<>("Language"));
        tableViewColUrl.setCellValueFactory(new PropertyValueFactory<>("Url"));
        tableViewColBit.setCellValueFactory(new PropertyValueFactory<>("Bitrate"));
        tableViewColFav.setCellValueFactory(new PropertyValueFactory<>("Favorite"));

        tableView.setItems(radioStationObservableList);
    }

}
