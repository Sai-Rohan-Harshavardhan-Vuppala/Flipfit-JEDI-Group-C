package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.business.FlipFitPaymentService;

import java.util.Scanner;
import java.util.List;

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

    private void showGyms(List<FlipFitGym> gyms) {
        System.out.printf("%n---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("Your Gyms%n");
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", "GYM ID", "GYM NAME", "GYM CITY", "GYM AREA", "GYM STATUS");
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        for(FlipFitGym gym: gyms){
            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", gym.getGymId(), gym.getGymName(), gym.getGymCity(), gym.getGymArea(), gym.getStatus());
        }
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
    }
    public boolean showMenu(){
        System.out.println("\n------------------------------\nWelcome to FlipFit Gym Owner Client");
        System.out.println("1. Add Gym");
        System.out.println("2. Update Gym");
        System.out.println("3. Delete Gym");
        System.out.println("4. View Gyms");
        System.out.println("5. Add Slot");
        System.out.println("6. Update Slot");
        System.out.println("7. Delete Slot");
        System.out.println("8. Logout");


        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                System.out.println("Enter Gym Name: ");
                String gymName = scanner.nextLine();

                System.out.println("Enter City: ");
                String gymCity = scanner.nextLine();

                System.out.println("Enter Area (Locality) in the city: ");
                String gymArea = scanner.nextLine();
                flipFitGymOwnerService.createGym(gymName, gymCity, gymArea, flipFitGymOwner.getGymOwnerId());
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
                System.out.println("Enter Gym Owner Id: ");
                int gymOwnerId = Integer.parseInt(scanner.nextLine());
                List<FlipFitGym> gyms = flipFitGymOwnerService.getGymByGymOwnerId(gymOwnerId);
                showGyms(gyms);
                break;
            case 5:
                System.out.println("Enter gymID: ");
                gymId = scanner.nextLine();

                System.out.println("Enter new slot: ");
                String slot = scanner.nextLine();

                System.out.println("Slot Added Successfully");
                break;
            case 6:
                System.out.println("Enter Gym Id: ");
                gymId = scanner.nextLine();

                System.out.println("Enter slotId: ");
                String slotId = scanner.nextLine();

                System.out.println("Enter updated slot: ");
                slot = scanner.nextLine();

                System.out.println("Slot updated Successfully");
                break;
            case 7:
                System.out.println("Enter slotId: ");
                slotId = scanner.nextLine();

                System.out.println("Slot deleted Successfully");
                break;
            case 8:
                System.out.println("Logout Successfully");
                return true;
        }
        return false;
    }
}
