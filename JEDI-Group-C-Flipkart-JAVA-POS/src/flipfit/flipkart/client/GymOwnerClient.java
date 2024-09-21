package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.business.FlipFitPaymentService;

import java.util.Scanner;

public class GymOwnerClient {
    private FlipFitAdminService flipFitAdminService;
    private FlipFitGymOwnerService flipFitGymOwnerService;
    private FlipFitCustomerService flipFitCustomerService;
    private FlipFitPaymentService flipFitPaymentService;
    private FlipFitGymOwner flipFitGymOwner;

    public GymOwnerClient(FlipFitGymOwnerService flipFitGymOwnerService, FlipFitCustomerService flipFitCustomerService, FlipFitAdminService flipFitAdminService, FlipFitPaymentService flipFitPaymentService, FlipFitGymOwner flipFitGymOwner) {
        this.flipFitGymOwnerService = flipFitGymOwnerService;
        this.flipFitCustomerService = flipFitCustomerService;
        this.flipFitAdminService = flipFitAdminService;
        this.flipFitPaymentService = flipFitPaymentService;
        this.flipFitGymOwner = flipFitGymOwner;
    }


    public boolean showMenu(){
        System.out.println("\n------------------------------\nWelcome to FlipFit Gym Owner Client");
        System.out.println("1. Add Gym");
        System.out.println("2. Update Gym");
        System.out.println("3. Delete Gym");
        System.out.println("4. Add Slot");
        System.out.println("5. Update Slot");
        System.out.println("6. Delete Slot");
        System.out.println("7. Logout");


        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                System.out.println("Enter Gym Name: ");
                String gymName = scanner.nextLine();

                System.out.println("Enter City: ");
                String city = scanner.nextLine();

                System.out.println("Gym Created Successfully");
                break;
            case 2:
                System.out.println("Enter Gym Id: ");
                String gymId = scanner.nextLine();

                System.out.println("Enter Updated Gym Name: ");
                String updatedGym = scanner.nextLine();

                System.out.println("Gym updated Successfully");
                break;
            case 3:
                System.out.println("Enter Gym Id: ");
                gymId = scanner.nextLine();

                System.out.println("Gym deleted Successfully");
                break;
            case 4:
                System.out.println("Enter gymID: ");
                gymId = scanner.nextLine();

                System.out.println("Enter new slot: ");
                String slot = scanner.nextLine();

                System.out.println("Slot Added Successfully");
                break;
            case 5:
                System.out.println("Enter Gym Id: ");
                gymId = scanner.nextLine();

                System.out.println("Enter slotId: ");
                String slotId = scanner.nextLine();

                System.out.println("Enter updated slot: ");
                slot = scanner.nextLine();

                System.out.println("Slot updated Successfully");
                break;
            case 6:
                System.out.println("Enter slotId: ");
                slotId = scanner.nextLine();

                System.out.println("Slot deleted Successfully");
                break;
            case 7:
                System.out.println("Logout Successfully");
                return true;
        }
        return false;
    }
}
