package flipfit.flipkart.helper;

import flipfit.flipkart.DAO.FlipFitUserDAOImpl;
import flipfit.flipkart.DAO.FlipFitUserDAOInterface;
import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.bean.FlipFitUser;
import flipfit.flipkart.exceptions.EmailAlreadyExistsException;
import flipfit.flipkart.exceptions.EmailNotFoundException;

import java.sql.Time;
import java.util.List;

import static flipfit.flipkart.constant.Constant.*;

public class Helper {
    public static boolean checkIfUserWithEmailExists(String email) throws EmailAlreadyExistsException {
        FlipFitUserDAOInterface flipFitUserDAO = new FlipFitUserDAOImpl();
        if(flipFitUserDAO.getByEmail(email) != null){
            throw new EmailAlreadyExistsException(email);
        }
        printInGreen("User with email " + email + " does not exist");
        return false;
    }

    public static FlipFitUser verifyCredentials(String email, String enteredPassword) {
        FlipFitUserDAOInterface flipFitUserDAO = new FlipFitUserDAOImpl();
        FlipFitUser flipFitUser = flipFitUserDAO.getByEmail(email);
        if(flipFitUser == null){
            printInRed("Invalid email");
            return null;
        }
        String flipFitUserStatus = flipFitUser.getStatus();
//        System.out.println(flipFitUserStatus);
        if(flipFitUserStatus.equals("pending")){
            printInRed("Registration pending at admin");
            return null;
        }
        if(flipFitUserStatus.equals("blacklisted")){
            printInRed("Your account has been blacklisted by admin");
            return null;
        }
        if(enteredPassword.equals(flipFitUser.getPassword()) == false){
            printInRed("Invalid password");
//            System.out.println("Original password: " + flipFitUser.getPassword());
            return null;
        }
        printInGreen("Credentials verified");
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
        printInYellow(title);
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
        printInYellow(title);
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", "GYM ID", "GYM OWNER ID", "GYM NAME", "GYM CITY", "GYM AREA");
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        for(FlipFitGym gym: gyms){
            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", gym.getGymId(), gym.getGymOwnerId(), gym.getGymName(), gym.getGymCity(), gym.getGymArea());
        }
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
    }

    public static void printInRed(String text){
        System.out.print(RED + text + RESET);
    }

    public static void printInGreen(String text){
        System.out.println(GREEN + text + RESET);
    }

    public static void printInYellow(String text){
        System.out.println(YELLOW + text + RESET );
    }
}
