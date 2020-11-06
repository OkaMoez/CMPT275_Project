package Messaging;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Vector;

public class InquiryPanel extends JPanel{
    private JPanel inquiryPanel;

    private JPanel contactPanel;
    private JLabel contactListLabel;
    private JTextField contactSearchTextField;
    private JList contactList;

    private JPanel messagePanel;
    private JLabel messageLabel;
    private JTextField sendMessageTextField;
    private JButton sendMessageButton;
    private JTextPane messagePane;

    Vector<Vector<String>> chatLog;

    public InquiryPanel() {
        this.setLayout(new GridLayout());
        this.add(inquiryPanel);

        contactListLabel.setText("Chat History");
        populateDummyText();
    }

    private void populateDummyText() {
        Document document = messagePane.getDocument();
        String message1 = "[Client] This dummy text is weak sauce.\n";
        String message2 = "[Contractor] Yeah.  It needs to change.\n";
        try {
            document.insertString(document.getLength(), message1, null);
            document.insertString(document.getLength(), message2, null);
        }
        catch (Exception e) {
        }
    }
}
