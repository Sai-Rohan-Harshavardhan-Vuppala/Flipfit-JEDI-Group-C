package flipfit.flipkart.business;

import flipfit.flipkart.bean.Notification;

public class FlipFitNotificationService {
    public Notification createNotification(String message, String email){
        // creating dummy notification --> needs to be changed
        Notification notification = new Notification(message, email);
        System.out.println("Notification with message " + message + "to email: " + email);
        return notification;

    }

    public Notification getNotificationById(int notificationId){
        // creating dummy notification --> needs to be changed
        Notification notification = this.createNotification("slot booked", "shv12@iitbbs.ac.in");
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
}
