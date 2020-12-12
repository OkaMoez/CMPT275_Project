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

    public UserID getFirstAlpha() {
        String owner = this.conversationOwner.toString();
        String other = this.otherUser.toString();

        // Assert that no conversation is with one's self
        assert (owner.compareToIgnoreCase(other) != 0);

        // TODO cache this result so it isn't redone every call
        if (owner.compareToIgnoreCase(other) < 0) {
            return this.conversationOwner;
        }
        else {
            return this.otherUser;
        }
    }

    public UserID getSecondAlpha() {
        String owner = this.conversationOwner.toString();
        String other = this.otherUser.toString();

        // Assert that no conversation is with one's self
        assert (owner.compareToIgnoreCase(other) != 0);

        // TODO cache this result so it isn't redone every call
        if (owner.compareToIgnoreCase(other) > 0) {
            return this.conversationOwner;
        }
        else {
            return this.otherUser;
        }
    }
}
