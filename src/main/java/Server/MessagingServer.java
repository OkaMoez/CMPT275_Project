package Server;

import Messaging.ChatMessage;
import Messaging.ConversationID;
import Users.UserID;

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


    public MessagingServer() {
        placeholderUser1 = new UserID("user");
        placeholderUser2 = new UserID("Amon");
        placeholderUser3 = new UserID("Brigham");
        placeholderUser4 = new UserID("Uchechi");
        placeholderUser5 = new UserID("Bob Ross");
        placeholderUser6 = new UserID("Bob the Builder");
        placeholderUser7 = new UserID("Gordon Ramsey");

        placeholderChats = new HashMap<ConversationID, Vector<String>>();
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
