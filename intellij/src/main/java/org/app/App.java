package org.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    //do something with public access as it allows for uncontrolled access
    public static String liveStreamDef;
    public static int currentIndex;
    public static RadioStationDataBase radioStationDataBase = new RadioStationDataBase();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setTitle("Radio Manager");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static void openModal(String fxml, Window owner, String title) throws IOException {
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setTitle(title);
        stage.setScene(new Scene(loadFXML(fxml)));
        stage.show();
    }

    static void closeModal(Window owner) {
        owner.hide();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}