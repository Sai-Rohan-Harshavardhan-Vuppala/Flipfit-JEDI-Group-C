package flipfit.flipkart.business;

import flipfit.flipkart.DAO.*;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.exceptions.*;
import flipfit.flipkart.helper.Helper;

import static flipfit.flipkart.helper.Helper.printInGreen;
import static flipfit.flipkart.helper.Helper.printInRed;

public class FlipFitAdminService {
    private FlipFitAdminDAOImpl flipFitAdminDAO;
    public FlipFitAdminService() {
        flipFitAdminDAO = new FlipFitAdminDAOImpl();
    }

    public FlipFitAdmin login(String email, String password) throws IncorrectCredentialsException, IncorrectRoleSelectedException {
        FlipFitAdmin flipFitAdmin = null;
        FlipFitUser user = Helper.verifyCredentials(email, password);
        if(user == null){
            throw new IncorrectCredentialsException();
        }
        flipFitAdmin = flipFitAdminDAO.getByUser(user);
        if(user.getRoleId() != 1){
            throw new IncorrectRoleSelectedException();
        }
        return flipFitAdmin;
    }

    public void approveGymOwner(int userId) throws UserNotFoundException {
        FlipFitUserDAOInterface flipFitUserDAO = new FlipFitUserDAOImpl();
        FlipFitUser flipFitUser = flipFitUserDAO.getByUserId(userId);
//        flipFitUser.printAllDetails();
        if(flipFitUser == null) {
            throw new UserNotFoundException(userId);
        }
        flipFitUserDAO.update(flipFitUser.getUsername(), flipFitUser.getPassword(), flipFitUser.getEmail(), flipFitUser.getName(), "whitelisted", flipFitUser.getRoleId(), flipFitUser.getUserId());
        printInGreen("Gym Owner request with User ID " + userId + " was approved");


    }

    public void rejectGymOwner(int userId) throws UserNotFoundException {
        FlipFitUserDAOInterface flipFitUserDAO = new FlipFitUserDAOImpl();
        if(flipFitUserDAO.delete(userId) == false) {
            throw new UserNotFoundException(userId);
        }
        FlipFitGymOwnerDAOImpl flipFitGymOwnerDAOImpl = new FlipFitGymOwnerDAOImpl();
        flipFitGymOwnerDAOImpl.deleteByUserId(userId);
        printInGreen("Gym Owner request with User ID " + userId + " was rejected");
    }

    public void approveGym(int gymId) throws GymNotFoundException {
        FlipFitGymDAOImpl flipFitGymDAOImpl = new FlipFitGymDAOImpl();
        FlipFitGym gym = flipFitGymDAOImpl.get(gymId);
        if(flipFitGymDAOImpl.update(gym.getGymName(), gym.getGymCity(), gym.getGymArea(),"approved", gym.getGymOwnerId(), gym.getGymId())){
            printInGreen("Gym approved with Gym ID " + gymId + " was approved");
        }
        else{
            throw new GymNotFoundException(gymId);
        }

    }

    public void rejectGym(int gymId) throws GymNotFoundException {
        FlipFitGymDAOImpl flipFitGymDAOImpl = new FlipFitGymDAOImpl();
        if(flipFitGymDAOImpl.delete(gymId)){
            printInGreen("Gym with Gym ID" + gymId + " was rejected");
        }
        else{
            throw new GymNotFoundException(gymId);
        }

    }

    public void approveSlot(int slotId) throws SlotAlreadyApprovedException, SlotNotFoundException {
        FlipFitSlotDAOInterface flipFitSlotDAO = new FlipFitSlotDAOImpl();
        FlipFitSlot flipFitSlot = flipFitSlotDAO.get(slotId);
        if(flipFitSlot == null){
            throw new SlotNotFoundException(slotId);
        }
        else{
            if(flipFitSlot.getStatus() == "approved"){
                throw new SlotAlreadyApprovedException(slotId);
            }
            else{
                boolean result = flipFitSlotDAO.update(flipFitSlot.getSlotId(), flipFitSlot.getGymId(), flipFitSlot.getSlotDate().toString(), flipFitSlot.getStartTime().toString(), flipFitSlot.getEndTime().toString(), flipFitSlot.getSeatsAvailable(), flipFitSlot.getPrice(), "approved", flipFitSlot.getTotalSeats());
                printInGreen("Slot " + slotId + " was approved");
            }
        }
    }


    public void rejectSlot(int rejectedSlotId) throws SlotNotFoundException{
        FlipFitSlotDAOInterface flipFitSlotDAO = new FlipFitSlotDAOImpl();
        if(flipFitSlotDAO.delete(rejectedSlotId)){
            printInGreen("Slot " + rejectedSlotId + " was rejected");
        }
        else{
            throw new SlotNotFoundException(rejectedSlotId);
        }
    }

    /*
     * Notification services begin from here --------------------->
     */

    public FlipFitNotification createNotification(String message, String email){
        // creating dummy notification --> needs to be changed
        FlipFitNotification notification = new FlipFitNotification(message, email);
        printInGreen("Notification with message " + message + "to email: " + email);
        return notification;

    }

    public FlipFitNotification getNotificationById(int notificationId){
        // creating dummy notification --> needs to be changed
        FlipFitNotification notification = this.createNotification("slot booked", "shv12@iitbbs.ac.in");
        printInGreen("Notification with id " + notificationId);
        return notification;
    }

    public boolean deleteNotification(int notificationId){
        printInGreen("Deleting notification with id " + notificationId);
        return true;
    }

    public void notifyWaitListedCandidates(){
        // get the list of waitlisted bookings using BookingService

        // confirm the first wailisted booking

        // iterate through the bookings and change their rank by -1 and call createNotification
    }


    /*
     * Notification services end here -------------------------->
     */
}
