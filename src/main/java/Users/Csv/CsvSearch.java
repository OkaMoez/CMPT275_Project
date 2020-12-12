package Users.Csv;

import Messaging.ChatMessage;
import Messaging.ConversationID;
import Users.Client;
import Users.Contractor;
import Users.User;
import Users.UserID;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class CsvSearch implements CsvMessagingInterface {
    // File locations
    private static final String userFileLocation = "src/main/resources/data/users.csv";
    private static final String messageIndexDirectoryLocation = "src/main/resources/data/messages/index";
    private static final String messageDirectoryLocation = "src/main/resources/data/messages";

    public CsvSearch() throws IOException {}

    public void parseUsers(HashMap<UserID, User> userHashMap) throws IOException {
        String[] throwAway = new String[9];
        File userFile = new File(userFileLocation);
        if (!userFile.exists()) {
            return;
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(userFile));
        String line = "";
        String comma = ",";
        while ((line = bufferedReader.readLine()) != null) {
            String[] user = line.split(comma);
            if("client".equals(user[1])){

                Client tempClient = new Client(new UserID(user[0]), user[2]);
                tempClient.setName(user[3]);
                tempClient.setAddress(user[4]);
                tempClient.setNumber(user[5]);
                userHashMap.put(tempClient.getUserID(),tempClient);
            }
            else if("contractor".equals(user[1])){

                Contractor tempContractor = new Contractor(new UserID(user[0]), user[2]);
                tempContractor.setBusinessName(user[6]);
                tempContractor.setName(user[3]);
                tempContractor.setNumber(user[5]);
                tempContractor.setRating(user[7]);
                tempContractor.setSubUserType(user[8]);
                userHashMap.put(tempContractor.getUserID(),tempContractor);
            }
        }
        bufferedReader.close();
        return;
    }

    public void addUser(String[] userData) throws IOException {
        File userFile = new File(userFileLocation);
        if (!userFile.exists()) {
            return;
        }

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(userFile, true));
        bufferedWriter.append("\n");
        for (int i=0; i<9;i++) {
            bufferedWriter.append(userData[i]);
            if(i<8){
            bufferedWriter.append(",");}
        }
        bufferedWriter.close();
    }

    private void removeLastLineOfFile(String fileLocation) throws IOException, CsvException {
        // Atm the cvs writer leaves a blank last line that is incompatible with our current parsing
        RandomAccessFile file = new RandomAccessFile(fileLocation,"rw");
        long length = file.length();
        file.setLength(length-1);
        file.close();
    }

    public void editUser(String[] userData) throws IOException, CsvException {
        File userFile = new File(userFileLocation);
        if (!userFile.exists()) {
            return;
        }

        CSVReader csvReader = new CSVReader(new FileReader(userFile));
        List<String[]> csvBody = csvReader.readAll();
        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);

            if(strArray[0].equalsIgnoreCase(userData[0])){

                csvBody.get(i)[3] = userData[3];
                csvBody.get(i)[4] = userData[4];
                csvBody.get(i)[5] = userData[5];
                csvBody.get(i)[6] = userData[6];
                csvBody.get(i)[7] = userData[7];
                csvBody.get(i)[8] = userData[8];
                i = csvBody.size();
           }

        }
        csvReader.close();

        // Write to CSV file which is open
        CSVWriter csvWriter = new CSVWriter(new FileWriter(userFileLocation), ',',
                CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
        csvWriter.writeAll(csvBody);
        csvWriter.flush();
        csvWriter.close();

        removeLastLineOfFile(userFileLocation);
    }


    // Message save/load scheme
    // Each user has a message index file that lists all other uses they have existing conversations with
    // Each conversation has its own file
    // Conversation files have 1 message per line
    // Format: sender name,time stamp,message
    private String getUserMessageIndexLocation(UserID currentUser){
        return messageIndexDirectoryLocation + "/" + currentUser.toString() + ".csv";
    }

    private String getUserMessageIndexLocation(ConversationID conversationID){
        return getUserMessageIndexLocation(conversationID.getOwner());
    }

    private String getConversationHistoryLocation(ConversationID conversationID){
        return messageDirectoryLocation + "/history_"
                + conversationID.getFirstAlpha().toString() + "-"
                + conversationID.getSecondAlpha().toString() + ".csv";
    }

    @Override
    public Vector<ConversationID> getUsersConversations(UserID currentUser) throws IOException, CsvException {
        Vector<ConversationID> results = new Vector<ConversationID>();

        File messageIndexFile = new File(getUserMessageIndexLocation(currentUser));
        if (!messageIndexFile.exists()) {
            // If there is no file, assume there are no conversations
            return results;
        }

        CSVReader csvReader = new CSVReader(new FileReader(messageIndexFile));
        List<String[]> csvBody = csvReader.readAll();
        csvReader.close();

        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);
            results.add(new ConversationID(currentUser, new UserID(strArray[0])));
        }

        return results;
    }

    @Override
    public void createNewConversation(ConversationID newConversation) throws IOException, CsvException {
        File messageIndexFile = new File(getUserMessageIndexLocation(newConversation));
        if (!messageIndexFile.exists()) {
            // If there is no file, make one
            messageIndexFile.getParentFile().mkdirs();
            messageIndexFile.createNewFile();
        }

        CSVReader csvReader = new CSVReader(new FileReader(messageIndexFile));
        List<String[]> csvBody = csvReader.readAll();
        csvReader.close();

        boolean foundUserID = false;
        String otherUserString = newConversation.getOtherUserID().toString();
        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);
            if(strArray[0].equalsIgnoreCase(otherUserString)){
                // We shouldn't be calling this if we know there is an existing conversation
                assert(true);
                foundUserID = true;
                break;
            }
        }
        if (!foundUserID) {
            String[] newRecipient = { otherUserString };
            csvBody.add(newRecipient);

            CSVWriter csvWriter = new CSVWriter(new FileWriter(messageIndexFile), ',',
                    CSVWriter.NO_QUOTE_CHARACTER,CSVWriter.NO_ESCAPE_CHARACTER,CSVWriter.DEFAULT_LINE_END);
            csvWriter.writeAll(csvBody);
            csvWriter.flush();
            csvWriter.close();
            removeLastLineOfFile(getUserMessageIndexLocation(newConversation));

            File conversationHistoryFile = new File(getConversationHistoryLocation(newConversation));
            conversationHistoryFile.createNewFile();
        }

        return;
    }

    @Override
    public Vector<ChatMessage> getConversationHistory(ConversationID conversationID) throws IOException, CsvException {
        Vector<ChatMessage> results = new Vector<ChatMessage>();

        File messageIndexFile = new File(getConversationHistoryLocation(conversationID));
        if (!messageIndexFile.exists()) {
            // If there is no file, assume there is no conversation history
            return results;
        }

        CSVReader csvReader = new CSVReader(new FileReader(messageIndexFile));
        List<String[]> csvBody = csvReader.readAll();
        csvReader.close();

        for(int i=0; i<csvBody.size(); i++){
            String[] strArray = csvBody.get(i);
            results.add(new ChatMessage(strArray[0], strArray[1], strArray[2]));
        }

        return results;
    }

    @Override
    public void addMessageToConversation(ConversationID conversationID, ChatMessage message) throws IOException, CsvException {
        File conversationHistoryFile = new File(getConversationHistoryLocation(conversationID));
        if (!conversationHistoryFile.exists()) {
            // If there is no file, make one
            conversationHistoryFile.getParentFile().mkdirs();
            conversationHistoryFile.createNewFile();
        }

        String[] messageData = { message.getSender().toString(), message.getTimestamp().toString(), message.getMessage() };
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(conversationHistoryFile, true));
        for (int i=0; i<3;i++) {
            bufferedWriter.append(messageData[i]);
            if (i<2) {
                bufferedWriter.append(",");
            }
        }
        bufferedWriter.close();
    }
}