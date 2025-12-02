import java.io.File;
import java.nio.file.*;
import java.util.Scanner;

public class security {
    public static String login() {
        utils.clearScreen();
        System.out.println("Lets get you logged in.");
        String[] userData = checkFile();
        String[] checkedData = checkAccount(userData);
        if (checkedData[0].equals("S")) {
            System.out.println("Login successful! Welcome back, " + checkedData[1] + ".");
        } else {
            System.out.println("Login failed. Please enter your username and password.");
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Username: ");
                String username = scanner.nextLine().trim();
                System.out.print("Password: ");
                String password = scanner.nextLine().trim();
                String passwordHash = Integer.toString(password.hashCode());
                System.out.println(passwordHash); // For testing purposes only

                String[] attemptData = new String[]{username, passwordHash};
                String[] attemptCheck = checkAccount(attemptData);
                if (attemptCheck[0].equals("S")) {
                    System.out.println("Login successful! Welcome back, " + attemptCheck[1] + ".");
                    return attemptCheck[1];
                } else {
                    System.out.println("Login failed. Please try again.");
                }
            }
        }
        return checkedData[1];
    }

    private static String[] checkFile(){
        File saveDataFile = new File("Data/keys.csv");
        File accountsFiles = new File("Data/accounts.csv");
        if (!saveDataFile.exists()) {
            try {
                saveDataFile.createNewFile();
            } catch (Exception e) {
                System.out.println("An error occurred while creating the save data file.");
            }
        }
        System.out.println("Checking saved login data...");
        try {
            String line = Files.readString(saveDataFile.toPath()).trim();
            if (line.isEmpty()) throw new Exception("Invalid User Data");

            String[] parts = line.split(",", 2);
            if (parts.length < 2) throw new Exception("Invalid User Data");

            String[] userData = new String[]{ parts[0], parts[1] }; // username, password
            return userData;
        } catch (Exception e) {
            System.out.println("An error occurred while reading the save data file.");
            return new String[] {"", ""};
        }
    }

    private static String[] checkAccount(String[] userData){
        Path path = Paths.get("Data/accounts.csv");
        if (!Files.exists(path)) {
            System.out.println("Accounts file not found.");
            return new String[] {"F", ""}; // Failed
        }

        try {
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                String storedUsername = parts[0].trim();
                String storedPasswordHash = parts[1].trim();

                if (storedUsername.equals(userData[0]) && storedPasswordHash.equals(userData[1])) {
                    saveLoginData(storedUsername, storedPasswordHash);
                    return new String[] {"S", storedUsername}; // Success
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred while reading the accounts file.");
            return new String[] {"F", ""}; // Failed
        }
        return new String[] {"F", ""}; // Failed
    }

    private static void saveLoginData(String username, String passwordHash){
        Path path = Paths.get("Data/keys.csv");
        String dataToSave = username + "," + passwordHash;
        try {
            Files.writeString(path, dataToSave);
            System.out.println("Login data saved successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while saving the login data.");
        }
    }

    public static void clearLoginData(){
        Path path = Paths.get("Data/keys.csv");
        try {
            Files.deleteIfExists(path);
            System.out.println("Login data cleared.");
        } catch (Exception e) {
            System.out.println("An error occurred while clearing the login data.");
        }
    }
}


