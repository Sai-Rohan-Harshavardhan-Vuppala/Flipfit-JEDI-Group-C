package flipfit.flipkart.exceptions;

public class EmailAlreadyExistsException extends Exception {
    String email;
    public EmailAlreadyExistsException(String email) {
        email = email;
    }

    public String getMessage(){
        return "User with email " + email + " already exists. Please enter a unique email address.";
    }
}
