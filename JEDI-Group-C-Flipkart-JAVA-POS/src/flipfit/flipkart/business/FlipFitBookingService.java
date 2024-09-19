package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitBooking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlipFitBookingService {
    public List<FlipFitBooking> getBookingsByCustomerId(int customerId){
        List<flipfit.flipkart.bean.FlipFitBooking> bookings = new ArrayList<>();
        System.out.println("List of all bookings displayed");
        return bookings;
    }

    public flipfit.flipkart.bean.FlipFitBooking createBooking(int customerId, int slotId, Date bookingDate, int paymentId){
        // Logic for creating a booking
        flipfit.flipkart.bean.FlipFitBooking booking = new flipfit.flipkart.bean.FlipFitBooking(customerId, slotId, bookingDate);
        System.out.println("Created booking");
        return booking;
    }

    public boolean confirmBooking(int paymentId, int amount){
        // get the payment record and validate the amount

        // confirm or reject the booking
        return true;
    }

    public Boolean cancelBooking(int bookingId){
        System.out.println("Booking with bookingId: " + bookingId + " cancelled");
        return true;
    }

    public void waitList(int bookingId){

        System.out.println("Booking with bookingId: " + bookingId + " waitlisted");

    }

    public List<flipfit.flipkart.bean.FlipFitBooking> getWaitListedBookings(){
        // retrieve waitlisted bookings from booking database
        List<flipfit.flipkart.bean.FlipFitBooking> bookings = new ArrayList<>();
        return bookings;
    }
}
