package edu.utsa.cs3443.fasttrack_projectdemo_javafx.model;

public class Part {
    private String partName;
    private int reminderID;
    private int partID;


    public Part(String partName, int reminderID,int partID){
        this.partName = partName;
        this.reminderID = reminderID;
        this.partID = partID;
    }

    public String getPartName(){
        return partName;
    }
    public void setPartName(String partName){
        this.partName = partName;
    }

    public int getPartID(){
        return partID;
    }
    public void setPartID(int ID){
        this.partID = ID;
    }

    public int getReminderID(){
        return reminderID;
    }
    public void setReminderID(int ID){
        this.reminderID = ID;
    }
}
