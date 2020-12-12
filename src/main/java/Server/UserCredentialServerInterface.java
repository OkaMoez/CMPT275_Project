package Server;

import Users.User;
import Users.UserID;

public interface UserCredentialServerInterface {
    enum LoginResult {
        SUCCESS_CLIENT,
        SUCCESS_CONTRACTOR,
        BAD_USERNAME,
        BAD_PASSWORD
    }
    LoginResult tryLoginCredentials(UserID userID, String password);
    User getUserFromCredentials(UserID userID, String password);

    User createNewAccount();
    enum UniqueUserInfoField {
        USERNAME,
        BUSINESS_NAME
    }
    String getNameFromID(UserID userID);
    boolean isFieldUnique(UniqueUserInfoField field, String name);
    boolean isContractor();
    boolean updateClientName();
    boolean updateBusinessName();
    boolean updateProfilePicture();
    boolean updateAddress();
}
