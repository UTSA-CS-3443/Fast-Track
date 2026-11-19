package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class VehiclesListController {
    // controller must be tied to a class if you want the change
    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> makeColumn;
    @FXML
    private TableColumn<Vehicle, String> modelColumn;
    @FXML
    private TableColumn<Vehicle, String> colorColumn;
    @FXML
    private TableColumn<Vehicle, Integer> IDColumn;
    @FXML
    private Label viewError;

    @FXML
    private void initialize(){
        // remember that every time a new manager object is created, it loads all the info
        Manager a = new Manager();
        ObservableList<Vehicle> observableList = FXCollections.observableArrayList(a.getVehiclesList());

        vehicleTable.setItems(observableList);
        makeColumn.setCellValueFactory(new PropertyValueFactory<>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<>("model"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

    }
    public void viewButtonClick(){
        Vehicle selectedVehicle = vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle == null) {
            viewError.setText("Select a vehicle first!");
        }
        else{
            // this sets the static variable in VehicleInfoController that decides which vehicle's info to display
            VehicleDetailsController.currVehicleID = selectedVehicle.getID();
            changeScreen("layouts/vehicle-details.fxml");
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
