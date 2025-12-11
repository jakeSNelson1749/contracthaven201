import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class utils {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    //always returns a unique ID
    public static String generateID(){
        Boolean isValid = false;
        String id = "";
        Random rand = new Random();
        while(!isValid){
            for(int i = 1;i<=6;i++){
                int randLetterInt = rand.nextInt(122-97+1) + 97;
                String randLetter = String.valueOf((char)(randLetterInt));
        
                int capsBool = rand.nextInt(2); //generates 0 or 1
                if(capsBool == 1){
                    randLetter = randLetter.toUpperCase();
                    id += randLetter;
                }
                else{
                    id += randLetter;
                }
            }
            if(utils.isIDValid(id)){
                isValid = true;
            }
        }
        return id;
    }
    //PRE: take String input
    //POST: return int value, -1 if invalid
    public static int convertToInteger(String input){
        input = input.trim();
        int value = -1;
        try {
            value = Integer.parseInt(input);
        } catch (Exception e) {
            //non integer value passed as input
        }
        return value;
    }
    //returns true if ID is unique, false if it has been generated already
    public static Boolean isIDValid(String ID){
        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/fakePostData.csv"));
            String line = br.readLine();
            //parse for ID, token 7
            while((line = br.readLine())!= null){
                String[] tokens = line.split(",");
                if(tokens.length == 8){
                    if(tokens[7].equals(ID)){
                        //ID not unique
                        br.close();
                        return false;
                    }
                }
                else{
                    //missing ID entirely
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("System error - file not found");
        }
        //ID unique 
        return true;
    }

    public static String generateUUID(){
        return java.util.UUID.randomUUID().toString();
    }

    public static String timestamp(){
        return LocalDateTime.now().toString();
    }

    public static String getTime(String raw){
        LocalDateTime time = LocalDateTime.parse(raw);

        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    public static void updateCSV(String fileName, String newLine, String removeID){
        try {
            File temp = new File(fileName + ".tmp");
            PrintWriter writer = new PrintWriter(temp);
            BufferedReader br = new BufferedReader(new FileReader("Data/accounts.csv"));
            String line;
            while((line = br.readLine()) != null){
                if(!line.contains(removeID)){
                    //skip line
                    writer.println(line);
                }
            }
            writer.println(newLine);
            writer.close();
            br.close(); //close bufferedreader

            // Replace original file
            new File(fileName).delete();
            temp.renameTo(new File(fileName));
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
    public static void removeFromCSV(String fileName, String removeID){
        try {
            File temp = new File(fileName + ".tmp");
            PrintWriter writer = new PrintWriter(temp);
            BufferedReader br = new BufferedReader(new FileReader("Data/accounts.csv"));
            String line = br.readLine(); //remove header
            while((line = br.readLine()) != null){
                if(!line.contains(removeID)){
                    //skip line
                    writer.println(line);
                }
            }
            writer.close();
            br.close(); //close bufferedreader

            // Replace original file
            new File(fileName).delete();
            temp.renameTo(new File(fileName));
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
    public static ArrayList<account> loadAccountList(){
        ArrayList<account> accountList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/accounts.csv"));
            String line = br.readLine(); //remove header
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                //0 is username, 1 is password, 2 is listingIDs, 3 is friendNames
                ArrayList<String> friends;
                ArrayList<String> ids;
                if(!tokens[2].equals("")){
                    ids = new ArrayList<>(Arrays.asList(tokens[2].split(" ")));
                }
                else{
                    ids = new ArrayList<>();
                }
                if(!tokens[3].equals("")){
                    friends = new ArrayList<>(Arrays.asList(tokens[3].split(" ")));
                }
                else{
                    friends = new ArrayList<>();
                }
                accountList.add(new account(tokens[0].trim(), tokens[1].trim(),ids, friends));
            }
            br.close(); //close bufferedreader
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
        return accountList;
    }
}
