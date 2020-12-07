package Users;

public class Contractor extends User {
    private String contactName;
    private String businessName;
    private String number;
    private String rating;
    private String subUserType;

    public Contractor(UserID userID, String password) {
        super(userID, password);
        // initialise business name by querying database with userid and password
        // mBusinessName = businessName;
    }

    public String getBusinessName() {
        return businessName;
    }
    public String getUserType() { return "contractor";}
    public String getName(){ return contactName;}
    public String getNumber(){return number;}
    public String getAddress(){return null;}
    public String getRating(){return rating;}
    public String getSubUserType(){return subUserType;}

    public void setBusinessName(String newBusinessName){ this.businessName = newBusinessName;}
    public void setName(String newContactName){this.contactName = newContactName;}
    public void setNumber(String newNumber){ this.number = newNumber;}
    public void setAddress(String newAddress){ }
    public void setRating(String newRating){this.rating = newRating;}
    public void setSubUserType(String newSubUserType){this.subUserType = newSubUserType;}
}
