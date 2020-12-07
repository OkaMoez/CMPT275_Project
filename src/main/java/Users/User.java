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
    abstract public String getRating();
    abstract public String getSubUserType();
    public String getPassword(){return password;}

    abstract public void setName(String newName);
    abstract public void setAddress(String newAddress);
    abstract public void setNumber(String newNumber);
    abstract public void setBusinessName(String newBusinessName);
    abstract public void setRating(String newRating);
    abstract public void setSubUserType(String newSubUSerType);

    private void setPassword(String password) {
        this.password = password;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
