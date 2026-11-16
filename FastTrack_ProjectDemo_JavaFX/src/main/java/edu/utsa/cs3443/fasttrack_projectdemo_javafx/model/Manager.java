package edu.utsa.cs3443.fasttrack_projectdemo_javafx.model;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.ConsoleAppUI.Screen;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.util.InputValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * the primary manager for vehicle, reminder, and part objects. Handles all of the operations involving reading and saving them to files.
 */
public class Manager extends Screen {
    ArrayList<Vehicle> VehiclesList = new ArrayList<>();
    ArrayList<Reminder> RemindersList = new ArrayList<>();
    ArrayList<Part> PartsList = new ArrayList<>();

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
                Vehicle e = new Vehicle(csv[0], csv[1], csv[2], csv[3], Double.parseDouble(csv[4]), Double.parseDouble(csv[5]), Integer.parseInt(csv[6]));
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
    /**
     * prints out each vehicle's ID, Make and edu.utsa.cs3443.fasttrack_projectdemo_javafx.model
     */
    public void printVehiclesList(){
        System.out.println();
        for (Vehicle a : VehiclesList){
            System.out.println((VehiclesList.indexOf(a)+1) + ": " + a.getID() + " " + a.getMake() + " " + a.getModel());
        }
        System.out.println();
    }
    /**
     * prints a selected vehicle's info, and the reminders that share the vehicle's ID.
     * @param vehicleID the integer that the user inputs from the menu interface, which is used to select the corresponding vehicle from the vehiclesList arraylist
     */
    public void printVehicleCard(int vehicleID){
        Vehicle currVehicle = getVehicleByID(vehicleID);

        System.out.printf("%-15s %s\n", "Make:", currVehicle.getMake());
        System.out.printf("%-15s %s\n", "Model:", currVehicle.getModel());
        System.out.printf("%-15s %s\n", "Color:", currVehicle.getColor());
        System.out.printf("%-15s %s\n", "Mileage:", currVehicle.getMileage());
        System.out.printf("%-15s %s\n", "MPG:",currVehicle.getMPG());
        System.out.printf("%-15s %s\n", "Avg MPG:", currVehicle.getAvgMPG());
        System.out.printf("%-15s %s\n", "ID:", currVehicle.getID());
    }

    /**
     * prints all the reminders associated with a SPECIFIC vehicle
     * @param vehicleID
     */
    public void printVehicleReminders(int vehicleID){
        Vehicle currVehicle = getVehicleByID(vehicleID);
        for (Reminder a : RemindersList){
            if (a.getVehicleID() == currVehicle.getID()){
                System.out.println("- ("+ a.getReminderID() + ") " + a.getReminderName());
            }
        }
    }

