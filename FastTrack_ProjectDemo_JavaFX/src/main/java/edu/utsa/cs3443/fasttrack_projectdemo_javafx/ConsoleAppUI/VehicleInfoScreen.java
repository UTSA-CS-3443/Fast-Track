package edu.utsa.cs3443.fasttrack_projectdemo_javafx.ConsoleAppUI;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Vehicle;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.util.InputValidator;

import java.util.Scanner;

/**
 * the controller class for the menu viewing a specific vehicle's information. Users can edit the vehicle, add reminders to the vehicle, or edit its reminders here.
 */
public class VehicleInfoScreen extends Screen{
    /**
     * lists the vehicle's info/reminder list, and prompts user for what action to take regarding this specific vehicle
     * @param vehicleID
     * @param a
     */
    public static void showVehicleInfo(int vehicleID, Manager a){
        Vehicle currVehicle = a.getVehicleByID(vehicleID);

        displayHeader("Viewing Vehicle Info + Reminders:");
        a.printVehicleCard(vehicleID);
        displayHeader("Reminders for: "+ currVehicle.getMake() + " " + currVehicle.getModel());
        a.printVehicleReminders(vehicleID);
        vehicleMenu(vehicleID);
        //maybe you can remove the edit reminder function here to make things easier.
        //it might be easy to include though, because you can make it so that you never change a reminder's vehicleID
    }

    /**
     * the menu loop for an individual vehicle's information screen. Returns to the main menu once it ends(?)
     * @param vehicleID the ID of the vehicle currently being viewed
     */
    public static void vehicleMenu(int vehicleID){
        Manager a = new Manager();
        int menuChoice = 0;
            System.out.println();
            System.out.println("(1) Edit this vehicle");
            System.out.println("(2) Add reminder for this vehicle");
            System.out.println("(3) Edit reminder for this vehicle");
            System.out.println("(4) Return to main menu");

            menuChoice = InputValidator.readIntNumberBetween(1,4,"*** ERROR: invalid selection. input an integer between 1-4. ***");

            switch (menuChoice){
                case 1:
                    editVehiclePrompt(vehicleID);

                    break;
                case 2:
                    a.addNewReminder();
                    break;
                case 3:
                    a.editReminder();
                    break;
                case 4:
                    break;
            }
    }

    /**
     * prompts user to enter new values for a vehicle being edited. The vehicle's ID is passed to this method, and it passes that ID to the manager class editVehicle method. has already been found at this point
     */
    public static void editVehiclePrompt(int ID){
        Scanner scnr = new Scanner(System.in);
        Manager a = new Manager();

        System.out.println("Editing vehicle information: enter new values, or leave fields blank to keep them unchanged.");
        System.out.println("Enter make:");
        String make = scnr.nextLine();
        System.out.println("Enter edu.utsa.cs3443.fasttrack_projectdemo_javafx.model:");
        String model = scnr.nextLine();
        System.out.println("Enter color:");
        String color = scnr.nextLine();
        System.out.println("Enter mileage:");
        String mileage = scnr.nextLine();
        System.out.println("Enter MPG");
        String MPG = scnr.nextLine();
        System.out.println("Enter avgMPG:");
        String avgMPG = scnr.nextLine();

        a.editVehicle(make, model, color, mileage, MPG, avgMPG, ID);

    }


}
