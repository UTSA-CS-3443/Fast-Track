package edu.utsa.cs3443.fasttrack_projectdemo_javafx.ConsoleAppUI;

import edu.utsa.cs3443.fasttrack_projectdemo_javafx.util.InputValidator;

import java.util.Scanner;

/**
 * The main view/controller class that executes the primary logic for the menu loop, and subsequently calls methods from each other controller class representing a screen in the application.
 */
public class MainMenuScreen extends Screen {
    public static void showMainMenu(){
        displayHeader("Main Menu");
        System.out.println();
        System.out.println("(1) View Vehicles List");
        System.out.println("(2) View Reminders List");
        System.out.println("(3) Exit");
        System.out.println();
    }

    public static void mainMenuLoop(){
        boolean looping = true;
        while (looping){
            showMainMenu();
            int mainMenuChoice = readMainMenuOption();
            switch (mainMenuChoice){
                case 1:
                    ViewVehiclesScreen.allVehiclesMenu();
                    break;
                case 2:
                    RemindersScreen.viewReminders();
                    RemindersScreen.reminderOptions();
                    break;
                case 3:
                    looping = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static int readMainMenuOption(){
        Scanner scnr = new Scanner(System.in);
        int choice = InputValidator.readIntNumberBetween(1,3,"--- ERROR: Invalid selection. Please enter an integer from 1-3");
        return choice;
    }
}
