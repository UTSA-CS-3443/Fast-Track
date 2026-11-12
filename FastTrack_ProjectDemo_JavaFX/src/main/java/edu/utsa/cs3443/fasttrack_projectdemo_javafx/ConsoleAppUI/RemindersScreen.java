package edu.utsa.cs3443.fasttrack_projectdemo_javafx.ConsoleAppUI;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.util.InputValidator;

import java.io.IOException;

/**
 * the main view/navigation screen for viewing and managing the reminders associated with the user's vehicles
 */
public class RemindersScreen extends Screen {
    int i = 0;

    public static void viewReminders(){
        Manager a = new Manager();
        displayHeader("Reminders"); // I like the display header function, feel free to delete my comments
        // loop through each line in csv file and print each reminder detail
        System.out.println("Note: each reminder has a vehicleID, which represents the vehicle it is associated with, and a reminderID, which identifies the reminder itself. \n");
        a.printRemindersList();
        System.out.println();
    }

    public static void reminderOptions(){
        Manager a = new Manager();
        System.out.println("(1) Add Reminder");
        System.out.println("(2) Edit Reminder");
        System.out.println("(3) Delete Reminder");
        System.out.println("(4) View Parts for Reminder");
        System.out.println("(5) Exit");


        int MenuChoice = InputValidator.readIntNumber("Error: enter a valid integer");
        switch (MenuChoice) {
            case 1:                         //Add new Reminder Case
                a.addNewReminder();
                break;
            case 2:                         //Edit Reminder Case
                a.editReminder();
                break;
            case 3:                         // Delete Reminder Case
                try {
                    a.deleteReminder();
                } catch(IOException e){
                    System.out.println("Error deleting reminder: " + e.getMessage());
                }
                break;
            case 4:                         // View Parts For Specific Reminder (Needs to take input and search for line with said number. Function Needs finished in Manager)
                PartsScreen.partsMenu();
                break;
            case 5:                         // Just Exit, no need to modify
                break;
        }
    }
}
