import java.util.Scanner;

import javax.management.Notification;

import java.util.ArrayList;

public class contractHaven{
    public static void main(String[] args) {
        security.login();
        // Username is now stored in security.getUsername() like this:
        // String username = security.getUsername();
        
        ArrayList<account> accountList = utils.loadAccountList();
        account myAccount = null;
        for(account a : accountList){
            if(a.getUserName().equals(security.getUsername())){
                myAccount = a;
            }
        }
        //account being used is not found in accountlist
        if(myAccount == null){
            System.out.println("something went wrong!");
            System.exit(0);
        }

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
                    choice = jobBoardMenuActions(scanner);
                    break;
                case 'C':
                    //create new listing
                    utils.clearScreen();
                    try {
                        jobBoard.createListing(myAccount, security.getUsername(), scanner);
                    } catch (Exception e) {
                        System.out.println("input file not found - something went wrong!");
                    }
                    //update for different menu here
                    choice = mainMenu.menu(scanner);
                    break;
                case 'M':
                    //access account
                    utils.clearScreen();
                    choice = accountMenuActions(scanner, myAccount);
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
    public static char accountMenuActions(Scanner scanner, account myAccount){
        char choice = mainMenu.myAccountMenu(scanner);
        //options are E C F L N B Q
        //while loop terminates on B
        while(choice != 'B'){
            switch (choice) {
                case 'E':
                    //edit username
                    System.out.print("Enter new username: ");
                    String newName = scanner.nextLine().trim();
                    myAccount.setUserName(newName);
                    security.setUsername(newName);

                    choice = mainMenu.myAccountMenu(scanner);
                    break;
                case 'C':
                    //change password
                    myAccount.setNewPassword(scanner);

                    choice = mainMenu.myAccountMenu(scanner);
                    break;
                case 'F':
                    //print friends
                    System.out.println(myAccount.printFriends());
                    choice = mainMenu.myAccountMenu(scanner);
                    break;
                case 'L':
                    //print listings
                    System.out.println(myAccount.printCurrentListings());
                    choice = mainMenu.myAccountMenu(scanner);
                    break;
                case 'N':
                    //print notifications
                    System.out.println(notification.getNotifications());
                    choice = mainMenu.myAccountMenu(scanner);
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
    public static char jobBoardMenuActions(Scanner scanner){
        char choice = mainMenu.jobBoardMenu(scanner);
        //options are C A F B Q
        //while loop terminates on B
        while(choice != 'B'){
            switch (choice) {
                case 'C':
                    //contract listing
                    System.out.print("Enter listing ID: ");
                    String cid = scanner.nextLine().trim();
                    String userid = "";
                    for(listing l : jobBoard.getAllListings()){
                        if(l.getID().equals(cid)){
                            userid = l.getAccountName();
                        }
                    }
                    if(userid.equals("")){
                        System.out.println("Sorry - listing ID not found!");
                        continue;
                    }

                    notification.acceptJob(userid, cid);
                    System.out.println("Job contracted!");
                    choice = mainMenu.jobBoardMenu(scanner);
                    break;
                case 'A':
                    //ask question
                    System.out.print("Enter listing ID: ");
                    String qid = scanner.nextLine().trim();
                    String quserid = "";
                    for(listing l : jobBoard.getAllListings()){
                        if(l.getID().equals(qid)){
                            quserid = l.getAccountName();
                        }
                    }
                    if(quserid.equals("")){
                        System.out.println("Sorry - listing ID not found!");
                        continue;
                    }
                    System.out.print("Enter question: ");
                    String q = scanner.nextLine().trim();
                    

                    notification.createQuestion(quserid, qid, q);
                    System.out.println("Question asked!");
                    choice = mainMenu.jobBoardMenu(scanner);

                    break;
                case 'F':
                    //filter jobboard
                    //filter by type
                    
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