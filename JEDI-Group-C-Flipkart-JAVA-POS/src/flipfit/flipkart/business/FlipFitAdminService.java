package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitNotification;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.bean.FlipFitUser;

public class FlipFitAdminService {
    public boolean login(String username, String password) {
        return true;
    }
    public void approveUser(FlipFitUser user) {
        System.out.println("User approved");
    }

    public boolean validateGym(FlipFitGym gym) {
        System.out.println("Gym approved"+gym);
        return true;
    }

    public boolean validateSlot(FlipFitSlot slot) {
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
