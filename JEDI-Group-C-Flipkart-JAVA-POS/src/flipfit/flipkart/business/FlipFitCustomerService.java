package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitCustomerDAO;
import flipfit.flipkart.DAO.FlipFitUserDAO;
import flipfit.flipkart.bean.FlipFitBooking;
import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitUser;
import flipfit.flipkart.helper.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlipFitCustomerService {
    private FlipFitCustomerDAO flipFitCustomerDAO;

    public FlipFitCustomerService() {
        flipFitCustomerDAO = new FlipFitCustomerDAO();
    }

    public FlipFitCustomer login(String email, String enteredPassword){
        // get the customer object from database
        FlipFitUser user = Helper.verifyCredentials(email, enteredPassword);
        return user != null ? flipFitCustomerDAO.getByUser(user) : null;
    }

    public FlipFitCustomer createCustomer(String username, String password, String email, String name, String phone){
        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        if(flipFitUserDAO.getByEmail(email) != null){
            System.out.println("User with email " + email + " already exists");
            return null;
        }
        System.out.println("User with email " + email + " does not exist");
        /*
            We need to validate whether all the fields which
            are entered follow their respective conditions
            ---- TO BE DONE --

        */
        flipFitUserDAO.create(username, password, email, name, 2, "whitelisted");
        FlipFitUser createdUser = flipFitUserDAO.getByEmail(email);

        int userId = createdUser.getUserId();
        flipFitCustomerDAO.create(userId, phone);

        System.out.println("Customer registered successfully");
        FlipFitCustomer flipFitCustomer = flipFitCustomerDAO.getByUser(createdUser);
        flipFitCustomer.setUserFields(createdUser);
        return flipFitCustomer;
    }

    public FlipFitCustomer getCustomerByUser(FlipFitUser user){

        return flipFitCustomerDAO.getByUser(user);
    }
    /*
     * Booking services begin from here ------------------------>
     */

    public List<FlipFitBooking> getBookingsByCustomerId(int customerId){
        List<flipfit.flipkart.bean.FlipFitBooking> bookings = new ArrayList<>();
        System.out.println("List of all bookings displayed");
        return bookings;
    }

    public FlipFitBooking createBooking(int customerId, int slotId, String transactionId){
        // Logic for creating a booking
        FlipFitPaymentService paymentService = new FlipFitPaymentService();
        int paymentId = paymentService.createPayment(transactionId);
        FlipFitBooking booking = new FlipFitBooking(customerId, slotId, paymentId);
        System.out.println("Created booking successfully");
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
    public List<FlipFitCustomer> getAllCustomers() {
        return flipFitCustomerDAO.getAllCustomers();
    }
    /*
     * Booking services end here ------------------------------->
     */
}
