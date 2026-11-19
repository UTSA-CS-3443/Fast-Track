package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Reminder;
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
import static edu.utsa.cs3443.fasttrack_projectdemo_javafx.ReminderDetailsController.currReminderID;

public class EditReminderController {
    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private TextField ReminderIDInsert;
    @FXML
    private TextField VehicleIDInsert;
    @FXML
    private TextField LocationInsert;
    @FXML
    private TextField DueDateInsert;
    @FXML
    private TextField ReminderNameInsert;
    @FXML
    private Label submitLabel;
    @FXML
    private void initialize(){
        Manager a = new Manager();
        Reminder b = a.getReminderByID(currReminderID);
        ReminderNameInsert.setText(b.getReminderName());
        DueDateInsert.setText(b.getDueDate());
        LocationInsert.setText(b.getLocation());
    }

    public void backClick(){
        changeScreen("layouts/reminder-details.fxml");
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

    public void submitReminderEdit(){
        Manager a = new Manager();
        Reminder b = a.getReminderByID(currReminderID);
        b.setReminderName(ReminderNameInsert.getText());
        b.setDueDate(DueDateInsert.getText());
        b.setLocation(LocationInsert.getText());
        a.saveRemindersToFile();     // Need to make function public in Manager to work
        submitLabel.setText("Changes saved successfully!");

    }
}
