import java.util.Scanner;

public class mainMenu {
    public static char menu() {
        //utils.clearScreen();
        System.out.println("\nWelcome to Contact Haven, " + security.getUsername() + "!");

        //Print the jobs with the least amount of time? Or most reward? Maybe both in columns?

        System.out.print("\n[V]iew job board\n[C]reate new listing\n[M]y account\n[S]earch\n[L]ogout\n[Q]uit\n\n");
        System.out.print("Enter selection: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        boolean isValidChoice = false;
        while(!isValidChoice){
            if(choice.equals("V") || choice.equals("C")||choice.equals("M") || choice.equals("S")||choice.equals("L") || choice.equals("Q")){
                isValidChoice = true;
            }
            else{
                System.out.println("Invalid Choice!");
                System.out.print("Enter selection: ");
                choice = scanner.nextLine().toUpperCase();
            }
        }
        return choice.charAt(0);
        /* 
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
        */
    }
}
