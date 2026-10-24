package UI;

import util.InputValidator;
import util.Utilities;

import java.io.IOException;
import java.util.Scanner;

/**
 * The main view/controller class that executes the primary logic for the menu loop, and subsequently calls methods from each other controller class representing a screen in the application.
 */
public class MainMenuScreen extends Screen {
    public static void showMainMenu(){
        displayHeader("Main Menu");
        System.out.println("(1) View Vehicles List");
        System.out.println("(2) View Reminders List");
        System.out.println("(3) Exit");
        MainMenuScreen.promptMessage("Select an option:");
    }

    public static void mainMenuLoop() throws IOException {
        boolean looping = true;
        while (looping){
            showMainMenu();
            int mainMenuChoice = readMainMenuOption();
            switch (mainMenuChoice){
                case 1:
                    ViewVehiclesScreen.allVehiclesMenu();
                    break;
                case 2:
                    RemindersScreen.viewReminders();
                    RemindersScreen.reminderOptions();
                    break;
                case 3:
                    looping = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static int readMainMenuOption(){
        Scanner scnr = new Scanner(System.in);
        int choice = InputValidator.readIntNumberBetween(1,3,"--- ERROR: Invalid selection. Please enter an integer from 1-3");
        return choice;
    }

    public static void performMainMenuOption(int mainMenuChoice){
        boolean looping = true;
        while (looping){
            switch (mainMenuChoice) {
                case 1:
                    /*
                    System.out.println("All Vehicles: ===============================");
                    everythingManager.printVehiclesList();
                    System.out.println("=============================================");
                    System.out.println("Select a vehicle to view more information:");

                    int vehicleSelection = scnr.nextInt();

                    if (everythingManager.doesVehicleExistIndexVer(vehicleSelection)){
                        everythingManager.printVehicleCard(vehicleSelection);
                        everythingManager.printVehicleReminders(vehicleSelection);

                        System.out.println("=================================");
                        System.out.println("What would you like to do? ======");
                        System.out.println("(1) Edit a reminder for this vehicle");
                        System.out.println("(2) Add a new reminder for this vehicle");
                        System.out.println("(3) Return to main menu");

                        int menuChoice2 = scnr.nextInt();
                        switch (menuChoice2){
                            case 1:
                                System.out.println("===== Input the ID of the reminder you would like to edit: =====");
                                everythingManager.printVehicleReminders(vehicleSelection);

                                //you dont need to ask for the vehicle ID here, because you're only confined to editing the reminders of an already selected vehicle.
                                int editReminderChoice = scnr.nextInt();
                                if (everythingManager.doesReminderExist(editReminderChoice)){
                                    System.out.println("===== EDITING REMINDER: (leave fields blank to keep them the same) =====");
                                    System.out.println("- Enter new reminder name: ");
                                    String newReminderName = scnr.nextLine();
                                    System.out.println("- Enter new due date: ");
                                    String newDueDate = scnr.nextLine();
                                    System.out.println("- Enter new location: ");
                                    String newLocation = scnr.nextLine();

                                    try{
                                        everythingManager.updateReminder(editReminderChoice, newReminderName, newDueDate, newLocation);
                                    }catch (IOException e){
                                        e.printStackTrace();
                                    }
                                }
                                else{
                                    System.out.println("--- ERROR: model.Reminder ID not found. ---");
                                }


                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            default:
                                break;
                        }
                    }
                    else{
                        System.out.println("--- ERROR: model.Vehicle ID not found. ---");
                    }

                    */
                    break;
                case 2:
                    System.out.println("All Reminders: ==============================");
                    System.out.println("=============================================");
                    System.out.println("Select a reminder to view more information:");

                    break;
                case 3:
                    looping = false;
                    break;
                default:
                    System.out.println("--- ERROR: Invalid Selection, please enter an integer from 1-3 ---");
                    System.out.println();
                    break;
            }
        }
    }
}