    /**
     * prompts user for attributes, creates new vehicle and saves it to csv file.
     */
    public void addNewVehicle(){
        Scanner scnr = new Scanner(System.in);
        System.out.println("Creating new vehicle. enter attributes:");
        System.out.println("Enter make:");
        String make = scnr.nextLine();
        System.out.println("Enter edu.utsa.cs3443.fasttrack_projectdemo_javafx.model:");
        String model = scnr.nextLine();
        System.out.println("Enter color:");
        String color = scnr.nextLine();
        System.out.println("Enter mileage:");
        String mileage = scnr.nextLine();
        System.out.println("Enter MPG");
        double MPG = scnr.nextDouble();
        System.out.println("Enter avgMPG:");
        double avgMPG = scnr.nextDouble();
        System.out.println("Enter ID:");
        int ID = vehicleIDValidator();

        Vehicle V = new Vehicle(make, model, color, mileage, MPG, avgMPG, ID);
        VehiclesList.add(V);
        System.out.println("New vehicle successfully added!");

        saveVehiclesToFile();
    }
    /**
     * edits the attributes of a vehicle identified by the passed ID parameter, which is not changed.
     * Attributes are left unaltered if the passed arguments are blank, and changes are saved to file
     * at the end of the method, because new Manager objects will constantly be reading the csv file and
     * the information must be updated upon every change.
     *
     * @param make new make value for the specified vehicle
     * @param model new mode value for the specified vehicle
     * @param color new color value for the specified vehicle
     * @param mileage new value for the specified vehicle
     * @param MPG new value for the specified vehicle. Passed as a String so that the (MPG == "null") check can be performed.
     * @param avgMPG new value for the specified vehicle. Passed as a String for the same reason as MPG.
     * @param ID the ID of the vehicle being changed, it is passed and not read from input/changed.
     */
    public void editVehicle(String make, String model, String color, String mileage, String MPG, String avgMPG, int ID){
        Vehicle editedVehicle = new Vehicle("", "", "", "",0.0, 0.0, ID);
        //getVehicleByID is returning the vehicle object that is already in the array.
        int index = 0;
        for (int i = 0; i < VehiclesList.size(); i++){
            if (VehiclesList.get(i).getID() == ID){
                index = i;
            }
        }

        if (!(make.equals(""))){
            VehiclesList.get(index).setMake(make);
        }
        if (!(model.equals(""))){
            VehiclesList.get(index).setModel(model);
        }
        if (!(color.equals(""))){
            VehiclesList.get(index).setColor(color);
        }
        if (!(mileage.equals(""))){
            VehiclesList.get(index).setMileage(mileage);
        }
        double newMPG = doubleNullInputValidator();
        if (newMPG != 0){
            VehiclesList.get(index).setMPG(newMPG);
        }
        double newAvgMPG = doubleNullInputValidator();
        if (newAvgMPG != 0) {
            VehiclesList.get(index).setAvgMPG(newAvgMPG);
        }
        saveVehiclesToFile();
        System.out.println("*** Vehicle edits successfully saved to file! ***");
    }

