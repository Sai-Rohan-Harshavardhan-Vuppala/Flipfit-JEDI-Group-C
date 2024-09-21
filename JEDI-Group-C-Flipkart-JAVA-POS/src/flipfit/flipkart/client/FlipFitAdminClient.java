package flipfit.flipkart.client;

import com.mysql.cj.conf.ConnectionUrlParser;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.business.FlipFitPaymentService;


import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;

public class FlipFitAdminClient {
    private FlipFitAdminService flipFitAdminService;
    private FlipFitGymOwnerService flipFitGymOwnerService;
    private FlipFitCustomerService flipFitCustomerService;
    private FlipFitPaymentService flipFitPaymentService;
    private FlipFitAdmin flipFitAdmin;

    public FlipFitAdminClient(FlipFitGymOwnerService flipFitGymOwnerService, FlipFitCustomerService flipFitCustomerService, FlipFitAdminService flipFitAdminService, FlipFitPaymentService flipFitPaymentService, FlipFitAdmin flipFitAdmin) {
        this.flipFitGymOwnerService = flipFitGymOwnerService;
        this.flipFitCustomerService = flipFitCustomerService;
        this.flipFitAdminService = flipFitAdminService;
        this.flipFitPaymentService = flipFitPaymentService;
        this.flipFitAdmin = flipFitAdmin;
    }

    public static void approveUser() {
        System.out.println("Approved User details");
    }
    public static void validateGym(){
        System.out.println("Gym validated");

    }
    public static void validateSlot(){
        System.out.println("Slot validated");

    }

    public void showPendingGymOwners(List<FlipFitGymOwner>pendingGymOwners){
        System.out.printf("%n---------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("Pending Gym Owners%n");
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-15s | %-20s |%n", "USER ID", "USERNAME", "EMAIL-ID", "FULL NAME", "GYM OWNER ID", "ACCOUNT NUMBER");
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------%n");
        for(FlipFitGymOwner gymOwner: pendingGymOwners){
            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-15s | %-20s |%n", gymOwner.getUserId(), gymOwner.getUsername(), gymOwner.getEmail(), gymOwner.getName(), gymOwner.getGymOwnerId(), gymOwner.getAccountNumber());
        }
        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------%n");
    }

    public boolean showMenu(){
        System.out.println("\n------------------------------\nWelcome to FlipFit Admin Client");
        System.out.println("1. View pending registrations");
        System.out.println("2. Approve Gym Owner");
        System.out.println("3. Reject Gym Owner");
        System.out.println("4. View pending gym requests");
        System.out.println("5. View pending slot requests");
        System.out.println("6. Approve Gym");
        System.out.println("7. Approve Slot");
        System.out.println("8. View all customers");
        System.out.println("9. View all gymOwners");
        System.out.println("10. View bookings");
        System.out.println("11. Logout");

        Scanner sc = new Scanner(in);


        int choice = Integer.parseInt(sc.nextLine());

        switch(choice){
            case 1:
                List<FlipFitGymOwner> pendingGymOwnerList = flipFitGymOwnerService.getPendingGymOwners();
                showPendingGymOwners(pendingGymOwnerList);
                break;
            case 2:
                System.out.println("Enter User ID: ");
                int approvedUserId = Integer.parseInt(sc.nextLine());
                flipFitAdminService.approveGymOwner(approvedUserId);
                break;
            case 3:
                System.out.println("Enter User ID: ");
                int rejectedUserId = Integer.parseInt(sc.nextLine());
                flipFitAdminService.rejectGymOwner(rejectedUserId);
                break;
            case 4:
                System.out.println("Pending gym requests displayed");
                break;
            case 5:
                System.out.println("Pending slot requests displayed");
                break;
            case 6:
                System.out.println("Enter gymId: ");
                int gymId = Integer.parseInt(sc.nextLine());
                System.out.println("Approve gym\n1. Yes\n2. No");
                int gymApprovalChoice = Integer.parseInt(sc.nextLine());
                switch(gymApprovalChoice){
                    case 1:
                        System.out.println("Request for GymId " + gymId + " approved");
                        break;
                    case 2:
                        System.out.println("Request for Slot " + gymId + " rejected");
                }
                validateGym();
                break;

            case 7:
                System.out.println("Enter slotId: ");
                int slotId = Integer.parseInt(sc.nextLine());
                System.out.println("Approve slot\n1. Yes\n2. No");
                int slotApprovalChoice = Integer.parseInt(sc.nextLine());
                switch(slotApprovalChoice){
                    case 1:
                        System.out.println("Request for GymId " + slotId + " approved");
                        break;
                    case 2:
                        System.out.println("Request for Slot " + slotId + " rejected");
                }
                validateSlot();
                break;
            case 8:
                System.out.println("List of customers displayed");
                break;
            case 9:
                System.out.println("List of gym owners displayed");
                break;
            case 10:
                System.out.println("List of bookings displayed");
                break;
            case 11:
                System.out.println("Logged out successfully");
                return true;
        }
        return false;
    }
}
