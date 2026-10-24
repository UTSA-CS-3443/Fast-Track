import UI.MainMenuScreen;
import model.Manager;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static void main() throws IOException {
    Scanner scnr = new Scanner(System.in);
    Manager everythingManager = new Manager();
    boolean repeatMenu = true;

    Manager Manager = new Manager();
    MainMenuScreen.mainMenuLoop();



    /*
    while (repeatMenu){
        System.out.println("model.Main Menu ==========================");
        System.out.println("(1) View Vehicles List");
        System.out.println("(2) View Reminders List");
        System.out.println("(3) Exit");
        System.out.println("Select an option: ==================");

        int menuChoice = scnr.nextInt();
        switch (menuChoice){
            case 1:
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
                break;
            case 2:
                System.out.println("All Reminders: ==============================");
                System.out.println("=============================================");
                System.out.println("Select a reminder to view more information:");

                break;
            case 3:
                repeatMenu = false;
                break;
            default:
                System.out.println("--- ERROR: Invalid Selection, please enter an integer from 1-3 ---");
                System.out.println();
                break;
        }




    }
    */

}
