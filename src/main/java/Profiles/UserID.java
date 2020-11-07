package Profiles;

import java.util.Objects;

public class UserID {
    private String mUserID;

    public UserID(String userID) {
        mUserID = userID;
    }

    // Allow easy comparisons of user ids
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return mUserID.equals(userID.mUserID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mUserID);
    }
}
