import java.util.ArrayList;
import java.util.Scanner;

public class account {
    private String userName;
    private String passwordHash;
    private ArrayList<String> listingIDs;
    private ArrayList<String> friendList;

    //load from CSV
    public account(String userName, String passHash, ArrayList<String> listingIDs, ArrayList<String> friendList){
        this.userName = userName;
        this.passwordHash = passHash;
        this.listingIDs = new ArrayList<>(listingIDs);
        this.friendList = new ArrayList<>(friendList);
    }
    //create new account
    public account(String userName, String passHash){
        this.userName = userName;
        this.passwordHash = passHash;
        this.listingIDs = new ArrayList<>();
        this.friendList = new ArrayList<>();
    }
    public void loadlistings(){
        for(listing l : jobBoard.getAllListings()){
            if(l.getAccountName().equals(this.userName)){
                listingIDs.add(l.getID());
            }
        }
    }

    //getters n' setters
    public String getUserName(){return userName;}
    public ArrayList<String> getListingIDs(){return listingIDs;}
    public ArrayList<String> getFriendList(){return friendList;}

    public void setUserName(String newUser){
        this.userName = newUser;
        utils.updateCSV("Data/accounts.csv", toCSV(), passwordHash);
    }

    public void setNewPassword(Scanner scanner){
        System.out.print("Enter current password: ");
        String tempPassword = scanner.nextLine().trim();
        String tempPasswordHash = security.hashPassword(tempPassword);

        String[] attempt = {this.userName, tempPasswordHash };
        String[] attemptCheck = security.checkAccount(attempt);

        if (attemptCheck[0].equals("S")) {
            //can edit password
            System.out.print("Enter new password: ");
            String newPass = scanner.nextLine().trim();
            String newPassHash = security.hashPassword(newPass);

            this.passwordHash = newPassHash;
            //must update in CSV
            //uses username to remove from CSV, needs newline
            String newLine = this.toCSV();
            utils.updateCSV("Data/accounts.csv",newLine, this.userName);
            return;
        } else {
            //cannot edit password
            System.out.println("Password Incorrect. Try again.");
        }
    }
    //true if friend added, false if friend cannot be added
    public Boolean addFriend(String newFriend){
        for(String friendName : friendList){
            if(friendName.equals(newFriend)){
                return false;
            }
        }
        friendList.add(newFriend);
        utils.updateCSV("Data/accounts.csv", toCSV(), this.userName);
        return true;
    }
    //true if friend removed, false if not
    public Boolean removeFriend(String friendName){
        if(friendList.isEmpty()){
            return false;
        }
        else{
            for(String a : friendList){
                if(friendName.equals(a)){
                    friendList.remove(a);
                    utils.updateCSV("Data/accounts.csv", toCSV(), this.userName);
                    return true;
                }
            }
        }
        return false;
    }
    //true if ID added, false if ID cannot be added
    public Boolean addListingID(String newID){
        for(String id : listingIDs){
            if(id.equals(newID)){
                //ID already in list
                return false;
            }
        }
        listingIDs.add(newID);
        utils.updateCSV("Data/accounts.csv", toCSV(), this.userName);
        return true;
    }
    //true if ID removed, false if not
    public Boolean removeListingID(String ID){
        if(listingIDs.isEmpty()){
            return false;
        }
        else{
            for(String a : listingIDs){
                if(a.equals(ID)){
                    listingIDs.remove(a);
                    utils.updateCSV("Data/accounts.csv", toCSV(), this.userName);
                    return true;
                }
            }
        }
        return false;
    }
    //implement to print friendlist
    public String printFriends(){
        String friendListString = "\nFriend List:";
        if(friendList.isEmpty()){
            friendListString += "\nFriend list empty, search to add some friends!";
        }
        for(String username : friendList){
            friendListString += "\n"+username;
        }
        return friendListString;
    }
    //implent to print current listings
    public String printCurrentListings(){
        String idListString = "\nCurrent listing:";
        if(listingIDs.isEmpty()){
            idListString += "\nNo current listings, go make some!";
        }
        for(String id : listingIDs){
            idListString += "\n"+id;
        }
        return idListString;
    }
    
    public String toCSV(){
        String ids = "";
        String friends = "";
        if(!listingIDs.isEmpty()){
            for(String i : listingIDs){
                ids += (i.trim() + " ");
            }
        }
        if(!friendList.isEmpty()){
            for(String i : friendList){
                friends += (i.trim() + " ");
            }
        }
        return this.userName+","+this.passwordHash+","+ids+","+friends;
    }
    @Override
    public String toString(){
        return "\nUsername: "+userName+"\n";
    }
    
}
