package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FastTrackApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FastTrackApplication.class.getResource("layouts/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load()); // TODO: note dimensions removal

        // TODO: explain new idea!
        // The LoginController will display the main-screen-view upon successful login
        // The Scene loaded from main-screen-view.fxml will appear on the same stage that
        // was originally used to display the Scene loaded from login-view.fxml (which is used here).
        MainMenuController mainMenuController = fxmlLoader.getController();
        mainMenuController.setStage(stage);

        stage.setTitle("FastTrack Vehicle Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
