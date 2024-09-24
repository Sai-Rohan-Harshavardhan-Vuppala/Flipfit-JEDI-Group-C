package flipfit.flipkart.exceptions;

public class IncorrectCredentialsException extends Exception {
    public IncorrectCredentialsException() {

    }

    public String getMessage(){
        return "Email or password is incorrect.\n";
    }
}
