package model;

import UI.PartsScreen;
import UI.Screen;
import util.InputValidator;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Manager extends Screen{    // Screen is extended for one function, viewReminderParts
    static ArrayList<Vehicle> VehiclesList = new ArrayList<>();
    static ArrayList<Reminder> RemindersList = new ArrayList<>();
    static ArrayList<Part> PartsList = new ArrayList<>();

    public Manager() {
        try {
            loadVehicles();
            loadReminders();
            loadParts();
        } catch (IOException e) {

        }
    }

    public static void loadVehicles() throws IOException {
        VehiclesList.clear();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/vehicles.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                Vehicle e = new Vehicle(csv[0], csv[1], csv[2], csv[3], Double.parseDouble(csv[4]), Double.parseDouble(csv[5]), Integer.parseInt(csv[6]));
                VehiclesList.add(e);
            }
        } catch (IOException e) {

        }
    }

    public static void loadReminders() throws IOException {
        RemindersList.clear();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader("data/reminders.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                Reminder e = new Reminder(csv[0], csv[1], csv[2],
                        Integer.parseInt(csv[3]), Integer.parseInt(csv[4]));
                RemindersList.add(e);
            }
        }
    }

    public static void loadParts() throws IOException {
        PartsList.clear();
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader("data/parts.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                Part e = new Part(csv[0], Integer.parseInt(csv[1]));
                PartsList.add(e);
            }
        }
    }

    private static String convertVehicleToLine(Vehicle a) {
        String line = "";
        line += a.getMake();
        line += ",";
        line += a.getModel();
        line += ",";
        line += a.getColor();
        line += ",";
        line += a.getMileage();
        line += ",";
        line += a.getMPG();
        line += ",";
        line += a.getAvgMPG();
        line += ",";
        line += a.getID();

        return line;
    }

    private static String convertReminderToLine(Reminder a) {
        String line = "";
        line += a.getReminderName();
        line += ",";
        line += a.getDueDate();
        line += ",";
        line += a.getLocation();
        line += ",";
        line += a.getVehicleID();
        line += ",";
        line += a.getReminderID();

        return line;
    }

    private static String convertPartToLine(Part a) {
        String line = "";
        line += a.getPartName();
        line += ",";
        line += a.getID();

        return line;
    }

    private static void saveVehiclesToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/vehicles.csv"));
        for (Vehicle a : VehiclesList) {
            String line = convertVehicleToLine(a);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    private static void saveRemindersToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/reminders.csv"));
        for (Reminder a : RemindersList) {
            String line = convertReminderToLine(a);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    private static void savePartsToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/parts.csv"));
        for (Part a : PartsList) {
            String line = convertPartToLine(a);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }

    /**
     * prints out each vehicle's ID, Make and model
     */
    public static void printVehiclesList() {
        for (Vehicle a : VehiclesList) {
            System.out.println((VehiclesList.indexOf(a) + 1) + ": " + a.getID() + " " + a.getMake() + " " + a.getModel());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * checks if model.Vehicle exists, using a user input for the vehicle's index in the VehiclesList arraylist. Mainly used from main menu
     *
     * @param menuSelection
     * @return
     */
    public static boolean doesVehicleExistIndexVer(int menuSelection) {
        if (menuSelection - 1 < 0 || menuSelection - 1 > VehiclesList.size()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * checks if vehicle exists, using its ID instead.
     *
     * @param submittedVehicleID
     * @return
     */
    public boolean doesVehicleExistIDVer(int submittedVehicleID) {
        for (Vehicle a : VehiclesList) {
            if (submittedVehicleID == a.getID()) {
                return true;
            }
        }
        return false;
    }

    /**
     * accepts a vehicle's ID, searches the manager object's arrayList, and returns the matching vehicleobject
     *
     * @param inputVehicleID integer ID that is checked against the IDs of objects in the VehiclesList arraylist
     * @return the found vehicle
     */
    public static Vehicle findVehicleByID(int inputVehicleID) {
        for (Vehicle a : VehiclesList) {
            if (inputVehicleID == a.getID()) {
                return a;
            }
        }
        System.out.println("*** ERROR: unable to find vehicle via ID ***");
        return null;
    }

    /**
     * prints a selected vehicle's info, and the reminders that share the vehicle's ID.
     *
     * @param vehicleID the integer that the user inputs from the menu interface, which is used to select the corresponding vehicle from the vehiclesList arraylist
     */
    public static void printVehicleCard(int vehicleID) {
        Vehicle currVehicle = findVehicleByID(vehicleID);
        System.out.println("Make: " + currVehicle.getMake());
        System.out.println("Model: " + currVehicle.getModel());
        System.out.println("Color: " + currVehicle.getColor());
        System.out.println("Mileage: " + currVehicle.getMileage());
        System.out.println("MPG: " + currVehicle.getMPG());
        System.out.println("Avg MPG: " + currVehicle.getAvgMPG());
        System.out.println("ID: " + currVehicle.getID());
        System.out.println("-------------------------------------------");
    }

    public static void printVehicleReminders(int vehicleID) {
        Vehicle currVehicle = findVehicleByID(vehicleID);

        System.out.println("(Reminders for " + currVehicle.getMake() + " " + currVehicle.getModel() + "):");
        for (Reminder a : RemindersList) {
            if (a.getVehicleID() == currVehicle.getID()) {
                System.out.println("- (" + a.getReminderID() + ") " + a.getReminderName());
            }
        }
    }


/**
 public boolean doesReminderExist(int submittedReminderID) {
 for (Reminder a : RemindersList) {
 if (submittedReminderID == a.getReminderID()) {
 return true;
 }
 }
 return false;
 }
 */

    // Created my own, goes off of the Reminder ID for now
    // - Chris
    /**
     public void updateReminder(int readReminderID, String newReminderName, String newDueDate, String newLocation) throws IOException {
     // first finds the reminder that we will be changing. the new object starts out identical to the one being edited
     // reminderID stays the same
     // I dont think??? the vehicleID is needed, because the reminder you're editing already has the matching reminderID, which means it has the matching vehicleID
     for (Reminder a : RemindersList) {
     if (a.getReminderID() == readReminderID) {
     //each field is only updated if there was a new value entered. if not, it stays the same.
     if (!(newReminderName == null)) {
     a.setReminderName(newReminderName);
     }
     if (!(newDueDate == null)) {
     a.setDueDate(newDueDate);
     }
     if (!(newLocation == null)) {
     a.setLocation(newLocation);
     }
     saveRemindersToFile();
     }
     }
     }
     */

    /**
     * New Stufff!!!
     */
    public static void printRemindersList() {
        for (int i = 0; i < RemindersList.size(); i++) {
            Reminder a = RemindersList.get(i);

            System.out.println((i + 1) + ": " + a.getReminderName() + "  |  " + a.getDueDate() + "  |  " + a.getLocation() + "  |  " + a.getVehicleID() + "  |  " + a.getReminderID());
        }
        System.out.println("-------------------------------------------");
    }

    public static void addNewReminder() throws IOException {
        String file = "data/reminders.csv";

        System.out.print("New Reminder Name: ");
        String tempsetReminderName = InputValidator.readString();
        System.out.print("Reminder Due Date: ");
        String tempsetDueDate = InputValidator.readString();
        System.out.print("Service Location: ");
        String tempsetLocation = InputValidator.readString();
        System.out.print("Enter Vehicle ID Reminder is for:");
        int tempsetVehicleID = InputValidator.readIntNumber("Try again");
        System.out.print("Set Reminder ID (3 digits): ");
        int tempsetReminderID = InputValidator.readIntNumber("Try again");

        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) {
            pw.println(tempsetReminderName + "," + tempsetDueDate + "," + tempsetLocation + "," + tempsetVehicleID + "," + tempsetReminderID);
        }

    }


    public static void deleteReminder() throws IOException {
        File inputFile = new File("data/reminders.csv");
        File tempFile = new File("data/temp.csv");
        String removalLine = "";
        String tempName = "";

        System.out.print("Enter the ID of the Reminder you wish to DELETE: ");
        int tempID = InputValidator.readIntNumber("Try again");
        int i = 0;

        for (Reminder a : RemindersList) {
            if (tempID == a.getReminderID()) {
                Reminder b = RemindersList.get(i);
                tempName = b.getReminderName();
                removalLine = (b.getReminderName() + "," + b.getDueDate() + "," + b.getLocation() + "," + b.getVehicleID() + "," + b.getReminderID());
            }
            i += 1;
        }

        System.out.println("Are you sure you want to DELETE " + tempName + "? (Y/N)");
        String answer = InputValidator.readString();
        if (answer.equals("Y")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.trim().equals(removalLine.trim())) {
                        continue;
                    }

                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("The Reminder has been DELETED.");
        } else {
            System.out.println("The Reminder Remains.");
        }
    }

    public static void editReminder() throws IOException {
        System.out.print("Enter the ID of the Reminder you wish to modify: ");
        int tempID = InputValidator.readIntNumber("Try again");
        for (int i = 0; i < RemindersList.size(); i++) {
            Reminder a = RemindersList.get(i);
            if (tempID == a.getReminderID()) {
                Reminder b = RemindersList.get(i);
                System.out.print("Enter Reminders Name: ");
                b.setReminderName(InputValidator.readString());
                System.out.print("Enter Reminders Due Date: ");
                b.setDueDate(InputValidator.readString());
                System.out.print("Enter Reminders Service Location: ");
                b.setLocation(InputValidator.readString());
                System.out.print("Enter Vehicle ID: ");
                b.setVehicleID(InputValidator.readIntNumber("Try again"));
                System.out.print("Enter Reminder ID (3 digits): ");
                b.setReminderID(InputValidator.readIntNumber("Try again"));
                saveRemindersToFile();
            }
            i += 1;
        }
    }

    public static void viewReminderParts() throws IOException {
        System.out.print("Enter Reminder ID You Wish to view Parts List For: ");
        int tempID = InputValidator.readIntNumber("Try again");
        boolean loop = true;
        int tempsetID = 0;
        while (loop = true) {
            loadParts();
            for (int i = 0; i < RemindersList.size(); i++) {
                Reminder a = RemindersList.get(i);
                if (tempID == a.getReminderID()) {
                    Screen.displayHeader("Parts");
                    System.out.println("   Part Name   |" + "   Part ID   ");
                    for (int j = 0; j < PartsList.size(); j++) {
                        Part b = PartsList.get(j);
                        if (tempID == b.getID()) {
                            System.out.println("   " + b.getPartName() + "   |    " + b.getID() + "   ");
                        }
                    }


                }
                if (tempID == a.getReminderID()) {
                    tempsetID = tempID;
                    //System.out.println("Setting TempSet to:" + tempsetID);
                    break;
                }
            }
            PartsScreen.partsOptions(tempsetID);
        }

    }

    public static void addNewPart(int tempsetID) throws IOException {
        String file = "data/parts.csv";
        System.out.print("New Part Name: ");
        String tempsetPartName = InputValidator.readString();

        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) {
            pw.println(tempsetPartName + "," + tempsetID);
        }
    }

    public static void deletePart(int tempsetID) throws IOException {
        File inputFile = new File("data/parts.csv");
        File tempFile = new File("data/temp.csv");
        String removalLine = "";
        String tempName = "";
        System.out.print("Enter the name of the Part you wish to DELETE: ");
        String partName = InputValidator.readString();
        int tempID = tempsetID;

        for (Part a : PartsList) {
            if (partName.equalsIgnoreCase(a.getPartName())
                    && tempID == a.getID()) {
                tempName = a.getPartName();
                removalLine = a.getPartName() + "," + a.getID();
                break;
            }
        }
        if (removalLine.isEmpty()) {
            System.out.println("Part not found.");
            return;
        }

        System.out.println("Are you sure you want to DELETE " + tempName + " (ID: " + tempID + ")? (Y/N)");
        String answer = InputValidator.readString();

        if (answer.equalsIgnoreCase("Y")) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile)); BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    if (currentLine.trim().equals(removalLine.trim())) {
                        continue;
                    }
                    writer.write(currentLine);
                    writer.newLine();
                }
            }

            Files.move(tempFile.toPath(), inputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            loadParts();
            System.out.println("The Part has been DELETED.");
        } else {
            System.out.println("The Part Remains.");
        }
    }
    public static void editPart() throws IOException {
        System.out.print("Enter the Name of the Part you wish to modify: ");
        String tempID = InputValidator.readString();
        for (int i = 0; i < PartsList.size(); i++) {
            Part a = PartsList.get(i);
            if (tempID.equals(a.getPartName())) {
                Part b = PartsList.get(i);
                System.out.print("Enter Parts New Name: ");
                b.setPartName(InputValidator.readString());
                //System.out.print("Enter Part ID: ");                               Left out, shouldn't be needed but is there if we change our mind
                //b.setID(InputValidator.readIntNumber("Has to be a number"));
                savePartsToFile();
            }
            i += 1;
        }
    }
}
       // else {
      //  System.out.println("No parts currently attached to this reminder");
      // public static void viewReminderParts() {
      //     System.out.print("Enter the ID of the Reminder you wish to view the parts for ");
       //    int tempID = InputValidator.readIntNumber("Try again");

       //    for (int i = 0; i < RemindersList.size(); i++) {
        //       Reminder a = RemindersList.get(i);
         //      if (tempID == a.getReminderID()) {

                   //Fill With logic to view parts that share the same ReminderID

        //       } else {
        //           System.out.println("Sorry there are no parts for this reminder");
         //      }

        //   }
    //   }


