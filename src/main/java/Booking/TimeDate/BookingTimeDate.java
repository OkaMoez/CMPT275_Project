package Booking.TimeDate;

import Booking.HourMinute;

import java.time.LocalDateTime;
import java.util.Date;

public class BookingTimeDate extends BookingTime {
    private LocalDateTime mDate;

    public BookingTimeDate(LocalDateTime date, HourMinute startTime, HourMinute endTime) {
        super(startTime, endTime);
        mDate = date;
    }

    // TODO write getters and setters as needed

    public LocalDateTime getmDate() {
        return mDate;
    }

    @Override
    public String toString() {
        // TODO make this good
        return super.toString() + " on " + mDate.toString();
    }

    public boolean interferesWith(BookingTimeDate otherTimeSlot) {
        if (this.getmDate().equals(otherTimeSlot.getmDate())) {
            if (this.getmStartTime().fallsWithin(otherTimeSlot.getmStartTime(), otherTimeSlot.getmEndTime()) ||
                    this.getmEndTime().fallsWithin(otherTimeSlot.getmStartTime(), otherTimeSlot.getmEndTime())) {
                return true;
            } else if (otherTimeSlot.getmStartTime().fallsWithin(this.getmStartTime(), this.getmEndTime()) ||
                    otherTimeSlot.getmEndTime().fallsWithin(this.getmStartTime(), this.getmEndTime())) {
                return true;
            }
        }
        return false;
    }
}
