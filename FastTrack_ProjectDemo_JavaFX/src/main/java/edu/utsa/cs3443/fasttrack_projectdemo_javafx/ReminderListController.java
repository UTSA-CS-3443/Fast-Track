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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ReminderListController {
    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private TableView<Reminder> vehicleTable;
    @FXML
    private TableColumn<Reminder, String> nameColumn;
    @FXML
    private TableColumn<Reminder, String> dateColumn;
    @FXML
    private TableColumn<Reminder, String> locationColumn;
    @FXML
    private TableColumn<Reminder, Integer> IDColumn;
    @FXML
    private Label viewError;

    @FXML
    private void initialize(){
        // remember that every time a new manager object is created, it loads all the info
        Manager a = new Manager();
        ObservableList<Reminder> observableList = FXCollections.observableArrayList(a.getRemindersList());

        vehicleTable.setItems(observableList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("reminderName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("reminderID"));

    }
    @FXML
    public void viewButtonClick(){
        Reminder selectedReminder = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedReminder == null) {
            viewError.setText("Select a reminder first!");
        }
        else{
            ReminderDetailsController.currReminderID = selectedReminder.getReminderID();
            changeScreen("layouts/reminder-details.fxml");
        }
    }

    public void backClick(){
        try {
            // NOTE TO SELF: I think the main thing that is necessary for swapping screens is that you need
            // THIS current screen to be set before you load the new FXML file. So just make sure the mainStage
            // is assigned here in the method that switches screens.

            // make sure to always SET STAGE by dynamically grabbing something from the current scene
            mainStage = (Stage) FastTrackLabel.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("layouts/main-screen.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());
            mainStage.setScene(loginScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void homeClick(){
        changeScreen("layouts/main-screen.fxml");
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
