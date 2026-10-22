package model;

public class Reminder {
    private String reminderName;
    private String dueDate;
    private String location;
    private int VehicleID;
    private int reminderID;

    /**
     *
     * @param reminderName description of the reminder
     * @param dueDate the reminder must be completed by this date
     * @param location the shop where the repair will happen
     * @param VehicleID each reminder will contain the ID of the vehicle it belongs to, as well as an ID for identifying the acutal reminder itself, which has less digits.
     * @param reminderID the ID for identifying each individual reminder
     */
    public Reminder(String reminderName, String dueDate, String location, int VehicleID, int reminderID){
        this.reminderName = reminderName;
        this.dueDate = dueDate;
        this.location = location;
        this.VehicleID = VehicleID;
        this.reminderID = reminderID;
    }
    public String getReminderName(){
        return reminderName;
    }
    public void setReminderName(String reminderName){
        this.reminderName = reminderName;
    }

    public String getDueDate(){
        return dueDate;
    }
    public void setDueDate(String dueDate){
        this.dueDate = dueDate;
    }

    public int getVehicleID(){
        return VehicleID;
    }
    public void setVehicleID(int ID){
        this.VehicleID = VehicleID;
    }

    public int getReminderID(){
        return reminderID;
    }
    public void setReminderID(int reminderID){
        this.reminderID = reminderID;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation (String location){
        this.location = location;
    }
}
