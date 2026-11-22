package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Part;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Reminder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PartDetailsController {
    public static int currPartID;
    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private Label partName;
    @FXML
    private Label partReminderID;
    @FXML
    private Label partID;
    @FXML
    private void initialize(){
        Manager a = Manager.getInstance();
        Part p = a.getPartByID(currPartID);
        partName.setText(p.getPartName());
        partReminderID.setText(String.valueOf(p.getReminderID()));
        partID.setText(String.valueOf(p.getPartID()));
    }

    public void editPartClick(){

    }
    public void backClick(){
        changeScreen("layouts/reminder-details.fxml");
    }
    public void changeScreen(String fxmlPath){
        try {
            mainStage = (Stage) FastTrackLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Scene mainMenuScene = new Scene(fxmlLoader.load());
            mainStage.setScene(mainMenuScene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
