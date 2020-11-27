package Users;

public class Contractor extends User {
    private String mBusinessName;

    public Contractor(UserID userID, String password) {
        super(userID, password);
        // initialise business name by querying database with userid and password
        // mBusinessName = businessName;
    }

    public String getBusinessName() {
        return mBusinessName;
    }

    public String getUserType() { return "contractor";}
}
