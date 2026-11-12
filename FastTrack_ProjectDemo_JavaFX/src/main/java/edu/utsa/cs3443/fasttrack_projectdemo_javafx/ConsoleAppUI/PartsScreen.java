package edu.utsa.cs3443.fasttrack_projectdemo_javafx.ConsoleAppUI;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.model.Manager;
import edu.utsa.cs3443.fasttrack_projectdemo_javafx.util.InputValidator;

import java.io.IOException;

/**
 * the navigation menu for parts; the menu only displays parts associated with a certain reminder
 */
public class PartsScreen extends Screen {
    public static void partsMenu(){
        Manager a = new Manager();
        a.viewReminderParts();
        System.out.println();
        System.out.println("(1) Add New Part");
        System.out.println("(2) Edit Part");
        System.out.println("(3) Delete Part");
        System.out.println("(4) Exit");

        int MenuChoice = InputValidator.readIntNumber("Error: enter a valid integer between 1-4");
        switch (MenuChoice) {
            case 1:
                try{
                    a.addNewPart();
                }catch(IOException e){
                    System.out.println("Error occured adding new part to file: " + e.getMessage());
                }
                break;
            case 2:
                try{
                    a.editPart();
                }catch(IOException e){
                    System.out.println("Error occured editing part: " + e.getMessage());
                }
                break;
            case 3:
                try{
                    a.deletePart();
                }catch(IOException e){
                    System.out.println("Error occured deleting part: " + e.getMessage());
                }
                break;
            case 4:
                break;

        }
    }
}
