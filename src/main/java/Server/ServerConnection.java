package Server;

import Booking.BookingDatabase;
import Messaging.ChatMessage;
import Messaging.ConversationID;
import Users.User;
import Users.UserID;

import java.util.Vector;

public class ServerConnection implements UserCredentialServerInterface, MessagingServerInterface {
    private UserCredentialsServer userCredentialsServer;
    private MessagingServer messagingServer;
    private BookingDatabase bookingDatabase;

    public ServerConnection() {
        userCredentialsServer = new UserCredentialsServer();
        messagingServer = new MessagingServer();
        bookingDatabase = new BookingDatabase();
    }

    @Override
    public Vector<ConversationID> getConversationList(UserID currentUser) {
        return messagingServer.getConversationList(currentUser);
    }

    @Override
    public Vector<ChatMessage> getConversationHistory(ConversationID conversationID) {
        return messagingServer.getConversationHistory(conversationID);
    }

    @Override
    public void sendMessage(ConversationID conversationID, ChatMessage message) {
        messagingServer.sendMessage(conversationID, message);
    }

    @Override
    public void createNewConversation(ConversationID newConversation) {
        messagingServer.createNewConversation(newConversation);
    }

    @Override
    public LoginResult tryLoginCredentials(UserID userID, String password) {
        return userCredentialsServer.tryLoginCredentials(userID, password);
    }

    @Override
    public User getUserFromCredentials(UserID userID, String password) {
        return userCredentialsServer.getUserFromCredentials(userID, password);
    }

    @Override
    public User createNewAccount() {
        return userCredentialsServer.createNewAccount();
    }

    @Override
    public String getNameFromID(UserID userID) {
        return userCredentialsServer.getNameFromID(userID);
    }

    @Override
    public boolean isFieldUnique(UniqueUserInfoField field, String name) {
        return userCredentialsServer.isFieldUnique(field, name);
    }

    @Override
    public boolean updateClientName() {
        return userCredentialsServer.updateClientName();
    }

    @Override
    public boolean updateBusinessName() {
        return userCredentialsServer.updateBusinessName();
    }

    @Override
    public boolean updateProfilePicture() {
        return userCredentialsServer.updateProfilePicture();
    }

    @Override
    public boolean updateAddress() {
        return userCredentialsServer.updateAddress();
    }
    @Override
    public boolean isContractor() {
        return userCredentialsServer.isContractor();
    }
}
