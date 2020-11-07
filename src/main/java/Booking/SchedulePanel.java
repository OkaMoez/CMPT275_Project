package Booking;

import javax.swing.*;
import java.awt.*;

public class SchedulePanel extends JPanel{
    private JPanel schedulePanel;
    private JLabel bookingListLabel;
    private JTextField bookingSearchTextField;
    private JList bookingList;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private DefaultListModel bookingListModel;

    public SchedulePanel() {
        // InquiryPanel extends JPanel but is not the same as inquiryPanel
        // Adding inquiryPanel to this parent class and applying a GridLayout
        // make them look and kinda act like the same thing
        this.setLayout(new GridLayout());
        this.add(schedulePanel);

        bookingListLabel.setText("Existing Bookings");
        populateDummyBookings();
    }

    void populateDummyBookings() {
        bookingListModel.addElement("Jane Doe");
        bookingListModel.addElement("John Smith");
        bookingListModel.addElement("Kathy Green");
    }

    private void createUIComponents() {
        bookingListModel = new DefaultListModel();
        bookingList = new JList(bookingListModel);
    }
}
