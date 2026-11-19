package edu.utsa.cs3443.fasttrack_projectdemo_javafx;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Vehicle;
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
import static edu.utsa.cs3443.fasttrack_projectdemo_javafx.VehicleDetailsController.currVehicleID;

public class AddVehicleController {
    Stage mainStage;
    @FXML
    private Label FastTrackLabel;
    @FXML
    private TextField insertMake;
    @FXML
    private TextField insertModel;
    @FXML
    private TextField insertColor;
    @FXML
    private TextField insertMileage;
    @FXML
    private TextField insertMPG;
    @FXML
    private TextField insertAMPG;
    @FXML
    private Label submitLabel;
    @FXML
    private void initialize(){
        Manager a = new Manager();
        Vehicle b = a.getVehicleByID(currVehicleID);
        insertMake.setText(b.getMake());
        insertModel.setText(b.getModel());
        insertColor.setText(b.getColor());
        insertMileage.setText(String.valueOf(b.getMileage()));
        insertMPG.setText(String.valueOf(b.getMPG()));
        insertAMPG.setText(String.valueOf(b.getAvgMPG()));
    }

    public void backClick(){
        changeScreen("layouts/vehicle-details.fxml");
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

    public void homeClick(){
        changeScreen("layouts/main-screen.fxml");
    }


    public void submitVehicleEdit(){
        Manager a = new Manager();
        Vehicle b = a.getVehicleByID(currVehicleID);
        b.setMake(insertMake.getText());
        b.setModel(insertModel.getText());
        b.setColor(insertColor.getText());
        b.setMileage(Integer.parseInt(insertMileage.getText()));
        b.setMPG(Double.parseDouble(insertMPG.getText()));
        b.setAvgMPG(Double.parseDouble(insertAMPG.getText()));
        a.saveVehiclesToFile();     // Need to make function public in Manager to work
        submitLabel.setText("Changes saved successfully!");

    }
}
