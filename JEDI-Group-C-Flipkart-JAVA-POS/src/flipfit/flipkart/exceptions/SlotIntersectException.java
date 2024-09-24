package flipfit.flipkart.exceptions;

public class SlotIntersectException extends Exception {
    public SlotIntersectException() {

    }

    public String getMessage() {
        return "Slot timings intersect with those of other slots in the same gym.";
    }
}
