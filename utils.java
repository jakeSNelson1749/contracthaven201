import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

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
}
