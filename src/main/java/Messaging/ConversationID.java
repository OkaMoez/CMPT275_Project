package Messaging;

import Users.UserID;

public class ConversationID {
    private UserID conversationOwner;
    private UserID otherUser;

    public ConversationID(UserID conversationOwner, UserID otherUser) {
        this.conversationOwner = conversationOwner;
        this.otherUser = otherUser;
    }

    public UserID getOwner() {
        return conversationOwner;
    }

    public UserID getOtherUserID() {
        return otherUser;
    }
}
