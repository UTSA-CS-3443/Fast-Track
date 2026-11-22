package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Reminder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ReminderListController {
    Stage mainStage;

    @FXML
    private Label FastTrackLabel;
    @FXML
    private TextArea inputArea;
    @FXML
    private RadioButton findRadioButton;
    @FXML
    private RadioButton deleteRadioButton;
    @FXML
    private TextArea outputArea;

    @FXML
    private void initialize() {
        ToggleGroup actionGroup = new ToggleGroup();
        findRadioButton.setToggleGroup(actionGroup);
        deleteRadioButton.setToggleGroup(actionGroup);
        findRadioButton.setSelected(true);
        outputArea.setEditable(false);
    }

    @FXML
    public void searchButtonClick() {
        String rawInput = inputArea.getText().trim();
        if (rawInput.isEmpty()) {
            outputArea.setText("Error: Please enter a Vehicle ID.");
            return;
        }

        int vehicleID;
        try {
            vehicleID = Integer.parseInt(rawInput);
        } catch (NumberFormatException e) {
            outputArea.setText("Error: Vehicle ID must be a valid integer.");
            return;
        }

        Manager manager = Manager.getInstance();
        if (!manager.doesVehicleExistIDVer(vehicleID)) {
            outputArea.setText("Error: No vehicle found with ID " + vehicleID + ".");
            return;
        }

        if (findRadioButton.isSelected()) {
            ArrayList<Reminder> reminders = manager.getVehicleRemindersList(vehicleID);
            if (reminders.isEmpty()) {
                outputArea.setText("No reminders found for Vehicle ID: " + vehicleID);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-22s | %-10s | %-20s | %-12s%n",
                        "Reminder Name", "Due Date", "Location", "Reminder ID"));
                sb.append("-------------------------------------------------------------------------\n");
                for (Reminder r : reminders) {
                    sb.append(String.format("%-22s | %-10s | %-20s | %-12d%n",
                            r.getReminderName(), r.getDueDate(), r.getLocation(), r.getReminderID()));
                }
                outputArea.setText(sb.toString());
            }
        } else if (deleteRadioButton.isSelected()) {
            int deleted = manager.deleteRemindersByVehicleID(vehicleID);
            if (deleted == 0) {
                outputArea.setText("No reminders found to delete for Vehicle ID: " + vehicleID);
            } else {
                outputArea.setText("Successfully deleted " + deleted + " reminder(s) for Vehicle ID: " + vehicleID);
            }
        }
    }

    @FXML
    public void backClick() {
        try {
            mainStage = (Stage) FastTrackLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layouts/main-screen.fxml"));
            Scene mainMenuScene = new Scene(fxmlLoader.load());
            mainStage.setScene(mainMenuScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}