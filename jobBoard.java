import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class jobBoard {

    private static ArrayList<listing> allListings = new ArrayList<>(); //static instance var for changeable jobboard 
    static{
        loadListings("Data/fakePostData.csv");
    }
    //to prevent jobBoard objects
    private jobBoard(){}
    
    //PRE: name of file containing all stored listing information
    //POST: returns an arraylist of all listing objects
    private static void loadListings(String inputPath){
        //ArrayList<listing> allListings = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputPath));
            String line = br.readLine(); //remove header
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                if(tokens.length != 7){
                    //invalid data
                    continue;
                }

                //load listings arraylist from csv
                listingTypes tempType = listingTypes.valueOf(tokens[3].trim());
                int tempReward = Integer.valueOf(tokens[4].trim());
                String tempDesc = tokens[2].trim().replace("\\n", "\n");
                LocalDateTime tempStart = LocalDateTime.parse(tokens[5].trim());
                LocalDateTime tempEnd = LocalDateTime.parse(tokens[6].trim());
                allListings.add(new listing(tokens[0].trim(), tokens[1].trim(), tempDesc, tempType, tempReward, tempStart, tempEnd));

            }
            br.close(); //close bufferedreader
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        //return allListings;
    }
    public static ArrayList<listing> getAllListings(){return allListings;}

    public static void printJobBoard(){
        ArrayList<listing> allListings = getAllListings();
        utils.clearScreen();
        System.out.println("Current Job Board:\n");
        for (listing l : allListings) {
            System.out.println(l);
            
        }
    }

    //function to add new listing
    //must add to static arraylist and csv file
    public static void createListing(String accountName, Scanner input) throws IOException{
        //need from user: title, desc, type, reward, when it goes active, when it expires
        //username from main file


        PrintWriter csvWriter = new PrintWriter(new FileWriter("Data/fakePostData.csv", true));
        //Scanner input = new Scanner(System.in);
        System.out.println("Lets create a new listing!\n");
        
        //getting title
        System.out.print("Enter listing title: ");
        String title = input.nextLine();
        
        //getting type
        System.out.println("\nLABOR, RENOVATION, CLEANING, TECH, ART\nADVISING, EDUCATION, PET, HEALTH, OTHER");
        System.out.print("Enter listing type: ");
        String strType = input.nextLine().toUpperCase();
        listingTypes type;
        if(strType.equals("LABOR") || strType.equals("RENOVATION") || strType.equals("CLEANING") || strType.equals("TECH") || strType.equals("ART")
        || strType.equals("ADVISING")|| strType.equals("EDUCATION") || strType.equals("PET")||strType.equals("HEALTH")){
            type = listingTypes.valueOf(strType);
        }
        else{
            System.out.println("Listing type entered as OTHER");
            type = listingTypes.OTHER;
        }

        //getting desc
        System.out.println("\nGive us a short description:");
        String desc = input.nextLine();

        //getting positive, integer reward
        System.out.print("\nHow much will this job pay? $");
        String tempValue = input.nextLine();
        int value = utils.convertToInteger(tempValue);
        while(value <= 0){
           System.out.print("Please enter a positive whole number: ");
            tempValue = input.nextLine();
            value = utils.convertToInteger(tempValue);
        }
        //get start and end times (and validate that they are dates)
        //for now they will be automatic
        LocalDateTime startTime = LocalDateTime.parse("2025-12-01T00:00");
        LocalDateTime endTime = LocalDateTime.parse("2026-01-01T00:00");

        //create listing object
        listing temp = new listing(accountName, title, desc, type, value, startTime, endTime);
        allListings.add(temp);
        csvWriter.println(temp.toCSV());
        System.out.println("\nListing added! Preview:");
        System.out.println(temp);


        //input.close();
        csvWriter.close();

    }
}
