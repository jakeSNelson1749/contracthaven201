public class contractHaven{
    public static void main(String[] args) {
        security.login();
        // Username is now stored in security.getUsername() like this:
        // String username = security.getUsername();
        
        //main menu (V, C, M, S, L, Q)
        char choice = mainMenu.menu();
        switch (choice) {
            case 'V':
                //view job board
                jobBoard.printJobBoard();
                System.out.println("\n");
                choice = mainMenu.menu();
                break;
            case 'C':
                //create new listing
                utils.clearScreen();
                //function to create new listing
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
                choice = mainMenu.menu();
                break;
            case 'Q':
                //quit
                System.out.println("Thank you for using Contract Haven!");
                System.exit(0);
            default:
                System.out.println("Error: mainMenu invalid choice");
        }
    }
}