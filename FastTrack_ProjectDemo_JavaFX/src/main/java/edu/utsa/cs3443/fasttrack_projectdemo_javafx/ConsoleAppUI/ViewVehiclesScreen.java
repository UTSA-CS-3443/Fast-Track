package edu.utsa.cs3443.fasttrack_projectdemo_javafx.ConsoleAppUI;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.util.InputValidator;

import java.util.Scanner;

/**
 * the main menu/navigation screen to view and manage the user's vehicles
 */
public class ViewVehiclesScreen extends Screen {
    public static void allVehiclesMenu(){
        Scanner scnr = new Scanner(System.in);
        Manager a = new Manager();

        displayHeader("All Vehicles");
        System.out.println("(Shown: ID, Make, Model)");
        a.printVehiclesList();
        System.out.println();
        System.out.println("(1) View a vehicle's information");
        System.out.println("(2) Add a new vehicle");
        System.out.println("(3) Delete a vehicle");
        System.out.println("(4) Return to main menu");
        System.out.println();
        int vehicleOptionsMenuChoice = InputValidator.readIntNumberBetween(1,4,"*** ERROR: invalid selection. input an integer between 1-2 ***");

        switch (vehicleOptionsMenuChoice){
            case 1:
                System.out.println("Enter the ID of the vehicle you would like to view:");
                boolean IDNotFound = true;
                int searchingVehicleID = findID(a);
                VehicleInfoScreen.showVehicleInfo(searchingVehicleID, a);
                break;
            case 2:
                a.addNewVehicle();
                break;
            case 3:
                if (a.getVehiclesListSize() == 0){
                    System.out.println("There are no vehicles currently in the list!");
                }
                else{
                    System.out.println("Enter the ID of the vehicle you would like to DELETE:");
                    int deletingID = findID(a);
                    a.deleteVehicle(deletingID);
                }
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public static int findID(Manager a){
        Scanner scnr = new Scanner(System.in);
        boolean IDNotFound = true;
        int inputID = 0;
        while (IDNotFound){
            inputID = InputValidator.readIntNumber("Error: please enter a valid integer");
            if (a.doesVehicleExistIDVer(inputID)){
                IDNotFound = false;
            }
            else{
                errorMessage("*** ERROR: vehicle ID not found. Please enter a valid ID. ***");
            }
        }
        return inputID;
    }

    public static void deleteMenuOption(){
        Manager a = new Manager();
        int deletingID = findID(a);

    }
}
