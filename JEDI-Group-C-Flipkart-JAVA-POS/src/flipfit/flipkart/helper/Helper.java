package flipfit.flipkart.helper;

import flipfit.flipkart.DAO.FlipFitUserDAO;
import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.bean.FlipFitUser;

import java.sql.Time;
import java.util.List;

public class Helper {
    public static boolean checkIfUserWithEmailExists(String email){
        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        if(flipFitUserDAO.getByEmail(email) != null){
            System.out.println("User with email " + email + " already exists");
            return true;
        }
        System.out.println("User with email " + email + " does not exist");
        return false;
    }

    public static FlipFitUser verifyCredentials(String email, String enteredPassword){
        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        FlipFitUser flipFitUser = flipFitUserDAO.getByEmail(email);
        if(flipFitUser == null){
            System.out.println("Invalid email");
            return null;
        }
        String flipFitUserStatus = flipFitUser.getStatus();
//        System.out.println(flipFitUserStatus);
        if(flipFitUserStatus.equals("pending")){
            System.out.println("Registration pending at admin");
            return null;
        }
        if(flipFitUserStatus.equals("blacklisted")){
            System.out.println("Your account has been blacklisted by admin");
            return null;
        }
        if(enteredPassword.equals(flipFitUser.getPassword()) == false){
            System.out.println("Invalid password");
//            System.out.println("Original password: " + flipFitUser.getPassword());
            return null;
        }
        System.out.println("Credentials verified");
        return flipFitUser;
    }

    public static boolean checkIfSlotsIntersect(Time startTime1, Time endTime1, Time startTime2, Time endTime2){
        if(startTime1.compareTo(startTime2) != -1){
            return checkIfSlotsIntersect(startTime2, endTime2, startTime1, endTime1);
        }
        if(endTime1.compareTo(startTime2) == 1){
            return true;
        }
        return false;
    }

    public static void showSlots(List<FlipFitSlot> slots, String title) {
        System.out.printf("%n-------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf(title + "%n");
        System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-15s | %-15s | %-10s |%n", "SLOT ID", "GYM ID", "DATE", "START TIME", "END TIME", "SEATS AVAILABLE", "TOTAL SEATS", "PRICE");
        System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
        for(FlipFitSlot slot: slots){
            System.out.printf("| %-10s | %-10s | %-10s | %-10s | %-10s | %-15s | %-15s | %-10s |%n", slot.getSlotId(), slot.getGymId(), slot.getSlotDate().toString(), slot.getStartTime().toString(), slot.getEndTime().toString(), slot.getSeatsAvailable(), slot.getTotalSeats(), slot.getPrice());
        }
        System.out.printf("-------------------------------------------------------------------------------------------------------------------%n");
    }

    public static void showGyms(List<FlipFitGym> gyms, String title){
        System.out.printf("%n---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf(title + "%n");
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", "GYM ID", "GYM OWNER ID", "GYM NAME", "GYM CITY", "GYM AREA");
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        for(FlipFitGym gym: gyms){
            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", gym.getGymId(), gym.getGymOwnerId(), gym.getGymName(), gym.getGymCity(), gym.getGymArea());
        }
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
    }
}
