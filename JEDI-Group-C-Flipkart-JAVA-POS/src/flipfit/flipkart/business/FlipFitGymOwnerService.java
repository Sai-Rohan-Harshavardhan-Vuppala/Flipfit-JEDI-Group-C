package flipfit.flipkart.business;

import flipfit.flipkart.DAO.*;
import flipfit.flipkart.DAO.FlipFitSlotDAOInterface;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.exceptions.*;
import flipfit.flipkart.helper.Helper;

import java.sql.Time;
import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static flipfit.flipkart.helper.Helper.printInGreen;


public class FlipFitGymOwnerService {
    private FlipFitGymOwnerDAOImpl flipFitGymOwnerDAOImpl;
    private FlipFitGymDAOImpl flipFitGymDAOImpl;
    private FlipFitSlotDAOInterface flipFitSlotDAO;

    public FlipFitGymOwnerService() {
        flipFitGymOwnerDAOImpl = new FlipFitGymOwnerDAOImpl();
        flipFitGymDAOImpl = new FlipFitGymDAOImpl();
        flipFitSlotDAO = new FlipFitSlotDAOImpl();
    }

    public FlipFitGymOwner login(String email, String password) throws IncorrectCredentialsException {
        FlipFitUser user = Helper.verifyCredentials(email, password);
        if(user == null) {
            throw new IncorrectCredentialsException();
        }
        return flipFitGymOwnerDAOImpl.getByUser(user);
    }

    public List<FlipFitGymOwner> getPendingGymOwners() {
        return flipFitGymOwnerDAOImpl.getByStatus("pending");
    }

    public FlipFitGymOwner createGymOwner(String username, String password, String email, String name, String accountNumber) throws EmailAlreadyExistsException {
        Helper.checkIfUserWithEmailExists(email);
        printInGreen("Creating gym owner");
        /*
        validate other details
         */

        FlipFitUserDAOInterface flipFitUserDAO = new FlipFitUserDAOImpl();
        flipFitUserDAO.create(username, password, email, name, 3, "pending");
        FlipFitUser flipFitUser = flipFitUserDAO.getByEmail(email);
        int userId = flipFitUser.getUserId();
        flipFitGymOwnerDAOImpl.create(userId, accountNumber);
        FlipFitGymOwner flipFitGymOwner = flipFitGymOwnerDAOImpl.getByUser(flipFitUser);
        printInGreen("Your registration request is pending at admin");
        return flipFitGymOwner;
    }

    public boolean createGym(String gymName, String gymCity, String gymArea, int gymOwnerId) throws GymOwnerNotFoundException, RegistrationPendingAtAdmin {
        FlipFitGymOwner gymOwner = flipFitGymOwnerDAOImpl.get(gymOwnerId);
        if(gymOwner == null){
            throw new GymOwnerNotFoundException(gymOwnerId);
        }
        else if(gymOwner.getStatus() == "pending"){
            throw new RegistrationPendingAtAdmin("gymOwner");
        }
        flipFitGymDAOImpl.create(gymName, gymCity, gymArea, gymOwnerId);
        return true;
    }

    public void updateGym(){

    }

    public void deleteGym(){

    }

    public List<FlipFitGym> getGymByGymOwnerId(int gymOwnerId){
        return flipFitGymDAOImpl.getGymByGymOwnerId(gymOwnerId);
    }
    /*
     * Slot services begin from here ----------------------->
     */

    public boolean createSlot(int gymId, String date, String startTime, String endTime, int seatsAvailable, double price) throws SlotIntersectException {

        FlipFitGym flipFitGym = flipFitGymDAOImpl.get(gymId);
        if(flipFitGym == null){
            System.out.println("Gym not found");
            return false;
        }
        // Checking whether the entered slot intersects any of the existing slots
        List<FlipFitSlot> slots = flipFitSlotDAO.getByGymIdAndStatusAndDate(gymId, "approved", date);
        for(FlipFitSlot slot : slots){
            if(Helper.checkIfSlotsIntersect(slot.getStartTime(), slot.getEndTime(), Time.valueOf(startTime), Time.valueOf(endTime))){
                throw new SlotIntersectException();
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
        FlipFitSlotDAOInterface flipFitSlotDAO = new FlipFitSlotDAOImpl();
        flipFitSlotDAO.update(slotId, gymId, date, startTime, endTime, seatsAvailable, price, status, totalSeats);
        printInGreen("Updated slot");
    }

    public boolean deleteSlot(int SlotId) {

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
        return flipFitGymDAOImpl.getByStatus("pending");
    }

    public List<FlipFitGym> getApprovedGyms() {
        return flipFitGymDAOImpl.getByStatus("approved");
    }
    public List<FlipFitGym> getAll() {
        return flipFitGymDAOImpl.getAll();
    }
    public List<FlipFitBooking> getAllBookings() {
        FlipFitBookingDAOImpl flipFitBookingDAOImpl = new FlipFitBookingDAOImpl();
        return flipFitBookingDAOImpl.getAllBookings();
    }

    public List<FlipFitSlot> getPendingSlots() {
        return flipFitSlotDAO.getByStatus("pending");
    }

    public List<FlipFitSlot> getSlotsByGymId(int gymId){
        return flipFitSlotDAO.getByGymId(gymId);
    }

    public FlipFitGymOwner getGymOwnerBySlotId(int slotId) throws SlotNotFoundException{
        FlipFitSlotDAOInterface flipFitSlotDAO = new FlipFitSlotDAOImpl();
        FlipFitSlot slot = flipFitSlotDAO.get(slotId);
        if(slot == null){
            throw new SlotNotFoundException(slotId);
        }
        FlipFitGymDAOImpl flipFitGymDAOImpl = new FlipFitGymDAOImpl();
        FlipFitGym gym = flipFitGymDAOImpl.get(slot.getGymId());

        FlipFitGymOwnerDAOImpl flipFitGymOwnerDAOImpl = new FlipFitGymOwnerDAOImpl();
        return flipFitGymOwnerDAOImpl.get(gym.getGymOwnerId());
    }


    /*
     * Slot services end from here ------------------------->
     */

    public List<FlipFitGymOwner> getAllGymOwners() {
        return flipFitGymOwnerDAOImpl.getAllGymOwners();
    }

}

