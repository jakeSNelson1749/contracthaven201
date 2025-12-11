import java.util.Scanner;

public class mainMenu {
    public static char menu(Scanner scanner) {
        //System.out.println("Welcome to Contract Haven, " + security.getUsername() + "!");

        //Print the jobs with the least amount of time? Or most reward? Maybe both in columns?

        System.out.print("\n[V]iew job board\n[C]reate new listing\n[M]y account\n[S]earch\n[L]ogout\n[Q]uit\n\n");
        System.out.print("Enter selection: ");

        //Scanner scanner = new Scanner(System.in);
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
    }
    public static char searchMenu(Scanner scanner){
        System.out.print("\n\n[A]ccount search\n[L]isting search\n[B]ack\n[Q]uit\n\n");
        System.out.print("Enter selection: ");

        //Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        boolean isValidChoice = false;
        while(!isValidChoice){
            if(choice.equals("A") || choice.equals("L")||choice.equals("B") || choice.equals("Q")){
                isValidChoice = true;
            }
            else{
                System.out.println("Invalid Choice!");
                System.out.print("Enter selection: ");
                choice = scanner.nextLine().toUpperCase();
            }
        }
        return choice.charAt(0);
    }
    public static char jobBoardMenu(Scanner scanner){
        System.out.print("\n\n[C]ontract listing\n[A]sk a question\n[F]ilter listings\n[B]ack\n[Q]uit\n\n");
        System.out.print("Enter selection: ");

        //Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        boolean isValidChoice = false;
        while(!isValidChoice){
            if(choice.equals("C") || choice.equals("A")||choice.equals("F") || choice.equals("B") || choice.equals("Q")){
                isValidChoice = true;
            }
            else{
                System.out.println("Invalid Choice!");
                System.out.print("Enter selection: ");
                choice = scanner.nextLine().toUpperCase();
            }
        }
        return choice.charAt(0);
    }
    public static char myAccountMenu(Scanner scanner){
        System.out.println("\nCurrently logged in as: "+security.getUsername());

        System.out.print("\n[E]dit username\n[C]hange password\n[F]riends\n[L]istings active\n[N]otifications\n[B]ack\n[Q]uit\n\n");
        System.out.print("Enter selection: ");

        //Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        boolean isValidChoice = false;
        while(!isValidChoice){
            if(choice.equals("E") || choice.equals("C")||choice.equals("F") || choice.equals("L")||choice.equals("N")||choice.equals("B") || choice.equals("Q")){
                isValidChoice = true;
            }
            else{
                System.out.println("Invalid Choice!");
                System.out.print("Enter selection: ");
                choice = scanner.nextLine().toUpperCase();
            }
        }
        return choice.charAt(0);
    }
}
