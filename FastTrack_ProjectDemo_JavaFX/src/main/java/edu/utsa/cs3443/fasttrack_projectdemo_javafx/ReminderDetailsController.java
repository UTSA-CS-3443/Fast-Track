package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Part;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Reminder;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReminderDetailsController {

    public static int currReminderID;

    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private Label reminderName;
    @FXML
    private Label reminderDate;
    @FXML
    private Label reminderLocation;
    @FXML
    private Label reminderVID;
    @FXML
    private Label reminderIDLabel;
    @FXML
    private Label partErrorLabel;
    @FXML
    private ListView<Part> partsList;
    @FXML
    private void initialize(){
        Manager a = new Manager();
        Reminder b = a.getReminderByID(currReminderID);
        reminderName.setText(b.getReminderName());
        reminderDate.setText(b.getDueDate());
        reminderLocation.setText(b.getLocation());
        reminderVID.setText(String.valueOf(b.getVehicleID()));
        reminderIDLabel.setText(String.valueOf(b.getReminderID()));

        ObservableList<Part> observableList = FXCollections.observableList(a.getReminderPartsList(currReminderID));
        partsList.setItems(observableList);
    }

    /**
     * this function will return to the screen for the Vehicle that this reminder belongs to, because
     * the vehicleDetails screen always displays information for the vehicle assigned to the
     * static currentVehicleID class-level variable.
     */
    public void backClick(){
        changeScreen("layouts/vehicle-details.fxml");
    }
    public void homeClick(){
        changeScreen("layouts/main-screen.fxml");
    }

    public void editReminderClick(){
        changeScreen("layouts/edit-reminder.fxml");
    }

    public void addPartClick() {
        changeScreen("layouts/add-part.fxml");

    }

    public void viewPartClick(){
        Part p = partsList.getSelectionModel().getSelectedItem();
        if (p != null) {
            PartDetailsController.currPartID = p.getPartID();
            changeScreen("layouts/part-details.fxml");
        } else {
            partErrorLabel.setText("Error: select a part first!");
        }
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
