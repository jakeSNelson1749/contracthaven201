
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class notification {

    //checks posts, and if posts have notifications than it shows them
    public static String getNotifications(){
        String notifs = "";
        List<String> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> rawNotifs = new ArrayList<>();
        Path posts = Path.of("Data/fakePostData.csv");
        try {
            List<String> lines = Files.readAllLines(posts);
            for (String line : lines){
                String[] tokens = line.split(",");
                if(tokens[0].equals(security.getUsername().trim())){
                    ids.add(tokens[7].strip());
                    names.add(tokens[1].strip());
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read posts! " + e.getMessage());
        }
        Path notifPath = Path.of("Data/notifications.csv");
        try {
            List<String> lines = Files.readAllLines(notifPath);
            for(String id : ids){
                for(String line: lines){
                    String[] tokens2 = line.split(",");
                    if (id.equals(tokens2[5].strip())){
                        rawNotifs.add(line);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read notifications! " + e.getMessage());
        }
        int x = 0;
        for (String line : rawNotifs) {
            switch (line.split(",")[3].strip()){
                case ("accept"):
                    notifs += utils.getTime(line.split(",")[4].strip()) + " " + line.split(",")[1].strip() + " has accepted " + ids.get(x) + "!\n";
                    break;
                case ("question"):
                    notifs += utils.getTime(line.split(",")[4].strip()) + " " + line.split(",")[1].strip() + " has asked a question about " + ids.get(x) + ": " + line.split(",")[2].strip() + "\n";
                    break;
            }
            x++;
        }
        return notifs;
    }

    public static void createQuestion(String postAccountname, String jobID, String message){
        String username = security.getUsername();
        String action = "question";
        String uuid = utils.generateUUID();
        writeNotification(postAccountname, username, action, uuid, jobID, message);
    }

    public static void acceptJob(String postAccountname, String jobID){
        String username = security.getUsername();
        String action = "accept";
        String uuid = utils.generateUUID();
        updateJobStatus(jobID);
        writeNotification(postAccountname, username, action, uuid, jobID, "Job accepted by user.");
        
    }

    private static void updateJobStatus(String jobID) {
        Path path = Path.of("Data/fakePostData.csv");

        try {
            List<String> lines = Files.readAllLines(path);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] parts = line.split(",");

                if (parts.length > 7 && parts[7].strip().equals(jobID)) {

                    // Example update: set column 8 (index 8) to current time
                    String newTimestamp = LocalDateTime.now()
                            .truncatedTo(ChronoUnit.MINUTES)
                            .toString();

                    if (parts.length > 8) {
                        parts[8] = newTimestamp;
                    }

                    // Reconstruct the updated line
                    lines.set(i, String.join(",", parts));
                    break;  // Stop after updating the one line
                }
            }

            Files.write(path, lines);

        } catch (Exception e) {
            System.out.println("Failed to update job status! " + e.getMessage());
        }
    }


    private static void writeNotification(String username1, String username2, String action, String uuid, String jobID, String message){
        // Create a new line with the notification details
        Path path = Path.of("Data/notifications.csv");
        String line = username1+","+username2+ "," + message + "," + action + "," + utils.timestamp() + "," + jobID + "," + uuid + "\n";
        try {
            Files.writeString(path, line, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (Exception e) {
            System.out.println("Failed to write notification! " + e.getMessage());
        }
    }

}