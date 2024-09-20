package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.bean.FlipFitSlot;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FlipFitGymOwnerService {
    public FlipFitGymOwner createGymOwner(String name, String email, String username, String password, String accountNumber){
        FlipFitGymOwner gymOwner = new FlipFitGymOwner(name, email, username, password, accountNumber);
        System.out.println("Gym owner" + gymOwner + "created");
        System.out.println("\nRegistration successful");
        return gymOwner;
    }

    public boolean login(String username, String password){
        return true;
    }

    public void createGym(){

    }

    public void updateGym(){

    }

    public void deleteGym(){

    }

    public void getGymByGymId(){

    }

    /*
     * Slot services begin from here ----------------------->
     */

    public FlipFitSlot createSlot(int gymId, LocalTime startTime, LocalTime endTime, int seatsAvailable, double price) {
        FlipFitSlot slot = new FlipFitSlot(gymId, startTime, endTime, seatsAvailable, price);
        System.out.println("Created slot " + slot);
        return slot;
    }


    public void updateSlot(int gymId, LocalTime startTime, LocalTime endTime, int seatsAvailable) {
        System.out.println("Updated slot");
    }

    public boolean deleteSlot(int SlotId) {
        System.out.println("Delete slot with " + SlotId);
        return true;
    }

    public FlipFitSlot getSlot(int slotId){
        // get the slot with slotId from the database
        return createSlot(1, LocalTime.now(), LocalTime.now().plus(Duration.ofMinutes(55)), 50, 200);
    }

    public boolean checkSlotAvailability(int slotId){
        System.out.println("Checked availability of " + slotId);
        return true;
    }
    public List<FlipFitSlot> searchByTime(String city, LocalTime time){
        List<FlipFitSlot> slots = new ArrayList<>();
        return slots;
    }
    public List<FlipFitSlot> searchByDate(String city, Date date){
        List<FlipFitSlot> slots = new ArrayList<>();
        return slots;
    }

    /*
     * Slot services end from here ------------------------->
     */
}

