package Server;

import Messaging.ChatMessage;
import Messaging.ConversationID;
import Users.UserID;

import java.util.Vector;

public interface MessagingServerInterface {
    Vector<ConversationID> getConversationList(UserID currentUser);
    Vector<ChatMessage> getConversationHistory(ConversationID conversationID);
    void sendMessage(ConversationID conversationID, ChatMessage message);
}
