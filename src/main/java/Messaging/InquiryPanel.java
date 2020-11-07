package Messaging;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InquiryPanel extends JPanel{
    // Main panel (still needs to be added to InquiryPanel objects)
    private JPanel inquiryPanel;

    // Contact panel, children, and settings
    private JPanel contactPanel;
    private JLabel contactListLabel;
    private JTextField contactSearchTextField;
    private JList contactList;

    // Message history panel, children, and settings
    private JPanel messagePanel;
    private JLabel messageLabel;
    private JTextPane messagePane;
    private JTextField sendMessageTextField;
    private JButton sendMessageButton;

    public InquiryPanel() {
        // InquiryPanel extends JPanel but is not the same as inquiryPanel
        // Adding inquiryPanel to this parent class and applying a GridLayout
        // make them look and kinda act like the same thing
        this.setLayout(new GridLayout());
        this.add(inquiryPanel);

        contactListLabel.setText("Chat History");
        populateDummyText();

        // TODO add key listener or default button so enter sends message
        sendMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Document document = messagePane.getDocument();
                String newMessage = "[Client] " + sendMessageTextField.getText() + "\n";
                try {
                    document.insertString(document.getLength(), newMessage, null);

                    // Clear message text field for next message input
                    sendMessageTextField.setText("");
                }
                catch (Exception eSend) {
                    // This is only so that the insertString calls will fire
                    // Java is picky
                }
            }
        });

    }

    // (Brigham) This is pretty lame stub in chat, I'll try to add a better version
    // if I have time before we submit
    private void populateDummyText() {
        Document document = messagePane.getDocument();
        String message1 = "[Client] This placeholder text is weak sauce.\n";
        String message2 = "[Contractor] Yeah.  It doesn't even save history between sessions.\n";
        try {
            document.insertString(document.getLength(), message1, null);
            document.insertString(document.getLength(), message2, null);
        }
        catch (Exception e) {
            // This is only so that the insertString calls will fire
            // Java is picky
        }
    }
}
