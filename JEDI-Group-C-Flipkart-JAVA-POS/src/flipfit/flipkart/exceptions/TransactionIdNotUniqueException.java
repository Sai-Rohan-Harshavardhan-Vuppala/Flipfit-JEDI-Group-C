package flipfit.flipkart.exceptions;

public class TransactionIdNotUniqueException extends Exception {
    String trransactionId;

    public TransactionIdNotUniqueException(String trransactionId) {
        this.trransactionId = trransactionId;
    }

    public String getMessage(){
        return "Transaction ID " + trransactionId + " is not unique. Please enter a unique transaction ID.";
    }
}
