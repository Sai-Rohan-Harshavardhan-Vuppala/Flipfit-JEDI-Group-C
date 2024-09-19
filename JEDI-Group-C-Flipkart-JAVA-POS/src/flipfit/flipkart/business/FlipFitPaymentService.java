package flipfit.flipkart.business;

public class FlipFitPaymentService {
    public void distributePayment(int bookingId){
        // update the paymentToGymOwnerDatabase with the owner mentioned price payment record

        // send notification that payment recieved
    }

    public boolean updatePaymentStatus(int paymentId, int amount){
        // called when user touches the /paid endpoint
        return true;
    }


}
