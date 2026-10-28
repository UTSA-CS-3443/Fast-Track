package UI;

import model.Manager;
import util.InputValidator;
import util.Utilities;

import java.io.*;
import java.lang.runtime.SwitchBootstraps;
import java.util.Scanner;

public class PartsScreen extends Screen {


    public static void partsOptions(int tempsetID) throws IOException{
        String errorMessage = "Pick one of the options";
        System.out.println("(1) Add New Part");
        System.out.println("(2) Edit Part");
        System.out.println("(3) Delete Part");
        System.out.println("(4) Exit");

        int MenuChoice = InputValidator.readIntNumber(errorMessage);
        switch (MenuChoice) {
            case 1:                         //Add new Reminder Case
                Manager.addNewPart(tempsetID);
                break;
            case 2:                         //Edit Reminder Case
                Manager.editPart();
                break;
            case 3:                         // Delete Reminder Case
                Manager.deletePart(tempsetID);
                break;
            case 4:                         // View Parts For Specific Reminder (Needs to take input and search for line with said number. Function Needs finished in Manager)
                RemindersScreen.viewReminders();
                RemindersScreen.reminderOptions();
                break;

        }
    }
}

