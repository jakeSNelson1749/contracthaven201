import java.util.Scanner;

public class mainMenu {
    public static void menu() {
        utils.clearScreen();
        System.out.println("Welcome to Contact Haven, " + security.getUsername() + "!\n");

        //Print the jobs with the least amount of time? Or most reward? Maybe both in columns?

        System.out.println("Menu Options:");
        System.out.println("[N]ew job post, [V]iew job posts, [A]pply for job, [L]ogout, [Q]uit");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        switch (choice) {
            case "N":
                break;
            case "V":
                break;
            case "A":
                break;
            case "L":
                utils.clearScreen();
                security.clearLoginData();
                security.login();
                menu();
                break;
            case "Q":
                System.out.println("Thank you for using Contract Haven!");
                System.exit(0);
            default:
                menu();
        }
    }
}
