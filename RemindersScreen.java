package UI;

import model.Manager;
import util.InputValidator;
import util.Utilities;

import java.io.IOException;
import java.lang.runtime.SwitchBootstraps;
import java.util.Scanner;

public class RemindersScreen extends Screen {
    int i = 0;

    public static void viewReminders() throws IOException {
        displayHeader("Reminders"); // I like the display header function, feel free to delete my comments
        System.out.println("         Name       |" + "   Date Due   |" + "   Location   |" + "   Vehicle ID  |" + "   Reminder ID  |");
        // loop through each line in csv file and print each reminder detail
        Manager.loadReminders();
        Manager.printRemindersList();
    }

    public static void reminderOptions() throws IOException {
        String errorMessage = "Pick one of the options";
        System.out.println("Options");
        System.out.println("-------------------------------------------");
        System.out.println("(1) Add Reminder");
        System.out.println("(2) Edit Reminder");
        System.out.println("(3) Delete Reminder");
        System.out.println("(4) View Parts for Reminder");
        System.out.println("(5) Exit");


        int MenuChoice = InputValidator.readIntNumber(errorMessage);
        switch (MenuChoice) {
            case 1:                         //Add new Reminder Case
                Manager.addNewReminder();
                break;
            case 2:                         //Edit Reminder Case
                Manager.editReminder();
                break;
            case 3:                         // Delete Reminder Case
                Manager.deleteReminder();
                break;
            case 4:                         // View Parts For Specific Reminder (Needs to take input and search for line with said number. Function Needs finished in Manager)
                Manager.viewReminderParts();
                break;
            case 5:                         // Just Exit, no need to modify
                break;
        }
    }
}
