package Users.Csv;

import Messaging.ChatMessage;
import Messaging.ConversationID;
import Users.UserID;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.Vector;

public interface CsvMessagingInterface {
    Vector<ConversationID> getUsersConversations(UserID currentUser) throws IOException, CsvException;
    void createNewConversation(ConversationID newConversation) throws IOException, CsvException;
    Vector<ChatMessage> getConversationHistory(ConversationID conversationID) throws IOException, CsvException;
    void addMessageToConversation(ConversationID conversationID, ChatMessage message) throws IOException, CsvException;
}
