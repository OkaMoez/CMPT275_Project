package Server;

import Messaging.ChatMessage;
import Messaging.ConversationID;
import Users.Csv.CsvMessagingInterface;
import Users.Csv.CsvSearch;
import Users.UserID;

import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class MessagingServer implements MessagingServerInterface {
    private UserID placeholderUser1;
    private UserID placeholderUser2;
    private UserID placeholderUser3;
    private UserID placeholderUser4;
    private UserID placeholderUser5;
    private UserID placeholderUser6;
    private UserID placeholderUser7;

    private HashMap<ConversationID, Vector<String>> placeholderChats;

    private CsvMessagingInterface csvMessageInterface;

    public MessagingServer() {
        try {
            csvMessageInterface = new CsvSearch();
        }
        catch (IOException e) {
            System.out.println("General I/O exception: " + e.getMessage());
            e.printStackTrace();
        }
        placeholderChats = new HashMap<ConversationID, Vector<String>>();
    }

    @Override
    public Vector<ConversationID> getConversationList(UserID currentUser) {
        Vector<ConversationID> results = new Vector<ConversationID>();
        try {
            results = csvMessageInterface.getUsersConversations(currentUser);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public Vector<ChatMessage> getConversationHistory(ConversationID conversationID) {
        Vector<ChatMessage> results = new Vector<ChatMessage>();
        try {
            results = csvMessageInterface.getConversationHistory(conversationID);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void sendMessage(ConversationID conversationID, ChatMessage message) {
        try {
            csvMessageInterface.addMessageToConversation(conversationID, message);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
