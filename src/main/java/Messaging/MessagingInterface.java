package Messaging;

class ConversationID{};
class InquiryMessage{};

public interface MessagingInterface {
    void getMessageHistory(ConversationID conversation);
    void sendMessage(ConversationID conversation, InquiryMessage message);
}
