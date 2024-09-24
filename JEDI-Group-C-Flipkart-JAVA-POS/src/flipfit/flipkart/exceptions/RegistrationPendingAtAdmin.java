package flipfit.flipkart.exceptions;

public class RegistrationPendingAtAdmin extends Exception {
    String entity;
    public RegistrationPendingAtAdmin(String entity) {
        this.entity = entity;
    }
    public String getMessage(){
        return entity + "registration pending at admin";
    }
}
