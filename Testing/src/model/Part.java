package model;

public class Part {
    private String partName;
    private int ID;

    public Part(String partName, int ID){
        this.partName = partName;
        this.ID = ID;
    }

    public String getPartName(){
        return partName;
    }
    public void setPartName(String partName){
        this.partName = partName;
    }

    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }
}
