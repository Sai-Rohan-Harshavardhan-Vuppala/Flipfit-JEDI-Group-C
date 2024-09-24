package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitPaymentDAOImpl;
import flipfit.flipkart.DAO.FlipFitPaymentDAOInterface;
import flipfit.flipkart.bean.FlipFitPayment;

public class FlipFitPaymentService {
    private FlipFitPaymentDAOInterface flipFitPaymentDAO;
    public FlipFitPaymentService() {
        flipFitPaymentDAO = new FlipFitPaymentDAOImpl();
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
