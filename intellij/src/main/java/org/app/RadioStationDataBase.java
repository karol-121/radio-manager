package org.app;

import java.util.ArrayList;

public class RadioStationDataBase {
    private ArrayList<RadioStation> database = new ArrayList<>();

    public RadioStationDataBase() {
        //constructor if needed;
    }

    public ArrayList<RadioStation> getDatabase() {
        return database;
    }

    public void setDatabase(ArrayList<RadioStation> database) {
        this.database = database;
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
