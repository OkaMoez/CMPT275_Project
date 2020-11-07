package Booking;

import javax.swing.*;
import java.awt.*;

public class BookingObjectRenderer extends DefaultListCellRenderer
{
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        BookingObject object = (BookingObject)value;
        setText(object.getClientId().mName);

        return this;
    }
}
