package Booking.TimeDate;

import Booking.HourMinute;

public class BookingTime {
    private HourMinute mStartTime;
    private HourMinute mEndTime;

    public BookingTime(HourMinute startTime, HourMinute endTime) {
        mStartTime = startTime;
        mEndTime = endTime;
    }

    @Override
    public String toString() {
        return "From " + mStartTime.toString() + " to " + mEndTime.toString();
    }
}
