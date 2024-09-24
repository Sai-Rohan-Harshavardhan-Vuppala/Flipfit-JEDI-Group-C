package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitBookingDAOImpl;
import flipfit.flipkart.DAO.FlipFitCustomerDAOImpl;
import flipfit.flipkart.DAO.FlipFitUserDAOInterface;
import flipfit.flipkart.DAO.FlipFitUserDAOImpl;
import flipfit.flipkart.DAO.FlipFitUserDAOInterface;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.exceptions.*;
import flipfit.flipkart.exceptions.TransactionIdNotUniqueException;
import flipfit.flipkart.helper.Helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static flipfit.flipkart.helper.Helper.printInGreen;
import static flipfit.flipkart.helper.Helper.printInRed;

public class FlipFitCustomerService {
    private FlipFitCustomerDAOImpl flipFitCustomerDAOImpl;
    private FlipFitBookingDAOImpl flipFitBookingDAOImpl;

    public FlipFitCustomerService() {
        flipFitCustomerDAOImpl = new FlipFitCustomerDAOImpl();
        flipFitBookingDAOImpl = new FlipFitBookingDAOImpl();
    }

    public FlipFitCustomer login(String email, String enteredPassword) throws IncorrectCredentialsException {
        // get the customer object from database
        FlipFitUser user = Helper.verifyCredentials(email, enteredPassword);
        if(user == null){
            throw new IncorrectCredentialsException();
        }
        return flipFitCustomerDAOImpl.getByUser(user);
    }

