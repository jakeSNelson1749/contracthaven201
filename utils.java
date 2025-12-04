import java.util.Random;

public class utils {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static String generateID(){
        String id = "";
        Random rand = new Random();
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
        return id;
    }
}
