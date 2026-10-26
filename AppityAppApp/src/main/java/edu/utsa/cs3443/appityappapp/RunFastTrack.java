package edu.utsa.cs3443.appityappapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunFastTrack extends Application  {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunFastTrack.class.getResource("Scenes/HomeSCREEN.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FastTrack");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
