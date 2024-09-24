package flipfit.flipkart.exceptions;

public class BookingNotFoundException extends Exception {
    int bookingId;
    public BookingNotFoundException(int bookingId) {
        this.bookingId = bookingId;
    }
    public String getMessage() {
        return "Booking with booking ID " + bookingId + " does not exist.";
    }
}
