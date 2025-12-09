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
        //while loop terminates on Q
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
                    //update for different menu here
                    choice = mainMenu.menu(scanner);
                    break;
                case 'M':
                    //access account
                    break;
                case 'S':
                    //search
                    utils.clearScreen();
                    choice = searchMenuActions(scanner);
                    break;
                case 'L':
                    //logout
                    utils.clearScreen();
                    security.clearLoginData();
                    security.login();
                    choice = mainMenu.menu(scanner);
                    break;
                default:
                    System.out.println("Error: mainMenu invalid choice - something went wrong!");
            }
        }
        //q as input
        scanner.close();
        System.out.println("Thank you for using Contract Haven!");
        System.exit(0);
    }
    public static char searchMenuActions(Scanner scanner){
        char choice = mainMenu.searchMenu(scanner);
        //options are A, L, B, Q
        //while loop terminates on B
        while(choice != 'B'){
            switch (choice) {
                case 'A':
                    //search for account
                    System.out.println("seraching for account...");
                    choice = mainMenu.searchMenu(scanner);
                    break;
                case 'L':
                    //search for listing
                    System.out.println("seraching for lsiting...");
                    choice = mainMenu.searchMenu(scanner);
                    break;
                case 'Q':
                    //quit the program
                    System.out.println("Thank you for using Contract Haven!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Error: searchMenu invalid choice - something went wrong!");
            }
    
        }
        //user inputs B, returns to main menu
        utils.clearScreen();
        return mainMenu.menu(scanner);
    }
}