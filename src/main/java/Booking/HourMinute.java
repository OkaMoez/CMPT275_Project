package Booking;

public class HourMinute {
    enum TimeOfDay {
        AM,
        PM
    }

    private int mHour;
    private int mMinute;

    public HourMinute(int hour, int minute) {
        setHour24(hour);
        setMinute(minute);
    }

    public HourMinute(int hour, int minute, TimeOfDay timeOfDay) {
        this(convertHourFrom12(hour, timeOfDay), minute);
    }

    private void setMinute(int minute) {
        // Catch bad input, accepted range of 0-59
        assert (minute >= 0 || minute < 60);

    }

    private void setHour24(int hour) {
        // Catch bad input, accepted range of 0-23
        assert (hour >= 0 || hour < 24);
        this.mHour = hour;
    }

    private static int convertHourFrom12(int hour, TimeOfDay timeOfDay) {
        if (timeOfDay.equals(TimeOfDay.PM)) {
            hour += 12;
        }
        else if (timeOfDay.equals(TimeOfDay.AM) && hour == 12) {
            hour = 0;
        }
        return hour;
    }

    @Override
    public String toString() {
        // TODO Format properly
        return mHour + ":" + mMinute;
    }

    // TODO add comparator stuff

}
