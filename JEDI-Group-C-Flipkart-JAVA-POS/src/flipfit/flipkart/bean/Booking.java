package flipfit.flipkart.bean;

import java.util.Date;

public class Booking {
    private String bookingId;
    private int customerId;
    private int slotId;
    private Date bookingDate;
    private String bookingStatus;

    public Booking(String bookingId, int customerId, int slotId, Date bookingDate) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.slotId = slotId;
        this.bookingDate = bookingDate;
        this.bookingStatus = "Booked";
    }
    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBookingStatus() {
        return bookingStatus;

    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

}
