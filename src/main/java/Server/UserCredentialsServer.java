package Server;

import Users.Client;
import Users.Contractor;
import Users.User;
import Users.UserID;

import java.util.HashMap;

public class UserCredentialsServer implements UserCredentialServerInterface {
    private Client placeholderClient1;
    private Client placeholderClient2;
    private Client placeholderClient3;
    private Contractor placeholderContractor1;
    private Contractor placeholderContractor2;
    private Contractor placeholderContractor3;
    private HashMap<UserID, User> placeholderUserMap;

    public UserCredentialsServer() {
        placeholderClient1 = new Client(new UserID("client"), "pass");
        placeholderClient2 = new Client(new UserID("Brigham"), "pass");
        placeholderClient3 = new Client(new UserID("Amon"), "pass");
        placeholderContractor1 = new Contractor(new UserID("contractor"), "pass");
        placeholderContractor2 = new Contractor(new UserID("Uchechi"), "pass");
        placeholderContractor3 = new Contractor(new UserID("bossMan123"), "pass");

        placeholderUserMap = new HashMap<UserID, User>();
        placeholderUserMap.put(placeholderClient1.getUserID(), placeholderClient1);
        placeholderUserMap.put(placeholderClient2.getUserID(), placeholderClient2);
        placeholderUserMap.put(placeholderClient3.getUserID(), placeholderClient3);
        placeholderUserMap.put(placeholderContractor1.getUserID(), placeholderContractor1);
        placeholderUserMap.put(placeholderContractor2.getUserID(), placeholderContractor2);
        placeholderUserMap.put(placeholderContractor3.getUserID(), placeholderContractor3);
    }

    @Override
    public LoginResult tryLoginCredentials(UserID userID, String password) {
        if (!placeholderUserMap.containsKey(userID)) {
            return LoginResult.BAD_USERNAME;
        }
        User possibleUser = placeholderUserMap.get(userID);
        if (!possibleUser.verifyPassword(password)) {
            return LoginResult.BAD_PASSWORD;
        }
        return LoginResult.SUCCESS;
    }

    @Override
    public User getUserFromCredentials(UserID userID, String password) {
        if (!placeholderUserMap.containsKey(userID)) {
            return null;
        }
        User possibleUser = placeholderUserMap.get(userID);
        if (!possibleUser.verifyPassword(password)) {
            return null;
        }
        return possibleUser;
    }

    @Override
    public User createNewAccount() {
        return null;
    }

    @Override
    public boolean isFieldUnique(UniqueUserInfoField field, String name) {
        return false;
    }

    @Override
    public boolean updateClientName() {
        return false;
    }

    @Override
    public boolean updateBusinessName() {
        return false;
    }

    @Override
    public boolean updateProfilePicture() {
        return false;
    }

    @Override
    public boolean updateAddress() {
        return false;
    }
}
