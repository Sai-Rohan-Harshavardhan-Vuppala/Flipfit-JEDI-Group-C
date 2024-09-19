package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitSlot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlipFitSlotService {
    public FlipFitSlot createSlot(int gymId, Time startTime, Time endTime, int seatsAvailable) {
        FlipFitSlot slot = new FlipFitSlot(gymId, startTime, endTime, seatsAvailable);
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
    public List<FlipFitSlot> searchByTime(String city, Time time){
        List<FlipFitSlot> slots = new ArrayList<>();
        return slots;
    }
    public List<FlipFitSlot> searchByDate(String city, Date date){
        List<FlipFitSlot> slots = new ArrayList<>();
        return slots;
    }
}
