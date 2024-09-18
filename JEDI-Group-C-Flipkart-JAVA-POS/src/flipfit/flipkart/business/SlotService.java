package flipfit.flipkart.business;

import flipfit.flipkart.bean.Slot;

import java.sql.Time;

public class SlotService {
    public Slot createSlot(int gymId, Time startTime, Time endTime, int seatsAvailable) {
        Slot slot = new Slot(gymId, startTime, endTime, seatsAvailable);
        System.out.println("Created slot " + slot);
        return slot;
    }


    public void updateSlot(int gymId, Time startTime, Time endTime, int seatsAvailable) {
        System.out.println("Updated slot");
    }

    public boolean deleteSlot(int SlotId) {
        System.out.println("Delete slot with " + SlotId);
        return true;
    }

    public boolean checkAvalabilityOfSlot(int slotId){
        System.out.println("Checked availability of " + slotId);
        return true;
    }
}
