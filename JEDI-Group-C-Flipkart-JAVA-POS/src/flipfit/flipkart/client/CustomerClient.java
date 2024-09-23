package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitBooking;
import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.business.FlipFitPaymentService;
import flipfit.flipkart.helper.Helper;
import flipfit.flipkart.utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    private void showAllGyms(List<FlipFitGym> allGyms) {
        System.out.printf("%n---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("All Gyms in selected City%n");
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", "GYM ID", "GYM OWNER ID", "GYM NAME", "GYM CITY", "GYM AREA");
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
        for(FlipFitGym gym: allGyms){
            System.out.printf("| %-10s | %-15s | %-30s | %-20s | %-20s |%n", gym.getGymId(), gym.getGymOwnerId(), gym.getGymName(), gym.getGymCity(), gym.getGymArea());
        }
        System.out.printf("---------------------------------------------------------------------------------------------------------------%n");
    }

    private void showCustomerBookings(List<FlipFitBooking> bookings) {
        System.out.printf("%n----------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("All Bookings%n");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------%n");
        System.out.printf("| %-12s | %-15s | %-30s | %-20s | %-25s |%n", "Customer ID", "Slot ID", "Booking Status", "Payment ID", "Booking Date");
        System.out.printf("----------------------------------------------------------------------------------------------------------------------%n");
        for(FlipFitBooking booking: bookings){
            System.out.printf("| %-12s | %-15s | %-30s | %-20s | %-25s |%n", booking.getCustomerId(), booking.getSlotId(), booking.getBookingStatus(), booking.getPaymentId(), booking.getCreatedAt());
        }
        System.out.printf("----------------------------------------------------------------------------------------------------------------------%n");
    }



    public boolean showMenu(){
        System.out.println("\n------------------------------\nWelcome to FlipFit Customer Client");

        System.out.println("1. View available slots");
        System.out.println("2. View all gyms");
        System.out.println("3. Book slot");
        System.out.println("4. View all booked slots");
//        System.out.println("5. Update booked slot");
        System.out.println("5. Cancel booked slot");
        System.out.println("6. Logout");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                List<FlipFitGym> approvedGyms = flipFitGymOwnerService.getApprovedGyms();
                if(approvedGyms.isEmpty()){
                    System.out.println("No cities available");
                    break;
                }
                List<String> cities = new ArrayList<>();
                for(FlipFitGym gym : approvedGyms){
                    cities.add(gym.getGymCity());
                }
                cities = cities.stream().collect(Collectors.toSet()).stream().toList();
                Util.showTable(cities, "Available cities");
                System.out.println("Enter City Index: ");

                int cityIndex = Integer.parseInt(scanner.nextLine());
                cityIndex--;
                if(cityIndex < 0 || cityIndex >= cities.size()){
                    System.out.println("Invalid City Index");
                    break;
                }
                List<FlipFitGym> filteredGyms = new ArrayList<>();
                List<String> areas = new ArrayList<>();
                for(FlipFitGym gym : approvedGyms){
                    if(gym.getGymCity().equals(cities.get(cityIndex))){
                        filteredGyms.add(gym);
                        areas.add(gym.getGymArea());
                    }
                }
                areas = areas.stream().collect(Collectors.toSet()).stream().toList();
                System.out.println("Number of approved gyms: " + approvedGyms.size());
                Util.showTable(areas, "Available areas");
                System.out.println("Enter Area Index: ");

                int areaIndex = Integer.parseInt(scanner.nextLine());
                areaIndex--;
                if(areaIndex < 0 || areaIndex >= areas.size()){
                    System.out.println("Invalid Area Index");
                }
                List<FlipFitGym> filteredGymsByArea = new ArrayList<>();
                for(FlipFitGym gym : filteredGyms){
                    if(gym.getGymArea() == areas.get(areaIndex)){
                        filteredGymsByArea.add(gym);
                    }
                }
                Helper.showGyms(filteredGymsByArea, "Available gyms");

                System.out.println("Enter Gym ID: ");
                int gymID = Integer.parseInt(scanner.nextLine());
                List<FlipFitSlot> slots = flipFitGymOwnerService.getSlotsByGymId(gymID);
                Helper.showSlots(slots, "Available slots");
                break;
            case 2:
                System.out.println("Enter city: ");
                String city = scanner.nextLine();
                List<FlipFitGym> allGyms = flipFitGymOwnerService.getAll();
                showAllGyms(allGyms);
                break;
            case 3:
                System.out.println("Enter slotId: ");
                int slotId = Integer.parseInt(scanner.nextLine());
                FlipFitSlot flipFitSlot = flipFitGymOwnerService.getSlot(slotId);
                if(flipFitSlot == null){
                    System.out.println("Slot with slot ID " + slotId + " does not exist");
                }
                FlipFitGymOwner gymOwner = flipFitGymOwnerService.getGymOwnerBySlotId(slotId);
                System.out.println("Pay " + flipFitSlot.getPrice() + " to account number " + gymOwner.getAccountNumber());
                System.out.println("Enter the transactionId: ");
                String transactionId = scanner.nextLine();
                if(flipFitCustomerService.createBooking(flipFitCustomer.getCustomerId(), slotId, transactionId)){
                    System.out.println("Booking successfully created");
                }
                else{
                    System.out.println("Booking failed");
                }
                break;
            case 4:
                System.out.println("Displaying all booked slots");
                List<FlipFitBooking> bookings = flipFitCustomerService.getCustomerBookings(flipFitCustomer.getCustomerId());
                showCustomerBookings(bookings);
                break;
//            case 5:
//                System.out.println("Enter bookingId: ");
//                int bookingId = Integer.parseInt(scanner.nextLine());
//                System.out.println("Enter slotId: ");
//                int updatedSlotId = Integer.parseInt(scanner.nextLine());
////                FlipFitSlot updatedSlot = flipFitGymOwnerService.getSlot(updatedSlotId);
////                System.out.println("Pay " + updatedSlot.getPrice());
//                System.out.println("Enter the transactionId: ");
//                String newTransactionId = scanner.nextLine();
//                flipFitCustomerService.createBooking(flipFitCustomer.getCustomerId(), updatedSlotId, newTransactionId);
            case 5:
                System.out.println("Enter bookingId: ");
                int inputBookingId = Integer.parseInt(scanner.nextLine());
                if(flipFitCustomerService.cancelBooking(flipFitCustomer.getCustomerId(), inputBookingId) == true){
                    System.out.println("Booking cancelled successfully");
                }
                else{
                    System.out.println("Invalid details. Booking could not be created.");
                }
                break;

            case 6:
                System.out.println("Logged out successfully");
                return true;
        }
        return false;
    }
}
