package flipfit.flipkart.business;

import flipfit.flipkart.DAO.FlipFitAdminDAO;
import flipfit.flipkart.DAO.FlipFitGymOwnerDAO;
import flipfit.flipkart.DAO.FlipFitUserDAO;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class FlipFitAdminService {
    private FlipFitAdminDAO flipFitAdminDAO;
    public FlipFitAdminService() {
        flipFitAdminDAO = new FlipFitAdminDAO();
    }

    public FlipFitAdmin login(String email, String password) {
        FlipFitUser user = Helper.verifyCredentials(email, password);
        return user != null ? flipFitAdminDAO.getByUser(user) : null;
    }

    public void approveGymOwner(int userId) {
        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        FlipFitUser flipFitUser = flipFitUserDAO.getByUserId(userId);
//        flipFitUser.printAllDetails();
        if(flipFitUser == null) {
            System.out.println("User " + userId + " doesn't exist");
            return;
        }
        flipFitUserDAO.update(flipFitUser.getUsername(), flipFitUser.getPassword(), flipFitUser.getEmail(), flipFitUser.getName(), "whitelisted", flipFitUser.getRoleId(), flipFitUser.getUserId());
        System.out.println("Gym Owner request with User ID " + userId + " was approved");
    }

    public void rejectGymOwner(int userId){
        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        flipFitUserDAO.delete(userId);
        FlipFitGymOwnerDAO flipFitGymOwnerDAO = new FlipFitGymOwnerDAO();
        flipFitGymOwnerDAO.deleteByUserId(userId);
        System.out.println("Gym Owner request with User ID " + userId + " was rejected");
    }

    public boolean approveGym(FlipFitGym gym) {
        System.out.println("Gym approved"+gym);
        return true;
    }

    public boolean approveSlot(FlipFitSlot slot) {
        System.out.println("Slot approved"+slot);
        return true;
    }


    /*
     * Notification services begin from here --------------------->
     */

    public FlipFitNotification createNotification(String message, String email){
        // creating dummy notification --> needs to be changed
        FlipFitNotification notification = new FlipFitNotification(message, email);
        System.out.println("Notification with message " + message + "to email: " + email);
        return notification;

    }

    public FlipFitNotification getNotificationById(int notificationId){
        // creating dummy notification --> needs to be changed
        FlipFitNotification notification = this.createNotification("slot booked", "shv12@iitbbs.ac.in");
        System.out.println("Notification with id " + notificationId);
        return notification;
    }

    public boolean deleteNotification(int notificationId){
        System.out.println("Deleting notification with id " + notificationId);
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
