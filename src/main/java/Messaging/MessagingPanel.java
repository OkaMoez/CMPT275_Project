package Messaging;

import MainWindow.MainWindow;
import Server.MessagingServerInterface;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Vector;

public class MessagingPanel extends JPanel{
    // Owner window
    MainWindow mainWindow;

    // Main panel (still needs to be added to InquiryPanel objects)
    private JPanel messagingPanel;

    // Contact panel, children, and settings
    private JPanel contactPanel;
    private JLabel contactListLabel;
    private JTextField contactSearchTextField;
    private JList contactList;
    private DefaultListModel contactListModel;
    private boolean contactsLoaded;

    // Message history panel, children, and settings
    private JPanel messagePanel;
    private JLabel messageLabel;
    private ConversationID currentConversation;
    private JTextPane messagePane;
    private JTextField sendMessageTextField;
    private JButton sendMessageButton;

    // temp?
    MessagingServerInterface messagingServer;

    public MessagingPanel(MainWindow mainWindow) {
        // Keep a reference to the owner object
        this.mainWindow = mainWindow;

        // MessagingPanel extends JPanel but is not the same as messagingPanel
        // Adding messagingPanel to this parent class and applying a GridLayout
        // make them look and kinda act like the same thing
        this.setLayout(new GridLayout());
        this.add(messagingPanel);

        contactListLabel.setText("Chat History");

        // fill placeholders
        messagingServer = mainWindow.getServerConnection();
        
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = messagePane.getDocument();
                String newMessage = sendMessageTextField.getText();
                try {
                    LocalDateTime timestamp = java.time.LocalDateTime.now();
                    ChatMessage messageToSend = new ChatMessage(mainWindow.getCurrentUser().getUserID(), timestamp, newMessage);
                    messagingServer.sendMessage(currentConversation, messageToSend);

                    addMessageToDocument(document, messageToSend);

                    // Clear message text field for next message input
                    sendMessageTextField.setText("");
                }
                catch (Exception eSend) {
                    // This is only so that the insertString calls will fire
                    // Java is picky
                }
            }
        });

        contactList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent selectionEvent) {
                if (!selectionEvent.getValueIsAdjusting() && contactsLoaded) {
                    // Query message server for selected conversation history
                    ConversationID conversationID = (ConversationID)contactList.getSelectedValue();
                    if ((currentConversation != null) && currentConversation.equals(conversationID)) {
                        // Don't update if the same conversation is selected twice
                        return;
                    }
                    // Otherwise, clear old messages and get new ones from the server
                    currentConversation = conversationID;
                    messageLabel.setText(currentConversation.getOtherUserID().toString());
                    messagePane.setText(null);
                    Vector<ChatMessage> chatHistory = messagingServer.getConversationHistory(conversationID);

                    // Populate page with chat history
                    Document document = messagePane.getDocument();
                    chatHistory.forEach((message) -> addMessageToDocument(document, message));
                }
            }
        });

    }

    public void pageSelected() {
        DefaultListModel model = (DefaultListModel) contactList.getModel();
        if (model.isEmpty()) {
            // Lock contact selection listener while loading contacts
            contactsLoaded = false;

            model.removeAllElements();
            messagingServer.getConversationList(mainWindow.getCurrentUser().getUserID()).forEach((conversationID) -> addContactToList(contactListModel, conversationID));

            // Unlock when done
            contactsLoaded = true;
        }
    }

    public JButton getDefaultButton() {
        return sendMessageButton;
    }

    private void addContactToList(DefaultListModel model, ConversationID conversationID) {
        model.addElement(conversationID);
    }

    private void addMessageToDocument(Document document, ChatMessage message) {
        try {
            String messageInfo = "[" + message.getSender().toString() + "] - " + message.getTimestamp().toString() + "\n";
            document.insertString(document.getLength(), messageInfo, null);
            document.insertString(document.getLength(), message.getMessage() + "\n", null);
        }
        catch (Exception e) {
            // This is only so that the insertString calls will fire
            // Java is picky
        }
    }

    private void createUIComponents() {
        contactListModel = new DefaultListModel();
        contactList = new JList(contactListModel);
        contactList.setCellRenderer(new ContactListRenderer());
    }
}
