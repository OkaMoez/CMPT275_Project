package Profiles;

public class Client extends User{
    private String mName;

    public Client(UserID userID, String password, String name) {
        super(userID, password);
        mName = name;
    }

    public String getName() {
        return mName;
    }
}
