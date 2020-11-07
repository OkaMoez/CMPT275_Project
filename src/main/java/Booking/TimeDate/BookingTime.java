package Booking.TimeDate;

import Booking.HourMinute;

import java.util.Date;

public class BookingTime {
    private HourMinute mStartTime;
    private HourMinute mEndTime;

    public BookingTime(HourMinute startTime, HourMinute endTime) {
        mStartTime = startTime;
        mEndTime = endTime;
    }
}
