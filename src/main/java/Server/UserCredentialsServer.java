package Server;

import Users.*;
import Users.Csv.CsvSearch;

import java.io.IOException;
import java.util.HashMap;

public class UserCredentialsServer implements UserCredentialServerInterface {
    public static User currentUser;
    private Client tempClient;
    private Contractor tempContractor;
    private String[] userString = new String[9];
    private Client placeholderClient1;
    private Client placeholderClient2;
    private Client placeholderClient3;
    private Contractor placeholderContractor1;
    private Contractor placeholderContractor2;
    private Contractor placeholderContractor3;
    public static HashMap<UserID, User> placeholderUserMap;
    private CsvSearch csvSearch;

    public UserCredentialsServer() {
        try {
            csvSearch = new CsvSearch();
            placeholderUserMap = new HashMap<UserID, User>();
            csvSearch.parseUsers(placeholderUserMap);
        }
        catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
        }
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
        String type = possibleUser.getUserType();

        if("client".equals(type)){
            currentUser = possibleUser;
            //System.out.println(currentUser.getName());
            return LoginResult.SUCCESS_CLIENT;
        }
        if("contractor".equals(type)){
            currentUser = possibleUser;

            return LoginResult.SUCCESS_CONTRACTOR;
        }
        return LoginResult.BAD_PASSWORD;
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
    @Override
    public boolean isContractor() {
        return false;
    }
}
