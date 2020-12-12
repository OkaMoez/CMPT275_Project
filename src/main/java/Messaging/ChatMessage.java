package Messaging;

import Users.UserID;

import java.time.LocalDateTime;

public class ChatMessage {
    private UserID sender;
    private LocalDateTime timestamp;
    private String message;

    public ChatMessage(UserID senderID, LocalDateTime timestamp, String message) {
        this.sender = senderID;
        this.timestamp = timestamp;
        this.message = message;
    }
    public ChatMessage(String senderID, String timestamp, String message) {
        this.sender = new UserID(senderID);
        this.timestamp = LocalDateTime.parse(timestamp);
        this.message = message;
    }

    public UserID getSender() { return sender; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getMessage() {
        return message;
    }
}
