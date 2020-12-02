package Booking;

import Booking.TimeDate.BookingTimeDate;

public class BookingObject {
    private UserID mClientId;
    private UserID mContractorId;
    private BookingTimeDate mBookingTimeSlot;
    private String mWhere;
    private String mWhat;

    public BookingObject(UserID client, UserID contractor, BookingTimeDate bookingTimeDate, String where, String what) {
        mClientId = client;
        mContractorId = contractor;
        mBookingTimeSlot = bookingTimeDate;
        mWhere = where;
        mWhat = what;
        //JOptionPane.showMessageDialog(null, mClientId.mName, "Error Message", JOptionPane.ERROR_MESSAGE);
    }

    public UserID getClientId() {
        return mClientId;
    }

    public UserID getContractorId() {
        return mContractorId;
    }

    public BookingTimeDate getBookingTimeDate() {
        return mBookingTimeSlot;
    }

    public String getWhere() {
        return mWhere;
    }

    public String getWhat() {
        return mWhat;
    }
    // TODO any useful functions
}
