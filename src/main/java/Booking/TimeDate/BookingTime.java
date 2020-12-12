package Booking.TimeDate;

import Booking.HourMinute;

public class BookingTime {
    private HourMinute mStartTime;
    private HourMinute mEndTime;

    public BookingTime(HourMinute startTime, HourMinute endTime) {
        mStartTime = startTime;
        mEndTime = endTime;
    }

    public HourMinute getmStartTime() {
        return mStartTime;
    }

    public HourMinute getmEndTime() {
        return mEndTime;
    }

    @Override
    public String toString() {
        return "From " + mStartTime.toString() + " to " + mEndTime.toString();
    }
}
