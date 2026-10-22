package model;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Manager {
    static ArrayList<Vehicle> VehiclesList = new ArrayList<>();
    static ArrayList<Reminder> RemindersList = new ArrayList<>();
    static ArrayList<Part> PartsList = new ArrayList<>();

    public Manager(){
        try{
            loadVehicles();
            loadReminders();
            loadParts();
        }catch(IOException e){

        }
    }

    public static void loadVehicles() throws IOException {
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/vehicles.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                Vehicle e = new Vehicle(csv[0], csv[1], csv[2], csv[3], Double.parseDouble(csv[4]), Double.parseDouble(csv[5]), Integer.parseInt(csv[6]));
                VehiclesList.add(e);
            }
        }catch(IOException e){

        }
    }

    public static void loadReminders() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("data/reminders.csv"));
        while ((line = br.readLine()) != null) {
            String[] csv = line.split(",");
            Reminder e = new Reminder(csv[0], csv[1], csv[2], Integer.parseInt(csv[3]), Integer.parseInt(csv[4]));
            RemindersList.add(e);
        }
    }

    public static void loadParts() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        BufferedReader br = new BufferedReader(new FileReader("data/parts.csv"));
        while ((line = br.readLine()) != null) {
            String[] csv = line.split(",");
            Part e = new Part(csv[0], Integer.parseInt(csv[2]));
            PartsList.add(e);
        }
    }

    private static String convertVehicleToLine(Vehicle a){
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
    private static String convertReminderToLine(Reminder a){
        String line = "";
        line += a.getReminderName();
        line += ",";
        line += a.getDueDate();
        line += ",";
        line += a.getVehicleID();
        line += ",";
        line += a.getReminderID();

        return line;
    }
    private static String convertPartToLine(Part a){
        String line = "";
        line += a.getPartName();
        line += ",";
        line += a.getID();

        return line;
    }

    private static void saveVehiclesToFile() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/vehicles.csv"));
        for (Vehicle a : VehiclesList) {
            String line = convertVehicleToLine(a);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
    private static void saveRemindersToFile() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/reminders.csv"));
        for (Reminder a : RemindersList) {
            String line = convertReminderToLine(a);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
    private static void savePartsToFile() throws IOException{
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
    public static void printVehiclesList(){
        for (Vehicle a : VehiclesList){
            System.out.println((VehiclesList.indexOf(a)+1) + ": " + a.getID() + " " + a.getMake() + " " + a.getModel());
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * checks if model.Vehicle exists, using a user input for the vehicle's index in the VehiclesList arraylist. Mainly used from main menu
     * @param menuSelection
     * @return
     */
    public static boolean doesVehicleExistIndexVer(int menuSelection){
        if (menuSelection - 1 < 0 || menuSelection-1 > VehiclesList.size()){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * checks if vehicle exists, using its ID instead.
     * @param submittedVehicleID
     * @return
     */
    public boolean doesVehicleExistIDVer(int submittedVehicleID){
        for (Vehicle a : VehiclesList){
            if (submittedVehicleID == a.getID()){
                return true;
            }
        }
        return false;
    }

    /**
     * accepts a vehicle's ID, searches the manager object's arrayList, and returns the matching vehicleobject
     * @param inputVehicleID integer ID that is checked against the IDs of objects in the VehiclesList arraylist
     * @return the found vehicle
     */
    public static Vehicle findVehicleByID(int inputVehicleID){
        for (Vehicle a : VehiclesList){
            if (inputVehicleID == a.getID()){
                return a;
            }
        }
        System.out.println("*** ERROR: unable to find vehicle via ID ***");
        return null;
    }

    /**
     * prints a selected vehicle's info, and the reminders that share the vehicle's ID.
     * @param vehicleID the integer that the user inputs from the menu interface, which is used to select the corresponding vehicle from the vehiclesList arraylist
     */
    public static void printVehicleCard(int vehicleID){
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

    public static void printVehicleReminders(int vehicleID){
        Vehicle currVehicle = findVehicleByID(vehicleID);

        System.out.println("(Reminders for " + currVehicle.getMake() + " " + currVehicle.getModel() + "):");
        for (Reminder a : RemindersList){
            if (a.getVehicleID() == currVehicle.getID()){
                System.out.println("- ("+ a.getReminderID() + ") " + a.getReminderName());
            }
        }
    }

    public static void editReminder(int editReminderID){

    }

    public boolean doesReminderExist(int submittedReminderID){
        for (Reminder a : RemindersList){
            if (submittedReminderID == a.getReminderID()){
                return true;
            }
        }
        return false;
    }

    public void updateReminder(int readReminderID, String newReminderName, String newDueDate, String newLocation) throws IOException{
        // first finds the reminder that we will be changing. the new object starts out identical to the one being edited
        // reminderID stays the same
        // I dont think??? the vehicleID is needed, because the reminder you're editing already has the matching reminderID, which means it has the matching vehicleID
        for (Reminder a : RemindersList){
            if (a.getReminderID() == readReminderID){
                //each field is only updated if there was a new value entered. if not, it stays the same.
                if (!(newReminderName == null)){
                    a.setReminderName(newReminderName);
                }
                if (!(newDueDate == null)){
                    a.setDueDate(newDueDate);
                }
                if (!(newLocation == null)){
                    a.setLocation(newLocation);
                }
                saveRemindersToFile();
            }
        }
    }
}
