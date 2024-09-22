package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitGymDAO;
import flipfit.flipkart.DAO.FlipFitGymOwnerDAO;
import flipfit.flipkart.DAO.FlipFitUserDAO;
import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.bean.FlipFitUser;
import flipfit.flipkart.helper.Helper;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FlipFitGymOwnerService {
    private FlipFitGymOwnerDAO flipFitGymOwnerDAO;
    private FlipFitGymDAO flipFitGymDAO;

    public FlipFitGymOwnerService() {
        flipFitGymOwnerDAO = new FlipFitGymOwnerDAO();
        flipFitGymDAO = new FlipFitGymDAO();
    }

    public FlipFitGymOwner login(String email, String password){
        FlipFitUser user = Helper.verifyCredentials(email, password);
        return user != null ? flipFitGymOwnerDAO.getByUser(user) : null;
    }

    public List<FlipFitGymOwner> getPendingGymOwners() {
        return flipFitGymOwnerDAO.getPendingGymOwners();
    }

    public FlipFitGymOwner createGymOwner(String username, String password, String email, String name, String accountNumber){
        if(Helper.checkIfUserWithEmailExists(email)){
            System.out.println("User with email already exists");
            return null;
        }
        System.out.println("Creating gym owner");
        /*
        validate other details
         */

        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        flipFitUserDAO.create(username, password, email, name, 3, "pending");
        FlipFitUser flipFitUser = flipFitUserDAO.getByEmail(email);
        int userId = flipFitUser.getUserId();
        flipFitGymOwnerDAO.create(userId, accountNumber);
        FlipFitGymOwner flipFitGymOwner = flipFitGymOwnerDAO.getByUser(flipFitUser);
        System.out.println("Your registration request is pending at admin");
        return flipFitGymOwner;
    }

    public void createGym(String gymName, String gymCity, String gymArea, int gymOwnerId){
        flipFitGymDAO.create(gymName, gymCity, gymArea, gymOwnerId);
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

    public List<FlipFitGym> getPendingGyms() {
        return flipFitGymDAO.getPendingGyms();
    }

    /*
     * Slot services end from here ------------------------->
     */

    public List<FlipFitGymOwner> getAllGymOwners() {
        return flipFitGymOwnerDAO.getAllGymOwners();
    }

}

