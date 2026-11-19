package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Part;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Super important, Kept creating a new static currReminderID instead of importing it. Important to remember.
 */
import static edu.utsa.cs3443.fasttrack_projectdemo_javafx.PartDetailsController.currPartID;

public class AddPartController {
    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private TextField insertPartName;
    @FXML
    private Label errorLabel;

    public void backClick(){
        changeScreen("layouts/part-details.fxml");
    }
    public void homeClick(){
        changeScreen("layouts/main-screen.fxml");
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
    /**
     * 1. Give error message if any field is blank, or if any field contains invalid input.
     * 2. Call the appropriate create method in Manager, which randomly generates an ID and saves the object to file
     * 3. Upon successful creation, return user to previous screen
     */
    public void createClick(){
        String partName = insertPartName.getText();
        Manager a = new Manager();
        if (insertPartName.getText() == null){
            errorLabel.setText("ERROR: Fields cannot be left blank");
        }
        else{
            a.addPart(partName, ReminderDetailsController.currReminderID);
            changeScreen("layouts/reminder-details.fxml");
        }
    }
}
