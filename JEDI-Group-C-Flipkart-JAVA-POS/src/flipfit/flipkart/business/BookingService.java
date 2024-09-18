package flipfit.flipkart.business;

import flipfit.flipkart.bean.Booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookingService {
    public List<Booking> getBookingsByCustomerId(int customerId){
        List<Booking> bookings = new ArrayList<>();
        System.out.println("List of all bookings displayed");
        return bookings;
    }

    public Booking createBooking(int customerId, int slotId, Date bookingDate, int paymentId){
        // Logic for creating a booking
        Booking booking = new Booking(customerId, slotId, bookingDate);
        System.out.println("Created booking");
        return booking;
    }

    public boolean confirmBooking(int paymentId, int amount){
        // get the payment record and validate the amount

        // confirm or reject the booking
    }

    public Boolean cancelBooking(int bookingId){
        System.out.println("Booking with bookingId: " + bookingId + " cancelled");
        return true;
    }

    public void waitList(int bookingId){

        System.out.println("Booking with bookingId: " + bookingId + " waitlisted");

    }

    public List<Booking> getWaitListedBookings(){
        // retrieve waitlisted bookings from booking database
        List<Booking> bookings = new ArrayList<>();
        return bookings;
    }

}
