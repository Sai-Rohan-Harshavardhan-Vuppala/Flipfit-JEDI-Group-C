package flipfit.flipkart.bean;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class FlipFitBooking extends FlipFitSlot{
    private int bookingId;
    private int customerId;
    private String bookingStatus;
    private int paymentId;
    private Timestamp createdAt;


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }





    public FlipFitBooking() {

        /*
        waitListRank = -1 -> booking is pending
        waitListRank = 0 -> booking is confirmed
        waitListRank > 0 -> booking is waitlisted
         */
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
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

    public String getBookingStatus() {
        return bookingStatus;

    }
    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }


    public Timestamp getcreatedAt() {
        return createdAt;
    }
}
