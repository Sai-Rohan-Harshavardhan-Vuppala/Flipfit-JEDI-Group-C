package flipfit.flipkart.exceptions;

public class IncorrectRoleSelectedException extends Exception {
    public IncorrectRoleSelectedException() {

    }

    public String getMessage(){
        return "Incorrect role selected\n";
    }
}
