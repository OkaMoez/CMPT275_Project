package Users;

public abstract class User {
    private UserID userID;
    private String password;
    // TODO make a name class with first/last?

    public User(UserID userID, String password) {
        this.userID = userID;
        setPassword(password);
    }

    public User(String userIdString, String password) {
        this(new UserID(userIdString), password);
    }

    // Get userID as a string
    public UserID getUserID() {
        return userID;
    }

    abstract public String getUserType();
    abstract public String getName();
    abstract public String getAddress();
    abstract public String getNumber();
    abstract public String getBusinessName();
    private void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