    /**
     * promtps user to enter the ID of a vehicle which is then deleted from the csv file.
     * @param ID
     */
    public void deleteVehicle(int ID){
        int deletingIndex = 0;
        for (int i = 0; i < VehiclesList.size(); i++){
            if (VehiclesList.get(i).getID() == ID){
                deletingIndex = i;
            }
        }
        VehiclesList.remove(deletingIndex);
        System.out.println("Vehicle successfully deleted!");
        saveVehiclesToFile();
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
    private void saveVehiclesToFile(){
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
     * prints all reminders
     */
    public void printRemindersList() {
        System.out.printf("  %-22s | %-10s | %-24s | %-12s | %-13s%n", "Name", "Due Date", "Location", "Vehicle ID", "Reminder ID");
        for (int i = 0; i < RemindersList.size(); i++) {
            Reminder a = RemindersList.get(i);
            System.out.printf("%d %-22s | %-10s | %-24s | %-12d | %-13d%n",
                    (i+1), a.getReminderName(), a.getDueDate(), a.getLocation(), a.getVehicleID(), a.getReminderID());
        }
    }

    /**
     * prompts user for new attributes, creates new reminder and saves to file
     */
    public void addNewReminder() {
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
        int tempsetReminderID = reminderIDValidator();

        try (FileWriter fw = new FileWriter(file, true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter pw = new PrintWriter(bw)) {
            pw.println(tempsetReminderName + "," + tempsetDueDate + "," + tempsetLocation + "," + tempsetVehicleID + "," + tempsetReminderID);
        } catch(IOException e){
            System.out.println("Error occured adding reminder: " + e.getMessage());
        }

    }

    /**
     * prompts user for a reminderID, and deletes that reminder from file.
     * @throws IOException
     */
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

    /**
     * prompts user for edits to a reminder, and saves changes to file
     */
    public void editReminder() {
        System.out.println("Editing reminder information. Enter a new value or leave fields blank to leave them unchanged. ");
        System.out.print("Enter the ID of the Reminder you wish to modify: ");
        int tempID = findReminderID();
        int editingIndex = 0;
        for (int i = 0; i < RemindersList.size(); i++) {
            if (tempID == RemindersList.get(i).getReminderID()) {
                editingIndex = i;
            }
        }
        System.out.print("Enter Reminder Name: ");
        String reminderName = InputValidator.readString();
        if (!(reminderName.isEmpty())){
            RemindersList.get(editingIndex).setReminderName(reminderName);
        }
        System.out.print("Enter Reminder Due Date: ");
        String dueDate = InputValidator.readString();
        if (!(dueDate.isEmpty())){
            RemindersList.get(editingIndex).setDueDate(dueDate);
        }
        System.out.print("Enter Reminder Service Location: ");
        String location = InputValidator.readString();
        if (!(location.isEmpty())){
            RemindersList.get(editingIndex).setLocation(location);
        }
        System.out.print("Enter Vehicle ID for reminder: ");
        int vehicleID = intNullInputValidator();
        if (vehicleID != -1){
            RemindersList.get(editingIndex).setVehicleID(vehicleID);
        }

        saveRemindersToFile();
        System.out.println("Reminder changes saved to file!");
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

    /**
     * saves all the object information in the RemindersList arrayList to file
     */
    private void saveRemindersToFile(){
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
     * prints all the parts associated with a specific reminder
     */
    public void viewReminderParts(){
        System.out.print("Enter a reminder's ID to view it's required parts: ");
        int inputPartReminderID = findReminderID();
        int reminderIndex = 0;


        for (int i = 0; i < RemindersList.size(); i++) {
            Reminder a = RemindersList.get(i);
            if (a.getReminderID() == inputPartReminderID){
                reminderIndex = i;
            }
        }


        displayHeader("Required Parts for: " + RemindersList.get(reminderIndex).getReminderName());
        System.out.println("Note: each part has a reminderID which represents its associated reminder, and a partID to identify the actual part.");
        System.out.printf("  %-20s | %-20s | %-20s%n", "Name","Reminder ID","PartID");
        for (int i = 0; i < PartsList.size(); i++) {
            Part a = PartsList.get(i);
            if (a.getReminderID() == inputPartReminderID){
                System.out.printf("  %-20s | %-20s | %-20s%n", a.getPartName(),a.getReminderID(),a.getPartID());
            }
        }
    }

    /**
     * prompts user for attibutes, creates a new part, and saves it to file
     * @throws IOException
     */
    public void addNewPart() throws IOException {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Adding new part. Enter attributes:");
        System.out.println("Enter part name:");
        String partName = scnr.nextLine();
        System.out.println("Enter the ID of the reminder this part is associated with:");
        int reminderID = findReminderID();
        System.out.println("Enter a partID for this part:");
        int partID = partIDValidator();

        Part P = new Part(partName, reminderID, partID);
        PartsList.add(P);
        System.out.println("New part successfully added!");

        savePartsToFile();
    }

    /**
     * prompts user for a partID, and deletes that part from file.
     * @throws IOException
     */
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
     * prompts user for changes to a part, and saves those changes to file
     * @throws IOException
     */
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
    private void savePartsToFile() throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter("data/parts.csv"));
        for (Part a : PartsList) {
            String line = convertPartToLine(a);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
    /**
     * repeatedly asks user for ID when creating/editing vehicle, loops until an unused ID is found
     * @return returns the validated ID
     */
    private int vehicleIDValidator(){
        Scanner scnr = new Scanner(System.in);
        boolean looping = true;
        int ID = 0;
        while (looping){
            ID = InputValidator.readIntNumber("Error: enter a valid integer");
            looping = false;
            for (Vehicle a : VehiclesList){
                if (a.getID() == ID){
                    looping = true;
                    System.out.println("Error: this ID is already in use. enter a valid ID.");
                }
            }
        }
        return ID;
    }

    /**
     * returns a valid partID from the objects in PartsList
     * @return a valid partID from an existing part object
     */
    private int partIDValidator(){
        Scanner scnr = new Scanner(System.in);
        boolean looping = true;
        int partID = 0;
        while (looping){
            partID = InputValidator.readIntNumber("Error: enter a valid integer");
            looping = false;
            for (Part a : PartsList){
                if (a.getPartID() == partID){
                    looping = true;
                    System.out.println("Error: this partID is already in use. enter a valid partID.");
                }
            }
        }
        return partID;
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
}
