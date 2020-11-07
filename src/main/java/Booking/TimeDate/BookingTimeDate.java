package Booking.TimeDate;

import Booking.HourMinute;

import java.util.Date;

public class BookingTimeDate extends BookingTime {
    private Date mDate;

    public BookingTimeDate(Date date, HourMinute startTime, HourMinute endTime) {
        super(startTime, endTime);
        mDate = date;
    }

    // TODO write getters and setters as needed

    public boolean interferesWith(BookingTimeDate otherTimeSlot) {
        // TODO write actual logic
        return false;
    }
}
