package UI;

import model.Manager;
import model.Vehicle;
import util.InputValidator;

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
        Vehicle currVehicle = a.findVehicleByID(vehicleID);

        displayHeader("Viewing Vehicle Info + Reminders:");
        a.printVehicleCard(vehicleID);
        displayHeader("Reminders for "+ currVehicle.getMake() + " " + currVehicle.getModel() + ":");
        a.printVehicleReminders(vehicleID);

        vehicleMenu(vehicleID, a);
        //maybe you can remove the edit reminder function here to make things easier.
        //it might be easy to include though, because you can make it so that you never change a reminder's vehicleID
    }

    public static void vehicleMenu(int vehicleID, Manager a){
        boolean looping = true;
        int menuChoice = 0;
        while (looping){
            promptMessage("What would you like to do?");
            System.out.println("(1) Edit vehicle");
            System.out.println("(2) Add reminder");
            System.out.println("(3) Edit reminder");
            System.out.println("(4) Return to main menu");

            menuChoice = InputValidator.readIntNumberBetween(1,4,"*** ERROR: invalid selection. input an integer between 1-4. ***");

            switch (menuChoice){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    looping = false;
                    break;
            }
        }
    }


}
