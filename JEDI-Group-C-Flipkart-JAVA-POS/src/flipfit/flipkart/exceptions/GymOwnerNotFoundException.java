package flipfit.flipkart.exceptions;

public class GymOwnerNotFoundException extends Exception {
    int gymOwnerId;
    public GymOwnerNotFoundException(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
    }

    public String getMessage() {
        return "Gym owner with id " + gymOwnerId + " does not exist.";
    }
}
