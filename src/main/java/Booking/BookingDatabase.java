package Booking;

import Booking.TimeDate.BookingTime;
import Booking.TimeDate.BookingTimeDate;
import Users.Client;
import Users.Contractor;
import org.apache.commons.collections.map.HashedMap;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class BookingDatabase implements BookingInterface{
    public static Map<UserID, Vector<BookingObject>> bookingMap = new HashMap<UserID, Vector<BookingObject>>();
    Map<UserID, BookingAvailability> availabilityMap;

    public BookingDatabase() {
        // Initiate booking map from csv
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/Booking/bookings.csv")))
        {
            String line = "";
            String comma = ",";
            String dash = "-";
            String colon = ":";
            while ((line = br.readLine()) != null) {
                String[] values = line.split(comma);
                UserID bookingClient = new UserID(values[0]);
                UserID bookingContractor = new UserID(values[1]);
                String[] dateArray = values[2].split(dash);
                String[] startTime = values[3].split(colon);
                String[] endTime = values[4].split(colon);
                String bookingLocation = values[5];
                String bookingNotes = values[6];
                LocalDateTime bookingDate;
                HourMinute bookingStartTime;
                HourMinute bookingEndTime;

                try{
                    bookingDate = LocalDateTime.of(Integer.valueOf(dateArray[0]), Integer.valueOf(dateArray[1]), Integer.valueOf(dateArray[2]), 0, 0, 0);
                    bookingStartTime = new HourMinute(Integer.valueOf(startTime[0]), Integer.valueOf(startTime[1]));
                    bookingEndTime = new HourMinute(Integer.valueOf(endTime[0]), Integer.valueOf(endTime[1]));
                }
                catch(Exception e) {
                    continue;
                }

                BookingTimeDate bookingTimeDate = new BookingTimeDate(bookingDate, bookingStartTime, bookingEndTime);

                BookingObject bookingObject = new BookingObject(bookingClient, bookingContractor, bookingTimeDate, bookingLocation, bookingNotes);

                if(bookingMap.containsKey(bookingContractor)) {
                    bookingMap.get(bookingClient).addElement(bookingObject);
                }
                else {
                    bookingMap.put(bookingClient, new Vector<BookingObject>());
                    bookingMap.get(bookingClient).addElement(bookingObject);
                }
            }
        }
        catch (IOException e) {
            System.out.println("IO Exception caught");
        }
        catch (NumberFormatException e) {
            System.out.println("NumberFormat Exception caught");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayOOB Exception caught");
        }
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
