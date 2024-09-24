package flipfit.flipkart.exceptions;

public class SlotNotFoundException extends Exception {
    int slotId;
    public SlotNotFoundException(int slotId) {
        this.slotId = slotId;
    }
    public int getSlotId() {
        return slotId;
    }

    public String getMessage() {
        return "Slot " + slotId + " does not exist\n";
    }
}
