import java.nio.file.*;
import java.security.MessageDigest;
import java.util.Scanner;

public class security {

    private static String validUsername;
    private static final Scanner scanner = new Scanner(System.in);

    public static String getUsername() {
        return validUsername;
    }

    public static void login() {
        utils.clearScreen();
        System.out.println("Lets get you logged in.");

        while (true) {
            String[] saved = checkFile();
            String[] checked = checkAccount(saved);

            if (checked[0].equals("S")) {
                System.out.println("Login successful! Welcome back, " + checked[1] + ".\n");
                return;
            }

            System.out.println("Login failed. No valid saved login found.");
            System.out.println("Would you like to create a new account? (Y/N)");

            String choice = scanner.nextLine().trim().toUpperCase();

            if (choice.equals("Y")) {
                newAccount();
                continue; // restart login attempt
            }

            if (choice.equals("N")) {
                System.out.print("Username: ");
                String username = scanner.nextLine().trim();

                System.out.print("Password: ");
                String password = scanner.nextLine().trim();
                String passwordHash = hashPassword(password);

                String[] attempt = { username, passwordHash };
                String[] attemptCheck = checkAccount(attempt);

                if (attemptCheck[0].equals("S")) {
                    System.out.println("Login successful! Welcome back, " + attemptCheck[1] + ".\n");
                    return;
                } else {
                    System.out.println("Login failed. Try again.");
                }
            }

            System.out.println("Invalid choice.");
        }
    }

    private static String[] checkFile() {
        try {
            Files.createDirectories(Paths.get("Data")); // ensure folder exists
        } catch (Exception ignored) {}

        Path file = Paths.get("Data/keys.csv");

        if (!Files.exists(file)) return new String[]{"", ""};

        try {
            String content = Files.readString(file).trim();
            if (content.isEmpty()) return new String[]{"", ""};

            String[] parts = content.split(",", 2);
            if (parts.length < 2) return new String[]{"", ""};

            return new String[] { parts[0], parts[1] };
        } catch (Exception e) {
            return new String[]{"", ""};
        }
    }

    private static String[] checkAccount(String[] userData) {
        Path path = Paths.get("Data/accounts.csv");
        if (!Files.exists(path)) return new String[]{"F", ""};

        try {
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(",", 2);
                if (parts.length < 2) continue;

                if (parts[0].trim().equals(userData[0]) &&
                    parts[1].trim().equals(userData[1])) {

                    saveLoginData(parts[0].trim(), parts[1].trim());
                    return new String[]{"S", parts[0].trim()};
                }
            }
        } catch (Exception e) {
            return new String[]{"F", ""};
        }

        return new String[]{"F", ""};
    }

    private static void saveLoginData(String username, String passwordHash) {
        validUsername = username;
        Path file = Paths.get("Data/keys.csv");

        try {
            Files.writeString(file, username + "," + passwordHash);
        } catch (Exception ignored) {}
    }

    public static void clearLoginData(){
        validUsername = null;
        try {
            Files.deleteIfExists(Paths.get("Data/keys.csv"));
        } catch (Exception ignored) {}
    }

    public static void newAccount() {
        utils.clearScreen();

        System.out.println("Create a new account.");
        System.out.print("Choose a username: ");
        String username = scanner.nextLine().trim();

        // Prevent duplicates
        if (usernameExists(username)) {
            System.out.println("Username already exists. Try another.");
            return;
        }

        System.out.print("Choose a password: ");
        String password = scanner.nextLine().trim();
        String passwordHash = hashPassword(password);

        Path path = Paths.get("Data/accounts.csv");
        String data = username + "," + passwordHash + "\n";

        try {
            Files.writeString(path, data, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            System.out.println("Account created! You can now log in.");
        } catch (Exception ignored) {}
    }

    private static boolean usernameExists(String username) {
        Path path = Paths.get("Data/accounts.csv");
        if (!Files.exists(path)) return false;

        try {
            for (String line : Files.readAllLines(path)) {
                String[] parts = line.split(",", 2);
                if (parts.length >= 1 && parts[0].trim().equals(username)) {
                    return true;
                }
            }
        } catch (Exception ignored) {}

        return false;
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));

            // convert bytes → hex
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                String hexByte = Integer.toHexString(0xff & b);
                if (hexByte.length() == 1) hex.append('0');
                hex.append(hexByte);
            }
            return hex.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
