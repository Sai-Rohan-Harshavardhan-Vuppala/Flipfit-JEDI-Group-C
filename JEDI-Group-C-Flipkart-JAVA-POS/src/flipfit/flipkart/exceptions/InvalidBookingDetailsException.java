package flipfit.flipkart.exceptions;

public class InvalidBookingDetailsException extends Exception {
    public InvalidBookingDetailsException() {}

    public String getMessage() {
        return "Invalid booking details entered";
    }
}
