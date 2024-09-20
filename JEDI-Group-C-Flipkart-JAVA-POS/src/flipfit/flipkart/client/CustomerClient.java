package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;

import java.util.Scanner;

public class CustomerClient {

    private FlipFitCustomer flipFitCustomer;

    public CustomerClient(FlipFitCustomer flipFitCustomer) {
        this.flipFitCustomer = flipFitCustomer;
    }


    public boolean showMenu(){
        System.out.println("\n------------------------------\nWelcome to FlipFit Customer Client");
        System.out.println("1. Book slot");
        System.out.println("2. View all booked slots");
        System.out.println("3. Cancel booked slot");
        System.out.println("4. Logout");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                System.out.println("Enter slotId: ");
                int slotId = Integer.parseInt(scanner.nextLine());
                FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerService();
                FlipFitSlot flipFitSlot = flipFitGymOwnerService.getSlot(slotId);
                System.out.println("Pay " + flipFitSlot.getPrice());
                System.out.println("Enter the transactionId: ");
                String transactionId = scanner.nextLine();
                FlipFitCustomerService flipFitCustomerService = new FlipFitCustomerService();
                flipFitCustomerService.createBooking(flipFitCustomer.getCustomerId(), slotId, transactionId);
                break;
            case 2:
                System.out.println("Displaying all booked slots");
                break;

            case 3:
                System.out.println("Enter slotId: ");
                int inputSlotId = Integer.parseInt(scanner.nextLine());
                System.out.println("Slot cancelled successfully");
                break;

            case 4:
                System.out.println("Logged out successfully");
                return true;
        }
        return false;
    }
}
