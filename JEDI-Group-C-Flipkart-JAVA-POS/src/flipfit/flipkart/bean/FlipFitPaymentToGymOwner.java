package flipfit.flipkart.bean;

import java.util.Date;

public class PaymentToGymOwner {
    private int paymentId;
    private int gymOwnerId;
    private double amount;
    private Date date;
    private String status;
    static int paymentCounter = 0;

    public PaymentToGymOwner(int gymOwnerId, double amount, Date date, String status) {
        this.paymentId = paymentCounter++;
        this.gymOwnerId = gymOwnerId;
        this.amount = amount;
        this.date = date;
        this.status = status;
    }



    public int getPaymentId() {
        return paymentId;
    }

    public int getGymOwnerId() {
        return gymOwnerId;
    }

    public void setGymOwnerId(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





}