    public FlipFitCustomer createCustomer(String username, String password, String email, String name, String phone) throws EmailAlreadyExistsException {
        FlipFitUserDAOInterface flipFitUserDAO = new FlipFitUserDAOImpl();
        if(flipFitUserDAO.getByEmail(email) != null){
            throw new EmailAlreadyExistsException(email);
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
        flipFitCustomerDAOImpl.create(userId, phone);

        printInGreen("Customer registered successfully");
        FlipFitCustomer flipFitCustomer = flipFitCustomerDAOImpl.getByUser(createdUser);
        flipFitCustomer.setUserFields(createdUser);
        return flipFitCustomer;
    }

    public FlipFitCustomer getCustomerByUser(FlipFitUser user){
        return flipFitCustomerDAOImpl.getByUser(user);
    }
    /*
     * Booking services begin from here ------------------------>
     */

    public List<FlipFitBooking> getBookingsByCustomerId(int customerId){
        List<flipfit.flipkart.bean.FlipFitBooking> bookings = new ArrayList<>();
        printInGreen("List of all bookings displayed");
        return bookings;
    }

    public boolean createBooking(int customerId, int slotId, String transactionId) throws TransactionIdNotUniqueException, InvalidBookingDetailsException {
        // Logic for creating a booking
        FlipFitPaymentService paymentService = new FlipFitPaymentService();
        FlipFitPayment payment = paymentService.createPayment(transactionId, customerId);
        if(payment == null){
            throw new TransactionIdNotUniqueException(transactionId);
        }
        FlipFitGymOwnerService gymOwnerService = new FlipFitGymOwnerService();
        FlipFitSlot slot = gymOwnerService.getSlot(slotId);
        List<FlipFitBooking> bookings = getBookingsByCustomerIdAndDate(customerId, slot.getSlotDate());
        FlipFitBookingDAOImpl flipFitBookingDAOImpl = new FlipFitBookingDAOImpl();
        bookings.forEach(booking -> {
            if(Helper.checkIfSlotsIntersect(booking.getStartTime(), booking.getEndTime(), slot.getStartTime(), slot.getEndTime())){
                try {
                    cancelBooking(booking.getCustomerId(), booking.getBookingId());
                }
                catch(InvalidBookingIdCustomerException e){
                    printInRed(e.getMessage());
                }
                catch(BookingNotFoundException e){
                    printInRed(e.getMessage());
                }
            }
        });
        FlipFitBookingDAOImpl bookingDAO = new FlipFitBookingDAOImpl();
        String bookingStatus = slot.getSeatsAvailable() > 0 ? "confirmed" : "waitlisted";
        int seatsAvailable = bookingStatus == "confirmed" ? slot.getSeatsAvailable() - 1 : 0;
        if(bookingDAO.create(customerId, slotId, payment.getPaymentId(), bookingStatus)){
            printInGreen("Your booking is " + bookingStatus);
            FlipFitGymOwnerService flipFitGymownerService = new FlipFitGymOwnerService();
            flipFitGymownerService.updateSlot(slot.getSlotId(), slot.getGymId(), slot.getSlotDate().toString(), slot.getStartTime().toString(), slot.getEndTime().toString(), seatsAvailable, slot.getPrice(), slot.getTotalSeats(), "approved");
            return true;
        }
        throw new InvalidBookingDetailsException();
    }

    public List<FlipFitBooking> getBookingsByCustomerIdAndDate(int customerId, Date date){
        FlipFitBookingDAOImpl flipFitBookingDAOImpl = new FlipFitBookingDAOImpl();
        return flipFitBookingDAOImpl.getByCustomerIdAndDate(customerId, date.toString());
    }


    public boolean cancelBooking(int customerId, int bookingId) throws InvalidBookingIdCustomerException, BookingNotFoundException{
        try {
            FlipFitBooking booking = flipFitBookingDAOImpl.get(bookingId);
            if (customerId != booking.getCustomerId()) {
                throw new InvalidBookingIdCustomerException(bookingId);
            }
            if (booking == null) {
                throw new BookingNotFoundException(bookingId);
            }
            System.out.println(booking.toString());
            if (booking.getBookingStatus().equals("confirmed")) {
                printInGreen("Booking with id " + bookingId + " has confirmed status");
                if (confirmFirstWaitlistedBooking(booking.getSlotId()) == false) {
                    FlipFitGymOwnerService gymOwnerService = new FlipFitGymOwnerService();
                    FlipFitSlot slot = gymOwnerService.getSlot(booking.getSlotId());
                    gymOwnerService.updateSlot(slot.getSlotId(), slot.getGymId(), slot.getSlotDate().toString(), slot.getStartTime().toString(), slot.getEndTime().toString(), slot.getSeatsAvailable() + 1, slot.getPrice(), slot.getTotalSeats(), slot.getStatus());
                }
            }
        }
        catch(WaitlistedBookingsNotFoundException e){
            printInGreen(e.getMessage());
        }
        return flipFitBookingDAOImpl.delete(bookingId);
    }

    private boolean confirmFirstWaitlistedBooking(int slotId) throws WaitlistedBookingsNotFoundException{
        FlipFitBooking booking = flipFitBookingDAOImpl.getfirstCreatedBookingBySlotIdAndStatus(slotId, "waitlisted");
        if(booking == null){
            throw new WaitlistedBookingsNotFoundException(slotId);
        }
        else{
            printInGreen("Waitlisted booking found");
            flipFitBookingDAOImpl.update(booking.getBookingId(), booking.getCustomerId(), booking.getSlotId(), booking.getPaymentId(), "confirmed");
            return true;
        }
    }

    public List<FlipFitBooking> getWaitListedBookings(){
        // retrieve waitlisted bookings from booking database
        List<FlipFitBooking> bookings = new ArrayList<>();
        return bookings;
    }

    public List<FlipFitBooking> getCustomerBookings(int customerId){
        return flipFitBookingDAOImpl.getByCustomerId(customerId);
    }

    public List<FlipFitCustomer> getAllCustomers() {
        return flipFitCustomerDAOImpl.getAllCustomers();
    }

//    public void getAllBookingsByDateAndCustomerId(int customerId, String date) {
//        return flipFitBookingDAOImpl.getByDateAndCustomerId(customerId, date);
//    }
    /*
     * Booking services end here ------------------------------->
     */
}
