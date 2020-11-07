package Booking;

import java.util.Map;
import java.util.Vector;

public class BookingDatabase implements BookingInterface{
    Map<UserID, Vector<BookingObject>> bookingMap;
    Map<UserID, BookingAvailability> availabilityMap;

    public BookingDatabase() {
    }

    public Vector<BookingObject> getBookingListForUser(UserID user) {
        // TODO (maybe) add some kind of security/fail state for an empty/uninitialized vector
        return bookingMap.get(user);
    }

    public BookingResult addNewBookingToUser(UserID user, BookingObject booking) {
        // TODO add logic for checking for availability
        if (false) {
            return BookingResult.FAIL_NOT_WORKING_HOURS;
        }
        else if (false) {
            return BookingResult.FAIL_DOUBLE_BOOKED;
        }

        bookingMap.get(user).add(booking);
        return BookingResult.SUCCESS;
    }

    public BookingAvailability getContractorAvailability(UserID contractor) {
        return availabilityMap.get(contractor);
    }
}
