package flipfit.flipkart.exceptions;

public class EmailNotFoundException extends Exception {
    String email;
    public EmailNotFoundException(String email) {
        this.email = email;
    }
    public String getMessage(){
        return "Email ID: " + email + " does not exist.";
    }
}
