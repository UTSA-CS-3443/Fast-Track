package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Reminder;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class VehicleDetailsController {
    public static int currVehicleID;
    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private Label vehicleMake;
    @FXML
    private Label vehicleModel;
    @FXML
    private Label vehicleColor;
    @FXML
    private Label vehicleMileage;
    @FXML
    private Label vehicleMPG;
    @FXML
    private Label vehicleAMPG;
    @FXML
    private Label IDlabel;
    @FXML
    private Label reminderErrorLabel;
    @FXML
    private ListView<Reminder> remindersList;

    @FXML
    private void initialize(){
        Manager a = new Manager();
        Vehicle b = a.getVehicleByID(currVehicleID);
        vehicleMake.setText(b.getMake());
        vehicleModel.setText(b.getModel());
        vehicleColor.setText(b.getColor());
        vehicleMileage.setText(b.getMileage());
        vehicleMPG.setText(String.valueOf(b.getMPG()));
        vehicleAMPG.setText(String.valueOf(b.getAvgMPG()));
        IDlabel.setText(String.valueOf(b.getID()));

        ObservableList<Reminder> observableList = FXCollections.observableList(a.getVehicleRemindersList(currVehicleID));
        remindersList.setItems(observableList);
    }

    @FXML
    public void viewReminderClick(){
        Reminder r = remindersList.getSelectionModel().getSelectedItem();
        if (r != null) {
            ReminderDetailsController.currReminderID = r.getReminderID();
            changeScreen("layouts/reminder-details.fxml");
        } else {
            reminderErrorLabel.setText("Error: select a reminder first!");
        }

    }

    public void editVehicleClick(){
    }

    public void backClick(){
        changeScreen("layouts/vehicles-list.fxml");
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
