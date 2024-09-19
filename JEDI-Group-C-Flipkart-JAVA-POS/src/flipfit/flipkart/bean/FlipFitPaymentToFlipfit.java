package flipfit.flipkart.bean;

import java.util.Date;

public class PaymentToFlipfit {
    private int bookingId;
    private int paymentId;
    private String status;
    private double amount;
    private Date date;
    static int counter = 0;

    public PaymentToFlipfit(int bookingId, String status, double amount, Date date) {
        this.bookingId = bookingId;
        this.status = status;
        this.amount = amount;
        this.date = date;
        this.paymentId = counter++;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }






}
