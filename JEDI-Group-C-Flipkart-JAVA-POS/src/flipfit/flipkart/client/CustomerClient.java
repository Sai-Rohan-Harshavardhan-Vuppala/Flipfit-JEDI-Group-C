package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.business.FlipFitPaymentService;

import java.util.Scanner;

public class CustomerClient {

    private FlipFitCustomer flipFitCustomer;
    private FlipFitAdminService flipFitAdminService;
    private FlipFitGymOwnerService flipFitGymOwnerService;
    private FlipFitCustomerService flipFitCustomerService;
    private FlipFitPaymentService flipFitPaymentService;

    public CustomerClient(FlipFitGymOwnerService flipFitGymOwnerService, FlipFitCustomerService flipFitCustomerService, FlipFitAdminService flipFitAdminService, FlipFitPaymentService flipFitPaymentService, FlipFitCustomer flipFitCustomer) {
        this.flipFitGymOwnerService = flipFitGymOwnerService;
        this.flipFitCustomerService = flipFitCustomerService;
        this.flipFitAdminService = flipFitAdminService;
        this.flipFitPaymentService = flipFitPaymentService;
        this.flipFitCustomer = flipFitCustomer;
    }


    public boolean showMenu(){
        System.out.println("\n------------------------------\nWelcome to FlipFit Customer Client");

        System.out.println("1. View Gyms");
        System.out.println("2. View available slots");
        System.out.println("3. Book slot");
        System.out.println("4. View all booked slots");
        System.out.println("5. Update booked slot");
        System.out.println("6. Cancel booked slot");
        System.out.println("7. Logout");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                System.out.println("Enter city: ");
                String city = scanner.nextLine();
                System.out.println("Displaying all available gyms in " + city);
                break;
            case 2:
                System.out.println("Enter gymId: ");
                String gymId = scanner.nextLine();
                System.out.println("Displaying dates on which slots are available");
                System.out.println("Enter date: ");
                String date = scanner.nextLine();
                System.out.println("Displaying all available slots");
                break;
            case 3:
                System.out.println("Enter slotId: ");
                int slotId = Integer.parseInt(scanner.nextLine());
                FlipFitSlot flipFitSlot = flipFitGymOwnerService.getSlot(slotId);
                System.out.println("Pay " + flipFitSlot.getPrice());
                System.out.println("Enter the transactionId: ");
                String transactionId = scanner.nextLine();
                flipFitCustomerService.createBooking(flipFitCustomer.getCustomerId(), slotId, transactionId);
                break;
            case 4:
                System.out.println("Displaying all booked slots");
                break;
            case 5:
                System.out.println("Enter bookingId: ");
                int bookingId = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter slotId: ");
                int updatedSlotId = Integer.parseInt(scanner.nextLine());
                FlipFitSlot updatedSlot = flipFitGymOwnerService.getSlot(updatedSlotId);
                System.out.println("Pay " + updatedSlot.getPrice());
                System.out.println("Enter the transactionId: ");
                String newTransactionId = scanner.nextLine();
                flipFitCustomerService.createBooking(flipFitCustomer.getCustomerId(), updatedSlotId, newTransactionId);
            case 6:
                System.out.println("Enter bookingId: ");
                int inputBookingId = Integer.parseInt(scanner.nextLine());
                System.out.println("Booking cancelled successfully");
                break;

            case 7:
                System.out.println("Logged out successfully");
                return true;
        }
        return false;
    }
}
