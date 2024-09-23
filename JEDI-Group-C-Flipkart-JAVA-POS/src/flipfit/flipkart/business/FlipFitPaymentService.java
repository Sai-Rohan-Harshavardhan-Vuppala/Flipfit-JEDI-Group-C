package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitPaymentDAO;
import flipfit.flipkart.bean.FlipFitPayment;

public class FlipFitPaymentService {
    private FlipFitPaymentDAO flipFitPaymentDAO;
    public FlipFitPaymentService() {
        flipFitPaymentDAO = new FlipFitPaymentDAO();
    }
    public FlipFitPayment createPayment(String transactionId, int customerId){
        if(flipFitPaymentDAO.create(transactionId, customerId) == true){
            return getPayment(transactionId);
        }
        return null;
    }
    public FlipFitPayment getPayment(String transactionId){
        return flipFitPaymentDAO.getByTransactionId(transactionId);
    }

}
