import java.util.Scanner;

public class contractHaven{
    public static void main(String[] args) {
        security.login();
        // Username is now stored in security.getUsername() like this:
        // String username = security.getUsername();
        
        //main menu (V, C, M, S, L, Q)
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Contract Haven, " + security.getUsername() + "!");
        char choice = mainMenu.menu(scanner);
        while(choice != 'Q'){
            switch (choice) {
                case 'V':
                    //view job board
                    utils.clearScreen();
                    jobBoard.printJobBoard();
                    choice = mainMenu.menu(scanner);
                    break;
                case 'C':
                    //create new listing
                    utils.clearScreen();
                    try {
                        jobBoard.createListing(security.getUsername(), scanner);
                    } catch (Exception e) {
                        System.out.println("input file not found - something went wrong!");
                    }
                    choice = mainMenu.menu(scanner);
                    break;
                case 'M':
                    //access account
                    break;
                case 'S':
                    //search
                    break;
                case 'L':
                    //logout
                    utils.clearScreen();
                    security.clearLoginData();
                    security.login();
                    choice = mainMenu.menu(scanner);
                    break;
                case 'Q':
                    //quit
                    System.out.println("Thank you for using Contract Haven!");
                    System.exit(0);
                default:
                    System.out.println("Error: mainMenu invalid choice - something went wrong!");
            }
        }
        scanner.close();
    }
}