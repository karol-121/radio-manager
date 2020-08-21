package org.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.nio.file.Path;

public class App extends Application {

    private static Scene scene;
    private static Stage stage;
    //observer is used to subscribe to close modal method
    public static Observer observer;
    //do something with public access as it allows for uncontrolled access
    public static String liveStreamDef;
    public static Boolean fileIsEdited = false;
    public static int currentIndex;
    public static Path currentFilePath;
    public static boolean toggleInputValidation = true;
    public static RadioStationDataBase radioStationDataBase = new RadioStationDataBase();

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        scene = new Scene(loadFXML("primary"));
        this.stage.setTitle("Radio Manager");
        this.stage.setScene(scene);
        this.stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        // TODO: 05.06.2020 this works but there is visible lag while scene does change
        double centerX = stage.getX() + (stage.getWidth()/2);
        double centerY = stage.getY() + (stage.getHeight()/2);
        scene.setRoot(loadFXML(fxml));
        stage.sizeToScene();
        stage.setX(centerX - (stage.getWidth()/2));
        stage.setY(centerY - (stage.getHeight()/2));
    }

    static void openModal(String fxml, Window owner, String title) throws IOException {
        // TODO: 05.06.2020 make metchod to calculate window center posision 
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(owner);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.setScene(new Scene(loadFXML(fxml)));
        stage.show();
        stage.setX((owner.getX() + (owner.getWidth()/2)) - (stage.getWidth()/2));
        stage.setY((owner.getY() + (owner.getHeight()/2)) - (stage.getHeight()/2));

    }

    static void closeModal(Window owner) {
        owner.hide();
        observer.update();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}