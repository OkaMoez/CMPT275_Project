package Users;

public class Client extends User{
    private String name;
    private String address;
    private String number;
    public Client(UserID userID, String password) {
        super(userID, password);
        // initialise Name by querying database with userid and password
        // mName = name;
    }

    public String getName() {
        return name;
    }
    public String getUserType() { return "client";}
    public String getAddress(){return address;}
    public String getNumber(){return number;}
    public String getBusinessName(){return null;}

    public void setName(String newName){this.name= newName;}
    public void setAddress(String newAddress){this.address= newAddress;}
    public void setNumber(String newNumber){this.number= newNumber;}

}
