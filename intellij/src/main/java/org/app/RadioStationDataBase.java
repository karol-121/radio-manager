package org.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

import static org.app.App.radioStationDataBase;

public class RadioStationDataBase {
    //private ArrayList<RadioStation> database = new ArrayList<>();
    private ObservableList<RadioStation> database = FXCollections.observableArrayList();

    public RadioStationDataBase() {
        //constructor if needed;
    }

    public ObservableList<RadioStation> getDatabase() {
        //ArrayList<RadioStation> returnDatabase = new ArrayList<>(database);
        return database;
    }

    public void setDatabase(ArrayList<RadioStation> databaseInn) {
        this.database.addAll(databaseInn);
    }

    public void addRadio(RadioStation radioStation){
        database.add(radioStation);
    }

    public void removeRadio(RadioStation radioStation) {
        database.remove(radioStation);
    }

    public void setRadio(int index, RadioStation radioStation) {
        database.set(index, radioStation);
    }

    public RadioStation getRadio(int index) {
        return database.get(index);
    }

    public int getIndex(RadioStation radioStation) {
        return database.indexOf(radioStation);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (RadioStation radioStation : database) {
            stringBuilder.append(radioStation.toString() + "\n");
        }
        return stringBuilder.toString();
    }


}
