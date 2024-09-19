package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitPayment;

public class FlipFitPaymentService {
    public int createPayment(String transactionId){
        FlipFitPayment flipFitPayment = new FlipFitPayment(transactionId);
        return flipFitPayment.getPaymentId();
    }

}
