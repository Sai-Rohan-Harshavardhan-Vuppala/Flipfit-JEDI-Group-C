package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitBookingDAO;
import flipfit.flipkart.DAO.FlipFitGymDAO;
import flipfit.flipkart.DAO.FlipFitGymOwnerDAO;
import flipfit.flipkart.DAO.FlipFitSlotDAO;
import flipfit.flipkart.DAO.FlipFitUserDAO;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.helper.Helper;

import java.sql.Time;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FlipFitGymOwnerService {
    private FlipFitGymOwnerDAO flipFitGymOwnerDAO;
    private FlipFitGymDAO flipFitGymDAO;
    private FlipFitSlotDAO flipFitSlotDAO;

    public FlipFitGymOwnerService() {
        flipFitGymOwnerDAO = new FlipFitGymOwnerDAO();
        flipFitGymDAO = new FlipFitGymDAO();
        flipFitSlotDAO = new FlipFitSlotDAO();
    }

    public FlipFitGymOwner login(String email, String password){
        FlipFitUser user = Helper.verifyCredentials(email, password);
        return user != null ? flipFitGymOwnerDAO.getByUser(user) : null;
    }

    public List<FlipFitGymOwner> getPendingGymOwners() {
        return flipFitGymOwnerDAO.getByStatus("pending");
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

    public boolean createGym(String gymName, String gymCity, String gymArea, int gymOwnerId){
        FlipFitGymOwner gymOwner = flipFitGymOwnerDAO.get(gymOwnerId);
        if(gymOwner == null){
            System.out.println("Gym owner with gym owner ID " + gymOwnerId + " does not exist");
            return false;
        }
        else if(gymOwner.getStatus() == "pending"){
            System.out.println("Gym owner registration pending at admin");
            return false;
        }
        flipFitGymDAO.create(gymName, gymCity, gymArea, gymOwnerId);
        return true;
    }

    public void updateGym(){

    }

    public void deleteGym(){

    }

    public List<FlipFitGym> getGymByGymOwnerId(int gymOwnerId){
        return flipFitGymDAO.getGymByGymOwnerId(gymOwnerId);
    }
    /*
     * Slot services begin from here ----------------------->
     */

    public boolean createSlot(int gymId, String date, String startTime, String endTime, int seatsAvailable, double price) {

        FlipFitGym flipFitGym = flipFitGymDAO.get(gymId);
        if(flipFitGym == null){
            System.out.println("Gym not found");
            return false;
        }
        // Checking whether the entered slot intersects any of the existing slots
        List<FlipFitSlot> slots = flipFitSlotDAO.getByGymIdAndStatusAndDate(gymId, "approved", date);
        for(FlipFitSlot slot : slots){
            if(Helper.checkIfSlotsIntersect(slot.getStartTime(), slot.getEndTime(), Time.valueOf(startTime), Time.valueOf(endTime))){
                System.out.println("Slot cannot be added as it intersects with other slots in the same gym.");
                return false;
            }
        }
        if(flipFitSlotDAO.create(gymId, date, startTime, endTime, seatsAvailable, price, seatsAvailable, "pending")){
            System.out.println("Slot registration pending at admin");
            return true;
        }
        else{
            System.out.println("Invalid details. Please try again.");
            return false;
        }
    }


    public void updateSlot(int slotId, int gymId, String date, String startTime, String endTime, int seatsAvailable, double price, int totalSeats, String status) {
        FlipFitSlotDAO flipFitSlotDAO = new FlipFitSlotDAO();
        flipFitSlotDAO.update(slotId, gymId, date, startTime, endTime, seatsAvailable, price, status, totalSeats);
        System.out.println("Updated slot");
    }

    public boolean deleteSlot(int SlotId) {
        System.out.println("Delete slot with " + SlotId);
        return true;
    }

    public FlipFitSlot getSlot(int slotId){
        // get the slot with slotId from the database
        return flipFitSlotDAO.get(slotId);
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
        return flipFitGymDAO.getByStatus("pending");
    }

    public List<FlipFitGym> getApprovedGyms() {
        return flipFitGymDAO.getByStatus("approved");
    }
    public List<FlipFitGym> getAll() {
        return flipFitGymDAO.getAll();
    }
    public List<FlipFitBooking> getAllBookings() {
        return FlipFitBookingDAO.getAllBookings();
    }

    public List<FlipFitSlot> getPendingSlots() {
        return flipFitSlotDAO.getByStatus("pending");
    }

    public List<FlipFitSlot> getSlotsByGymId(int gymId){
        return flipFitSlotDAO.getByGymId(gymId);
    }

    public FlipFitGymOwner getGymOwnerBySlotId(int slotId) {
        FlipFitSlotDAO flipFitSlotDAO = new FlipFitSlotDAO();
        FlipFitSlot slot = flipFitSlotDAO.get(slotId);
        if(slot == null){
            System.out.println("Slot with id " + slotId + " does not exist");
            return null;
        }
        FlipFitGymDAO flipFitGymDAO = new FlipFitGymDAO();
        FlipFitGym gym = flipFitGymDAO.get(slot.getGymId());

        FlipFitGymOwnerDAO flipFitGymOwnerDAO = new FlipFitGymOwnerDAO();
        return flipFitGymOwnerDAO.get(gym.getGymOwnerId());
    }


    /*
     * Slot services end from here ------------------------->
     */

    public List<FlipFitGymOwner> getAllGymOwners() {
        return flipFitGymOwnerDAO.getAllGymOwners();
    }

}

