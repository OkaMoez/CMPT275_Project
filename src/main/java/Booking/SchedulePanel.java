package Booking;

import Booking.TimeDate.BookingTimeDate;
import MainWindow.MainWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.Vector;

import static Booking.BookingDatabase.bookingMap;

public class SchedulePanel extends JPanel{
    private JPanel schedulePanel;
    private JLabel bookingListLabel;
    private JTextField bookingSearchTextField;
    private JList bookingList;
    private DefaultListModel bookingListModel;
    private MainWindow mainWindow;

    private JTextArea nameTextArea;
    private JTextArea timeDateTextArea;
    private JTextArea locationTextArea;
    private JTextArea taskTextArea;
    private JTextArea notesTextArea;

    public SchedulePanel(MainWindow mainWindowCopy) {
        // InquiryPanel extends JPanel but is not the same as inquiryPanel
        // Adding inquiryPanel to this parent class and applying a GridLayout
        // make them look and kinda act like the same thing
        this.setLayout(new GridLayout());
        this.add(schedulePanel);

        this.mainWindow = mainWindowCopy;

        bookingListLabel.setText("Existing Bookings");
        populateDummyBookings();
        populateContractorBookings();

        bookingList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent selectionEvent) {
                if (!selectionEvent.getValueIsAdjusting()) {
                    BookingObject object = (BookingObject)bookingList.getSelectedValue();
                    nameTextArea.setText(object.getClientId().mName);
                    timeDateTextArea.setText(object.getBookingTimeDate().toString());
                    locationTextArea.setText(object.getWhere());
                    taskTextArea.setText(object.getWhat());
                    // TODO add notes field
                    // notesTextArea.setText(object.getmClientId().mName);
                }
            }
        });
    }

    public void populateContractorBookings() {
        try {
            Vector<BookingObject> allBookings = new Vector<BookingObject>();
            if (bookingMap.containsKey(mainWindow.getUsername())) {
                allBookings = bookingMap.get(mainWindow.getUsername());
            }

            for (int i = 0; i < allBookings.size(); i++) {
                bookingListModel.addElement(allBookings.get(i));
            }
        }
        catch (NullPointerException e) {
            // do nothing
        }
        catch (Exception e) {
            //do nothing
        }
    }

    void populateDummyBookings() {
        BookingObject booking1 = new BookingObject(
                new UserID("Bob Smith"),
                new UserID("builder"),
                new BookingTimeDate(LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40), new HourMinute(10, 30), new HourMinute(11, 00)),
                "My place",
                "Fix it");
        BookingObject booking2 = new BookingObject(
                new UserID("Jane Doe"),
                new UserID("builder"),
                new BookingTimeDate(LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40), new HourMinute(10, 30), new HourMinute(11, 00)),
                "My place",
                "New roof");
        BookingObject booking3 = new BookingObject(
                new UserID("Some Other Person"),
                new UserID("builder"),
                new BookingTimeDate(LocalDateTime.of(2015, Month.JULY, 29, 19, 30, 40), new HourMinute(10, 30), new HourMinute(11, 00)),
                "My place",
                "Demolish whole house plz");
        bookingListModel.addElement(booking1);
        bookingListModel.addElement(booking2);
        bookingListModel.addElement(booking3);
    }

    private void createUIComponents() {
        bookingListModel = new DefaultListModel();
        bookingList = new JList(bookingListModel);
        bookingList.setCellRenderer(new BookingObjectRenderer());
    }
}
