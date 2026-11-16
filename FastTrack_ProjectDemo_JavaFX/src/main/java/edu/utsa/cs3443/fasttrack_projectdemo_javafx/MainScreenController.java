package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;

public class MainScreenController {
    Stage mainStage;

    @FXML
    private Label FastTrackLabel;

    /**
     * Vehicle button click calls changeScreen with the vehicles-list directory
     */
    public void VehiclesButtonClick(){
        changeScreen("/edu/utsa/cs3443/fasttrack_projectdemo_javafx/layouts/vehicles-list.fxml");
    }
    /**
     * Vehicle button click calls changeScreen with the reminder-list directory
     */
    public void RemindersButtonClick(){
        changeScreen("/edu/utsa/cs3443/fasttrack_projectdemo_javafx/layouts/reminders-list.fxml");
    }

    public void exitButtonClick() {

    }

    public void changeScreen(String fxmlPath) {
        try {
            //this is grabbing the stage dynamically so that you don't have to avoid passing the stage into this controller.
            mainStage = (Stage) FastTrackLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
            mainStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
