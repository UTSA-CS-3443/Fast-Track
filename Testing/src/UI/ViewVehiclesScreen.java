package UI;

import model.Manager;
import util.InputValidator;

import java.util.Scanner;

public class ViewVehiclesScreen extends Screen {
    public static void allVehiclesMenu(){
        Manager a = new Manager();
        displayHeader("All Vehicles:");
        a.printVehiclesList();
        //allVehiclesMenu(a);
    }

    public static void allVehiclesMenu(Manager a){
        Scanner scnr = new Scanner(System.in);

        System.out.println("(1) View a vehicle's information");
        System.out.println("(2) Return to main menu");
        int vehicleOptionsMenuChoice = InputValidator.readIntNumberBetween(1,2,"*** ERROR: invalid selection. input an integer between 1-2 ***");

        switch (vehicleOptionsMenuChoice){
            case 1:
                promptMessage("Enter the ID of the vehicle you would like to view:");
                boolean IDNotFound = true;
                int searchingVehicleID = 0;
                while (IDNotFound){
                    searchingVehicleID = scnr.nextInt();
                    if (a.doesVehicleExistIDVer(searchingVehicleID)){
                        IDNotFound = false;
                    }
                    else{
                        errorMessage("*** ERROR: vehicle ID not found. ***");
                    }
                }
                VehicleInfoScreen.showVehicleInfo(searchingVehicleID, a);
                break;
            case 2:
                break;
        }
    }
}
