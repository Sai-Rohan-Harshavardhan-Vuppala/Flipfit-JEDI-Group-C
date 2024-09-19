package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitBooking;
import flipfit.flipkart.bean.FlipFitCustomer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlipFitCustomerService {

    public FlipFitCustomer login(String username, String password){
        // get the customer object from database
        FlipFitCustomer newFlipFitCustomer = createCustomer("Sai Rohan", "sairohan", "sairohan2812@gmail.com", "password");
        return newFlipFitCustomer;
    }
    public FlipFitCustomer createCustomer(String customerName, String username, String customerEmail, String customerPassword){
        FlipFitCustomer customer = new FlipFitCustomer(customerName, username, customerEmail, customerPassword);
        System.out.println("Customer " + customer + " created");
        return customer;
    }


    /*
     * Booking services begin from here ------------------------>
     */

    public List<FlipFitBooking> getBookingsByCustomerId(int customerId){
        List<flipfit.flipkart.bean.FlipFitBooking> bookings = new ArrayList<>();
        System.out.println("List of all bookings displayed");
        return bookings;
    }

    public FlipFitBooking createBooking(int customerId, int slotId, Date bookingDate, int paymentId){
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

    public List<FlipFitBooking> getWaitListedBookings(){
        // retrieve waitlisted bookings from booking database
        List<FlipFitBooking> bookings = new ArrayList<>();
        return bookings;
    }

    /*
     * Booking services end here ------------------------------->
     */
}
