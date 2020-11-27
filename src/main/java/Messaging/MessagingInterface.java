package Messaging;

import Users.UserID;

import java.util.Vector;

public interface MessagingInterface {
    Vector<ConversationID> getConversationList(UserID currentUser);
    Vector<String> getConversationHistory(ConversationID conversationID);
    void sendMessage(ConversationID conversationID, ChatMessage message);
}
