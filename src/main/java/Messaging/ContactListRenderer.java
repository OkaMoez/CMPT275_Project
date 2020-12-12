package Messaging;

import MainWindow.MainWindow;
import Server.ServerConnection;

import javax.swing.*;
import java.awt.*;

public class ContactListRenderer extends DefaultListCellRenderer {
    private ServerConnection serverConnection;

    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        ConversationID conversationID = (ConversationID)value;
        String senderName = serverConnection.getNameFromID(conversationID.getOtherUserID());
        setText(senderName);

        return this;
    }
}
