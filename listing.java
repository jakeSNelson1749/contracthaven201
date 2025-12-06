import java.time.*;
import java.time.format.DateTimeFormatter;

public class listing {
    private String accountName;
    private String title;
    private String description;
    private listingTypes type;
    private int value;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String id;

    public listing(String accountName, String title, String description, listingTypes type, int value, LocalDateTime startTime, LocalDateTime endTime){
        this.accountName = accountName;
        this.description = description;
        this.title = title;
        this.type = type;
        this.value = value;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = utils.generateID();
    }
    
    //accessors / mutators
    public String getAccountName(){return accountName;}
    public String getDesc(){return description;}
    public String getTitle(){return title;}
    public listingTypes getType(){return type;}
    public int getValue(){return value;}
    public LocalDateTime getStartTime(){return startTime;}
    public LocalDateTime getEndTime(){return endTime;}


    public void setAccountName(String newName){this.accountName = newName;}
    public void setDesc(String newDesc){this.description = newDesc;}
    public void setTitle(String newTitle){this.title = newTitle;}
    public void setType(listingTypes newType){this.type = newType;}
    public void setValue(int newValue){this.value = newValue;}
    public void setStartTime(LocalDateTime newStart){this.startTime = newStart;}
    public void setEndTime(LocalDateTime newEnd){this.endTime = newEnd;}


    public boolean isActive() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(startTime) && now.isBefore(endTime);
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm a");
        return "\n\t"+title+"\nOffered by: "+accountName+"\nCategory: "+type+"\nID: "+id+"\n\nDescription: "+description+"\n\nReward: $"+value+"\n\nStart date: "+startTime.format(formatter)+"\nEnd date: "+endTime.format(formatter)+"\n";
    }
    public String toCSV(){
        return accountName + ","+title+","+description+","+type+","+value+","+startTime+","+endTime;
    }


}

