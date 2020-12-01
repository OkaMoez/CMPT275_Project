package Users;

import java.util.Objects;

public class UserID {
    private String userID;

    public UserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return userID;
    }

    // Allow easy comparisons of user ids
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return this.userID.equals(userID.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
}
