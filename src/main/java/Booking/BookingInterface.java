package Booking;

import java.util.Vector;

class BookingAvailability{}

class UserID{
    public String mName;
    public UserID(String name){
        mName = name;
    }
}

public interface BookingInterface {
    enum BookingResult {
        SUCCESS,
        FAIL_DOUBLE_BOOKED,
        FAIL_NOT_WORKING_HOURS
    }

    Vector<BookingObject> getBookingListForUser(UserID user);
    BookingResult addNewBookingToUser(UserID user, BookingObject booking);

    BookingAvailability getContractorAvailability(UserID contractor);
}
