package edu.utsa.cs3443.fasttrack_projectdemo_javafx.ConsoleAppUI;

public class Screen {
    protected static void displayHeader(String header){
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%60s %n", header);
        System.out.println("-----------------------------------------------------------------");
    }
    protected static void errorMessage(String message){
        System.out.println("*** " + message + " ***");
    }
}
