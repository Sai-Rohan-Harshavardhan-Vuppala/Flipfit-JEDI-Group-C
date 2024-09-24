package flipfit.flipkart.exceptions;

public class SlotAlreadyApprovedException extends Exception{
    int slotId;
    public SlotAlreadyApprovedException(int slotId) {
        this.slotId = slotId;
    }
    public int getSlotId() {
        return slotId;
    }
    public String getMessage() {
        return "Slot with slot ID" + slotId + " already approved\n";
    }
}
