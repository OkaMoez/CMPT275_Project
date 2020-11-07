package Profiles;

public class Contractor extends User {
    private String mBusinessName;

    public Contractor(UserID userID, String password, String businessName) {
        super(userID, password);
        mBusinessName = businessName;
    }

    public String getBusinessName() {
        return mBusinessName;
    }
}
