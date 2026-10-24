package UI;

public class Screen {
    protected static void displayHeader(String header){
        System.out.println("-----------------------------------------------------------------");
        System.out.println(header);
        System.out.println("-----------------------------------------------------------------");
    }

    protected static void promptMessage(String prompt){
        System.out.println("===== " +prompt+ " =====");

    }

    protected static void errorMessage(String message){
        System.out.println("*** " + message + " ***");
    }
}
