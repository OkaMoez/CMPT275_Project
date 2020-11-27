package Users;

public class Client extends User{
    private String mName;

    public Client(UserID userID, String password) {
        super(userID, password);
        // initialise Name by querying database with userid and password
        // mName = name;
    }

    public String getName() {
        return mName;
    }

    public String getUserType() { return "client";}
}
