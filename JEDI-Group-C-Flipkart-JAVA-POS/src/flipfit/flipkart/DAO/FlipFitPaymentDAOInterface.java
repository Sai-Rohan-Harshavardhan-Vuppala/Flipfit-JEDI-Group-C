package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitPayment;

public interface FlipFitPaymentDAOInterface {
    public boolean create(String transactionId, int customerId);
    public FlipFitPayment getByTransactionId(String transactionId);
}
