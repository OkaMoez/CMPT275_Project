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
        // Catch bad input
        assert (minute < 0 || minute > 59);

    }

    private void setHour24(int hour) {
        // Catch bad input
        assert (hour < 0 || hour > 23);
        this.mHour = hour;
    }

    private static int convertHourFrom12(int hour, TimeOfDay timeOfDay) {
        if (timeOfDay == TimeOfDay.PM) {
            hour += 12;
        }
        else if (timeOfDay == TimeOfDay.AM && hour == 12) {
            hour = 0;
        }
        return hour;
    }

    // TODO add comparator stuff

}
