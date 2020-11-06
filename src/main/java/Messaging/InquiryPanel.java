package Messaging;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Vector;

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
    }

    // (Brigham) This is pretty lame stub in chat, I'll try to add a better version
    // if I have time before we submit
    private void populateDummyText() {
        Document document = messagePane.getDocument();
        String message1 = "[Client] This dummy text is weak sauce.\n";
        String message2 = "[Contractor] Yeah.  It needs to change.\n";
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
