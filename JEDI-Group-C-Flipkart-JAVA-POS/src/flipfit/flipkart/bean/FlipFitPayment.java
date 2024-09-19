package flipfit.flipkart.bean;

public class FlipFitPayment {
    private int transactionId;
    private int customerId;
    private int paymentId;

    public FlipFitPayment(int transactionId, int customerId, int paymentId) {
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.paymentId = paymentId;
    }
}
