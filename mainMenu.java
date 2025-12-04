import java.util.Scanner;

public class mainMenu {
    public static void menu() {
        utils.clearScreen();
        System.out.println("\nWelcome to Contact Haven, " + security.getUsername() + "!");

        //Print the jobs with the least amount of time? Or most reward? Maybe both in columns?

        System.out.print("\n[V]iew job board\n[C]reate new listing\n[M]y account\n[S]earch\n[L]ogout\n[Q]uit\n\n");
        System.out.print("Enter selection: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        switch (choice) {
            case "V":
                //view job board
                jobBoard.printJobBoard();
                break;
            case "C":
                //create new listing
                break;
            case "M":
                //access account
                break;
            case "S":
                //search
                break;
            case "L":
                //logout
                utils.clearScreen();
                security.clearLoginData();
                security.login();
                menu();
                break;
            case "Q":
                //quit
                System.out.println("Thank you for using Contract Haven!");
                System.exit(0);
            default:
                menu();
        }
    }
}
