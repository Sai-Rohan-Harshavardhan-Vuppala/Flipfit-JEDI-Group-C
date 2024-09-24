package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.business.FlipFitPaymentService;
import flipfit.flipkart.exceptions.GymOwnerNotFoundException;
import flipfit.flipkart.exceptions.RegistrationPendingAtAdmin;
import flipfit.flipkart.exceptions.SlotIntersectException;
import flipfit.flipkart.helper.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static flipfit.flipkart.helper.Helper.printInRed;
import static flipfit.flipkart.helper.Helper.printInYellow;

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
            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", gym.getGymId(), gym.getGymName(), gym.getGymCity(), gym.getGymArea(), gym.getGymStatus());
        }
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
    }
    public boolean showMenu(){
        printInYellow("\n------------------------------\nWelcome to FlipFit Gym Owner Client");
        System.out.println("1. Add Gym");
//        System.out.println("2. Update Gym");
//        System.out.println("3. Delete Gym");
        System.out.println("2. Add Slot");
//        System.out.println("5. Update Slot");
//        System.out.println("6. Delete Slot");
        System.out.println("3. View all gyms");
        System.out.println("4. View slots in a gym");
        System.out.println("5. View slot by slot ID");
        System.out.println("6. Logout");


        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                try {
                    System.out.println("Enter Gym Name: ");
                    String gymName = scanner.nextLine();

                    System.out.println("Enter City: ");
                    String gymCity = scanner.nextLine();

                    System.out.println("Enter Area (Locality) in the city: ");
                    String gymArea = scanner.nextLine();
                    if (flipFitGymOwnerService.createGym(gymName, gymCity, gymArea, flipFitGymOwner.getGymOwnerId()) == true) {
                        System.out.println("Gym added successfully!");
                    } else {
                        System.out.println("Gym addition failed!");
                    }
                }
                catch(GymOwnerNotFoundException e){
                    printInRed(e.getMessage());
                }
                catch(RegistrationPendingAtAdmin e){
                    printInRed(e.getMessage());
                }
                break;
//            case 2:
//                System.out.println("Enter Gym Id: ");
//                String gymId = scanner.nextLine();
//
//                System.out.println("Enter Updated Gym Name: ");
//                String updatedGym = scanner.nextLine();
//
//                System.out.println("Gym updated Successfully");
//                break;
//            case 3:
//                System.out.println("Enter Gym Id: ");
//                gymId = scanner.nextLine();
//
//                System.out.println("Gym deleted Successfully");
//                break;
            case 2:
                try {
                    System.out.println("Enter gymID: ");
                    int enteredGymId = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter Date (YYYY-MM-DD): ");
                    String enteredDate = scanner.nextLine();

                    System.out.println("Enter start time (HH:MM:SS) in 24-hour format: ");
                    String startTime = scanner.nextLine();

                    System.out.println("Enter end time (HH:MM:SS) in 24-hour format: ");
                    String endTime = scanner.nextLine();

                    System.out.println("Enter the number of seats available: ");
                    int seatsAvailable = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter the price: ");
                    double price = Double.parseDouble(scanner.nextLine());

                    if (flipFitGymOwnerService.createSlot(enteredGymId, enteredDate, startTime, endTime, seatsAvailable, price) == true) {
                        System.out.println("Slot Added Successfully");
                    } else {
                        System.out.println("Slot addition failed");
                    }
                }
                catch(SlotIntersectException e){
                    printInRed(e.getMessage());
                }
                break;
//            case 5:
//                System.out.println("Enter Gym Id: ");
//                gymId = scanner.nextLine();
//
//                System.out.println("Enter slotId: ");
//                String slotId = scanner.nextLine();
//
//                System.out.println("Slot updated Successfully");
//                break;
//            case 6:
//                System.out.println("Enter slotId: ");
//                slotId = scanner.nextLine();
//
//                System.out.println("Slot deleted Successfully");
//                break;
            case 3:
                List<FlipFitGym> gyms = flipFitGymOwnerService.getGymByGymOwnerId(flipFitGymOwner.getGymOwnerId());
                showGyms(gyms);
                break;
            case 4:
                System.out.println("Enter Gym ID: ");
                int viewSlotGymId = Integer.parseInt(scanner.nextLine());
                List<FlipFitSlot> slots = flipFitGymOwnerService.getSlotsByGymId(viewSlotGymId);
                Helper.showSlots(slots, "Slots in gym with gymID " + viewSlotGymId);
                break;
            case 5:
                System.out.println("Enter slot ID: ");
                int viewSlotId = Integer.parseInt(scanner.nextLine());
                List<FlipFitSlot> viewSlots = new ArrayList<FlipFitSlot>();
                FlipFitSlot slot = flipFitGymOwnerService.getSlot(viewSlotId);
                if(slot == null || slot.getStatus().equals("pending")){
                    System.out.println("Invalid slot ID");
                }
                else{
                    viewSlots.add(slot);
                    Helper.showSlots(viewSlots, "Slot details of slot with slot ID " + viewSlotId);
                }
            case 6:
                System.out.println("Logout Successfully");
                return true;
        }
        return false;
    }
}
