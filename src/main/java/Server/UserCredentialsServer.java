package Server;

import Users.*;

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
    private CsvSearch csvSearch = new CsvSearch();

    public UserCredentialsServer() throws IOException {
        //csvSearch.checkForUser()
        placeholderUserMap = new HashMap<UserID, User>();

        while(!CsvSearch.end){

              userString =csvSearch.sendUser();
                //System.out.println(userString[1]);
             if("client".equals(userString[1])){

                tempClient = new Client(new UserID(userString[0]), userString[2]);
                tempClient.setName(userString[3]);
                tempClient.setAddress(userString[4]);
                tempClient.setNumber(userString[5]);
                placeholderUserMap.put(tempClient.getUserID(),tempClient);
            }
            if("contractor".equals(userString[1])){

                tempContractor = new Contractor(new UserID(userString[0]), userString[2]);
                tempContractor.setBusinessName(userString[6]);
                tempContractor.setName(userString[3]);
                tempContractor.setNumber(userString[5]);
                tempContractor.setRating(userString[7]);
                tempContractor.setSubUserType(userString[8]);
                placeholderUserMap.put(tempContractor.getUserID(),tempContractor);
            }
        }/*
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
        */
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
