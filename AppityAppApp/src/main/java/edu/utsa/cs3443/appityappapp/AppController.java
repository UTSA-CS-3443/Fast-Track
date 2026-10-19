package edu.utsa.cs3443.appityappapp;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppController {


    // Drop Menu Controller if not obvious
    @FXML
    private void DropME(){
        ChoiceBox<String> choiceBox = new ChoiceBox<>(FXCollections.observableArrayList("Home", "Vehicles", "Reminders"));
    }

}
