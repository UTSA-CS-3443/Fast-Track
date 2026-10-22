package model;

public class Vehicle {
    private String make;
    private String model;
    private String color;
    private String mileage;
    private double MPG;
    private double avgMPG;
    private int ID;

    public Vehicle (String make, String model, String color, String mileage, double MPG, double avgMPG, int ID){
        this.make = make;
        this.model = model;
        this.color = color;
        this.MPG = MPG;
        this.avgMPG = avgMPG;
        this.ID = ID;
    }

    public String getMake(){
        return make;
    }
    public void setMake(String make){
        this.make = make;
    }

    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }

    public String getMileage(){
        return mileage;
    }
    public void setMileage(){
        this.mileage = mileage;
    }

    public double getMPG(){
        return MPG;
    }
    public void setMPG(double MPG){
        this.MPG = MPG;
    }

    public double getAvgMPG(){
        return avgMPG;
    }
    public void setAvgMPG(double avgMPG){
        this.avgMPG = avgMPG;
    }

    public int getID(){
        return ID;
    }
    public void setID(int ID){
        this.ID = ID;
    }

}
