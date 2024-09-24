package flipfit.flipkart.exceptions;

public class UserNotFoundException extends Exception {
    int userId;
    public UserNotFoundException(int userId) {
        this.userId = userId;
    }

    public String getMessage(){
        return "User with id " + userId + " does not exist\n";
    }
}
