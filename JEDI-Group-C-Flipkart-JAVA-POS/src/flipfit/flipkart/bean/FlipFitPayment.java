package flipfit.flipkart.bean;

public class FlipFitPayment {
    private String transactionId;
    private int paymentId;
    static int paymentCounter = 0;

    public FlipFitPayment(String transactionId) {
        this.transactionId = transactionId;
        this.paymentId = paymentCounter++;
    }

    public int getPaymentId(){
        return paymentId;
    }
}
