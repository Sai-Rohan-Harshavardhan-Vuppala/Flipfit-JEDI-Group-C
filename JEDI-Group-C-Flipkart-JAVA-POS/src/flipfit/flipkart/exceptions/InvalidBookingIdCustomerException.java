package flipfit.flipkart.exceptions;

public class InvalidBookingIdCustomerException extends Exception {
    int bookingId;
    public InvalidBookingIdCustomerException(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getMessage() {
        return "Booking id " + bookingId + " does not exist in your bookings. Please enter a valid Booking ID.";
    }
}
