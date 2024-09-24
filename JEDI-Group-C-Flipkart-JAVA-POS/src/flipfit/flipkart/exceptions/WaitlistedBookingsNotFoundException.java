package flipfit.flipkart.exceptions;

public class WaitlistedBookingsNotFoundException extends Exception {
    int slotId;
    public WaitlistedBookingsNotFoundException(int slotId) {
        this.slotId = slotId;
    }

    public String getMessage() {
        return "No Waitlisted bookings exist in slot with slot ID " + slotId;
    }
}
