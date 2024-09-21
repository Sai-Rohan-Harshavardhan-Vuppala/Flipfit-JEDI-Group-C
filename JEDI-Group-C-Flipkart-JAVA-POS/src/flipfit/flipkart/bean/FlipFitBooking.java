package flipfit.flipkart.bean;

import java.util.Date;

public class FlipFitBooking {
    private int bookingId;
    private int customerId;
    private int slotId;
    private Date bookingDate;
    private String bookingStatus;
    private int waitListRank;
    private int paymentId;

    public FlipFitBooking(int customerId, int slotId, int paymentId) {
        this.customerId = customerId;
        this.slotId = slotId;
        this.bookingStatus = "Booked";
        this.paymentId = paymentId;
        this.waitListRank = -1;
        /*
        waitListRank = -1 -> booking is pending
        waitListRank = 0 -> booking is confirmed
        waitListRank > 0 -> booking is waitlisted
         */
    }
    public int getPaymentId(){
        return paymentId;
    }

    public int getBookingId() {
        return bookingId;
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

    public int getWaitListRank() {
        return waitListRank;
    }

    public void setWaitListRank(int waitListRank) {
        this.waitListRank = waitListRank;
    }

}
