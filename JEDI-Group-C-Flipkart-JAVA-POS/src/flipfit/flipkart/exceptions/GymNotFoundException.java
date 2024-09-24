package flipfit.flipkart.exceptions;

public class GymNotFound extends Exception {
    int gymId;
    public GymNotFound(int gymId) {
        this.gymId = gymId;
    }

    public String getMessage() {
        return "Gym " + gymId + " not found";
    }

}
