package Profiles;

public abstract class User {
    private UserID mUserID;
    private String mPassword;
    // TODO make a name class with first/last?

    public User(UserID userID, String password) {
        mUserID = userID;
        setPassword(password);
    }

    public User(String userIdString, String password) {
        this(new UserID(userIdString), password);
    }

    UserID getUserID() {
        return mUserID;
    }

    private void setPassword(String password) {
        mPassword = password;
    }

    public boolean verifyPassword(String password) {
        return mPassword.equals(password);
    }
}
