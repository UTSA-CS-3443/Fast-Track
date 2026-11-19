package edu.utsa.cs3443.fasttrack_projectdemo_javafx.model;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.util.InputValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * the primary manager for vehicle, reminder, and part objects. Handles all of the operations involving reading and saving them to files.
 */
public class Manager{
    ArrayList<Vehicle> VehiclesList = new ArrayList<>();
    ArrayList<Reminder> RemindersList = new ArrayList<>();
    ArrayList<Part> PartsList = new ArrayList<>();

    Random rand = new Random();

    public Manager(){
        try{
            loadVehicles();
            loadReminders();
            loadParts();
        }catch(IOException e){

        }
    }
    /**
     * loads vehicles from csv file
     * @throws IOException
     */
    public ArrayList<Vehicle> getVehiclesList(){
        return VehiclesList;
    }
    public void loadVehicles() throws IOException {
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/vehicles.csv"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                Vehicle e = new Vehicle(csv[0], csv[1], csv[2], Integer.parseInt(csv[3]), Double.parseDouble(csv[4]), Double.parseDouble(csv[5]), Integer.parseInt(csv[6]));
                VehiclesList.add(e);
            }
            br.close();
        }catch(IOException e){
            System.out.println("Error occured loading vehicles from file: " + e.getMessage());
        }
    }
    /**
     * loads reminders from csv file.
     */
    public void loadReminders() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/reminders.csv"));
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                Reminder e = new Reminder(csv[0], csv[1], csv[2], Integer.parseInt(csv[3]), Integer.parseInt(csv[4]));
                RemindersList.add(e);
            }
            br.close();
        } catch (IOException e){
            System.out.println("Error loading reminders: " + e.getMessage());
        }
    }
    /**
     * loads parts from csv file.
     */
    public void loadParts(){
        Scanner scanner = new Scanner(System.in);
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/parts.csv"));
            while ((line = br.readLine()) != null) {
                String[] csv = line.split(",");
                Part e = new Part(csv[0], Integer.parseInt(csv[1]), Integer.parseInt(csv[2]));
                PartsList.add(e);
            }
            br.close();
        } catch (IOException e){
            System.out.println("Error occured loading parts from file: " + e.getMessage());
        }
    }
    /**
     * checks if edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Vehicle exists, using a user input for the vehicle's index in the VehiclesList arraylist. Mainly used from main menu
     * @param menuSelection
     * @return
     */
    public boolean doesVehicleExistIndexVer(int menuSelection){
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
    public Vehicle getVehicleByID(int inputVehicleID){
        for (Vehicle a : VehiclesList){
            if (inputVehicleID == a.getID()){
                return a;
            }
        }
        System.out.println("*** ERROR: unable to find vehicle via ID ***");
        return null;
    }

    /**
     * same function as the above method, but for reminders
     * @param inputReminderID
     * @return
     */
    public Reminder getReminderByID(int inputReminderID){
        for (Reminder a : RemindersList){
            if (inputReminderID == a.getReminderID()){
                return a;
            }
        }
        return null;
    }
    public Part getPartByID(int inputPartID){
        for (Part a : PartsList){
            if (inputPartID == a.getPartID()){
                return a;
            }
        }
        return null;
    }

    /**
     * finds all reminders associated with a vehicle for the VehicleDetails controller
      * @param vehicleID the vehicle
     * @return ArrayList of all associated reminders to be used in ListView
     */
    public ArrayList<Reminder> getVehicleRemindersList (int vehicleID){
        ArrayList<Reminder> list = new ArrayList<>();
        for (Reminder a : RemindersList){
            if (a.getVehicleID() == vehicleID){
                list.add(a);
            }
        }
        return list;
    }
    public ArrayList<Part> getReminderPartsList (int reminderID){
        ArrayList<Part> list = new ArrayList<>();
        for (Part a : PartsList){
            if (a.getReminderID() == reminderID){
                list.add(a);
            }
        }
        return list;
    }











    public void addVehicle(String make, String model, String color, int mileage, double MPG, double avgMPG){
        int ID = generateID();
        Vehicle V = new Vehicle(make, model, color, mileage, MPG, avgMPG, ID);
        VehiclesList.add(V);
        saveVehiclesToFile();
    }
    public void addReminder(String reminderName, String dueDate, String location, int vehicleID) {
    // remember the vehicleID must be appropriately passed/assigned, but reminderID will be generated
        Reminder R = new Reminder(reminderName, dueDate, location, vehicleID, generateID());
        RemindersList.add(R);
        saveRemindersToFile();
    }

    /**
     * registers a new part and saves to file
     * @param partName passed from AddPartController
     * @param reminderID uses the currReminderID static variable to keep track of which reminder
     *                   this part should be assigned to
     */
    public void addPart(String partName, int reminderID){
        int partID = generateID();
        Part P = new Part(partName, reminderID, partID);
        PartsList.add(P);
        savePartsToFile();
    }
    public void editVehicle(String make, String model, String color, String mileage, String MPG, String avgMPG, int ID){
    }
    public void editPart() throws IOException {
        int partEditIndex = 0;
        System.out.print("Enter the partID of the part you wish to modify: ");
        int editPartID = findPartID();
        for (int i = 0; i < PartsList.size(); i++) {
            Part a = PartsList.get(i);
            if (editPartID == a.getPartID()) {
                partEditIndex = i;
            }
        }
        System.out.println("Editing part. Enter new values, or leave fields blank to keep them unchanged");
        System.out.print("Enter part name: ");
        PartsList.get(partEditIndex).setPartName(InputValidator.readString());
        savePartsToFile();
        System.out.println("Part edits saved to file!");
    }

    public void editReminder() {
    }
    public void deleteVehicle(){
    }
    public void deleteReminder() throws IOException {
        File inputFile = new File("data/reminders.csv");
        File tempFile = new File("data/temp.csv");
        String removalLine = "";
        String tempName = "";

        System.out.print("Enter the ID of the Reminder you wish to DELETE: ");
        int tempID = InputValidator.readIntNumber("Error: Enter a valid integer.");
        int i = 0;

        for (Reminder a : RemindersList) {
            if (tempID == a.getReminderID()) {
                Reminder b = RemindersList.get(i);
                tempName = b.getReminderName();
                removalLine = (b.getReminderName() + "," + b.getDueDate() + "," + b.getLocation() + "," + b.getVehicleID() + "," + b.getReminderID());
            }
            i += 1;
        }
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
    }

    public void deletePart() throws IOException {
        System.out.println("Enter the partID of the part you wish to delete:");
        int partID = findPartID();
        int deletingIndex = 0;
        for (int i = 0; i < PartsList.size(); i++){
            if (PartsList.get(i).getPartID() == partID){
                deletingIndex = i;
            }
        }
        PartsList.remove(deletingIndex);
        System.out.println("Part successfully deleted!");
        savePartsToFile();
    }












    /**
     * returns -1 if input is empty, returns the integer parsed from the input string if Integer.parseInt is successful. Otherwise, keeps looping.
     * @return valid integer input read from a String
     */
    private int intNullInputValidator(){
        Scanner scnr = new Scanner(System.in);
        boolean looping = true;
        while (looping){
            String b = scnr.nextLine();
            try{
                Integer.parseInt(b);
                return Integer.parseInt(b);
            } catch (Exception e){
                if (b.isEmpty()){
                    looping = false;
                    return -1;
                }
                else{
                    System.out.println("Error: invalid input detected. Enter an integer, or leave input blank to leave the vehicleID unchanged.");
                }
            }
        }
        return -1;
    }

    /**
     * same function as intNullInputValidator, but for doubles.
     * @return
     */
    private double doubleNullInputValidator(){
        Scanner scnr = new Scanner(System.in);
        boolean looping = true;
        while (looping){
            String b = scnr.nextLine();
            try{
                Double.parseDouble(b);
                return Double.parseDouble(b);
            } catch (Exception e){
                if (b.isEmpty()){
                    looping = false;
                    return -1;
                }
                else{
                    System.out.println("Error: invalid input detected. Enter a double, or leave input blank to leave the value unchanged.");
                }
            }
        }
        return -1;
    }
    /**
     * repeatedly asks user for a valid reminderID when creating/editing vehicle, loops until an unused ID is found
     * @return the available reminderID
     */
    private int reminderIDValidator(){
        Scanner scnr = new Scanner(System.in);
        boolean looping = true;
        int reminderID = 0;
        while (looping){
            reminderID = InputValidator.readIntNumber("Error: enter a valid integer");
            looping = false;
            for (Reminder a : RemindersList){
                if (a.getReminderID() == reminderID){
                    looping = true;
                    System.out.println("Error: this reminderID is already in use. enter a valid reminderID.");
                }
            }
        }
        return reminderID;

    }
    public int getVehiclesListSize(){
        return VehiclesList.size();
    }

    /**
     * returns a valid reminderID from the objects in RemindersList
     * @return
     */
    public int findReminderID(){
        Scanner scnr = new Scanner(System.in);
        boolean looping = true;
        while (looping){
            int tempID = InputValidator.readIntNumber("Error: enter a valid integer");
            for (Reminder a : RemindersList){
                if (a.getReminderID() == tempID){
                    System.out.println("ID matched!");
                    return tempID;
                }
            }
            System.out.println("Error: reminderID not found. Enter a valid reminderID");
        }
        return 0; // should never be reached
    }

    /**
     * same function as findReminderID, but for parts
     * @return
     */
    public int findPartID(){
        Scanner scnr = new Scanner(System.in);
        boolean looping = true;
        while (looping){
            int tempID = InputValidator.readIntNumber("Error: enter a valid integer");
            for (Part a : PartsList){
                if (a.getPartID() == tempID){
                    System.out.println("partID matched!");
                    return tempID;
                }
            }
            System.out.println("Error: partID not found. Enter a valid partID");
        }
        return 0; // should never be reached
    }

    /**
     * converts a vehicle object's attributes to a comma separated string in order to write information to a csv file
     * @param a
     * @return
     */
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

    /**
     * saves all the vehicle objects in the VehiclesList array to file
     */
    public void saveVehiclesToFile(){
        Path path = Paths.get("data/vehicles.csv");
        try {
            Files.delete(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/vehicles.csv", false));
            for (Vehicle a : VehiclesList) {
                String line = convertVehicleToLine(a);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Error occured saving vehicle to file: "+ e.getMessage());
        }
    }
    /**
     * turns a part's information into a comma-separated string to save to a csv file
     * @param a
     * @return
     */
    private String convertPartToLine(Part a){
        String line = "";
        line += a.getPartName();
        line += ",";
        line += a.getReminderID();
        line += ",";
        line += a.getPartID();

        return line;
    }

    /**
     * saves all of the parts currently in PartsList to file
     * @throws IOException
     */
    public void savePartsToFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/parts.csv"));
            for (Part a : PartsList) {
                String line = convertPartToLine(a);
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        }catch(IOException e){
            System.out.println("Error occured saving parts to file: " + e.getMessage());
        }
    }
    /**
     * turns a reminder object's information into a comma-separated string to save to a csv file
     * @param a
     * @return
     */
    private String convertReminderToLine(Reminder a){
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
    public ArrayList<Reminder> getRemindersList(){
        return RemindersList;
    }
    /**
     * saves all the object information in the RemindersList arrayList to file
     */
    public void saveRemindersToFile(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/reminders.csv"));
            for (Reminder a : RemindersList) {
                String line = convertReminderToLine(a);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        }catch(IOException e){
            System.out.println("Error occured saving reminders to file: " + e.getMessage());
        }
    }

    /**
     * generates unique ID numbers for objects by checking if they have been used in any of the 3 object arrays
     * @return a unique id in the range 1-99999
     */
    public int generateID(){
        int ID = 0;
        String formattedID;
        boolean isReused = true;
        while (isReused){
            isReused = false;
            ID = rand.nextInt(99999)+1;
            for (Vehicle a : VehiclesList){
                if (a.getID() == ID){
                    isReused = true;
                }
            }
            for (Reminder b : RemindersList){
                if (b.getReminderID() == ID){
                    isReused = true;
                }
            }
            for (Part c : PartsList){
                if (c.getPartID() == ID){
                    isReused = true;
                }
            }
        }
        formattedID = String.format("%05d", ID);
        return Integer.parseInt(formattedID);
    }

}
