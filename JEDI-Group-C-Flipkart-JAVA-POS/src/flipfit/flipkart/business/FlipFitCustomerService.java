package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitBookingDAO;
import flipfit.flipkart.DAO.FlipFitCustomerDAO;
import flipfit.flipkart.DAO.FlipFitUserDAO;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.helper.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlipFitCustomerService {
    private FlipFitCustomerDAO flipFitCustomerDAO;
    private FlipFitBookingDAO flipFitBookingDAO;

    public FlipFitCustomerService() {
        flipFitCustomerDAO = new FlipFitCustomerDAO();
        flipFitBookingDAO = new FlipFitBookingDAO();
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

    public boolean createBooking(int customerId, int slotId, String transactionId){
        // Logic for creating a booking
        FlipFitPaymentService paymentService = new FlipFitPaymentService();
        FlipFitPayment payment = paymentService.createPayment(transactionId, customerId);
        if(payment == null){
            System.out.println("Transaction id must be unique.");
            return false;
        }
        FlipFitGymOwnerService gymOwnerService = new FlipFitGymOwnerService();
        FlipFitSlot slot = gymOwnerService.getSlot(slotId);
        List<FlipFitBooking> bookings = getBookingsByCustomerIdAndDate(customerId, slot.getSlotDate());
        FlipFitBookingDAO flipFitBookingDAO = new FlipFitBookingDAO();
        for(FlipFitBooking booking : bookings){
            if(Helper.checkIfSlotsIntersect(booking.getStartTime(), booking.getEndTime(), slot.getStartTime(), slot.getEndTime())){
                cancelBooking(booking.getCustomerId(), booking.getBookingId());
            }
        }
        FlipFitBookingDAO bookingDAO = new FlipFitBookingDAO();
        String bookingStatus = slot.getSeatsAvailable() > 0 ? "confirmed" : "waitlisted";
        int seatsAvailable = bookingStatus == "confirmed" ? slot.getSeatsAvailable() - 1 : 0;
        if(bookingDAO.create(customerId, slotId, payment.getPaymentId(), bookingStatus)){
            System.out.println("Your booking is " + bookingStatus);
            FlipFitGymOwnerService flipFitGymownerService = new FlipFitGymOwnerService();
            flipFitGymownerService.updateSlot(slot.getSlotId(), slot.getGymId(), slot.getSlotDate().toString(), slot.getStartTime().toString(), slot.getEndTime().toString(), seatsAvailable, slot.getPrice(), slot.getTotalSeats(), "approved");
            return true;
        }
        System.out.println("Invalid details. Booking failed");
        return false;
    }

    public List<FlipFitBooking> getBookingsByCustomerIdAndDate(int customerId, Date date){
        FlipFitBookingDAO flipFitBookingDAO = new FlipFitBookingDAO();
        return flipFitBookingDAO.getByCustomerIdAndDate(customerId, date.toString());
    }


    public boolean cancelBooking(int customerId, int bookingId){
        FlipFitBooking booking = flipFitBookingDAO.get(bookingId);
        if(customerId != booking.getCustomerId()){
            System.out.println("Booking ID not present in your bookings. Please enter a valid Booking ID.");
            return false;
        }
        if(booking == null){
            System.out.println("Booking with id " + bookingId + " does not exist");
            return false;
        }
        System.out.println(booking.toString());
        if(booking.getBookingStatus().equals("confirmed")){
            System.out.println("Booking with id " + bookingId + " has confirmed status");
            if(confirmFirstWaitlistedBooking(booking.getSlotId()) == false){
                FlipFitGymOwnerService gymOwnerService = new FlipFitGymOwnerService();
                FlipFitSlot slot = gymOwnerService.getSlot(booking.getSlotId());
                gymOwnerService.updateSlot(slot.getSlotId(), slot.getGymId(), slot.getSlotDate().toString(), slot.getStartTime().toString(), slot.getEndTime().toString(), slot.getSeatsAvailable() + 1, slot.getPrice(), slot.getTotalSeats(), slot.getStatus());
            }
        }
        return flipFitBookingDAO.delete(bookingId);
    }

    private boolean confirmFirstWaitlistedBooking(int slotId) {
        FlipFitBooking booking = flipFitBookingDAO.getfirstCreatedBookingBySlotIdAndStatus(slotId, "waitlisted");
        if(booking == null){
            System.out.println("No waitlisted bookings exist in slot with slot ID " + slotId);
            return false;
        }
        else{
            System.out.println("Waitlisted booking found");
            flipFitBookingDAO.update(booking.getBookingId(), booking.getCustomerId(), booking.getSlotId(), booking.getPaymentId(), "confirmed");
            return true;
        }
    }

    public List<FlipFitBooking> getWaitListedBookings(){
        // retrieve waitlisted bookings from booking database
        List<FlipFitBooking> bookings = new ArrayList<>();
        return bookings;
    }

    public List<FlipFitBooking> getCustomerBookings(int customerId){
        return FlipFitBookingDAO.getByCustomerId(customerId);
    }

    public List<FlipFitCustomer> getAllCustomers() {
        return flipFitCustomerDAO.getAllCustomers();
    }
    /*
     * Booking services end here ------------------------------->
     */
}
