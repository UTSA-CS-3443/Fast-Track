package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FastTrackApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/edu/utsa/cs3443/fasttrack_projectdemo_javafx/layouts/main-screen.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("FastTrack Vehicle Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
