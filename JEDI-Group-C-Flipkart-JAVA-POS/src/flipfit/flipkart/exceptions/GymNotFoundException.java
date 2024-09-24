package flipfit.flipkart.exceptions;

public class GymNotFoundException extends Exception {
    int gymId;
    public GymNotFoundException(int gymId) {
        this.gymId = gymId;
    }

    public String getMessage() {
        return "Gym " + gymId + " not found\n";
    }

}
