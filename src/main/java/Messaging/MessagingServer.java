package Messaging;

import Users.UserID;

import java.util.Map;
import java.util.Vector;

public class MessagingServer implements MessagingInterface{
    static UserID placeholderUser1 = new UserID("user");
    static UserID placeholderUser2 = new UserID("Amon");
    static UserID placeholderUser3 = new UserID("Brigham");
    static UserID placeholderUser4 = new UserID("Uchechi");
    static UserID placeholderUser5 = new UserID("Bob Ross");
    static UserID placeholderUser6 = new UserID("Bob the Builder");
    static UserID placeholderUser7 = new UserID("Gordon Ramsey");

    static Map<ConversationID, Vector<String>> placeholderChats;


    public MessagingServer() {
    }

    @Override
    public Vector<ConversationID> getConversationList(UserID currentUser) {
        // placeholder
        Vector<ConversationID> placeholderConversationIDs = new Vector<ConversationID>();
        placeholderConversationIDs.add(new ConversationID(placeholderUser1, placeholderUser5));
        placeholderConversationIDs.add(new ConversationID(placeholderUser1, placeholderUser6));
        placeholderConversationIDs.add(new ConversationID(placeholderUser1, placeholderUser7));
        return placeholderConversationIDs;
    }

    @Override
    public Vector<String> getConversationHistory(ConversationID conversationID) {
        if (placeholderChats.containsKey(conversationID)) {
            return placeholderChats.get(conversationID);
        }

        Vector<String> placeholderConversation = new Vector<String>();
        placeholderConversation.add("[Client] This placeholder text is weak sauce.\n");
        placeholderConversation.add("[Contractor] Yeah.  It doesn't even save history between sessions.\n");
        placeholderChats.put(conversationID, placeholderConversation);
        return placeholderConversation;
    }

    @Override
    public void sendMessage(ConversationID conversationID, ChatMessage message) {
        placeholderChats.get(conversationID).add(message.getMessage());
    }
}
