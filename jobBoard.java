import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.*;

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

                //load listings arraylist from csv
                //doesn't need to check for bad data, will only store accurate posts
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
}
