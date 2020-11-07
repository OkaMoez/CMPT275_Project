package Booking;

import Booking.TimeDate.BookingTimeDate;

public class BookingObject {
    private UserID mClientId;
    private UserID mContractorId;
    private BookingTimeDate mBookingTimeSlot;

    public BookingObject(UserID client, UserID contractor, BookingTimeDate bookingTimeDate) {
        mClientId = client;
        mContractorId = contractor;
        mBookingTimeSlot = bookingTimeDate;
    }

    // TODO any useful functions
}
