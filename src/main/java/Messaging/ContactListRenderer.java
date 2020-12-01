package Messaging;

import javax.swing.*;
import java.awt.*;

public class ContactListRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        ConversationID conversationID = (ConversationID)value;
        setText(conversationID.getOtherUserID().toString());

        return this;
    }
}
