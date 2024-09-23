package flipfit.flipkart.client;
import flipfit.flipkart.bean.*;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;
import flipfit.flipkart.business.FlipFitPaymentService;

import java.util.Scanner;

public class FlipFitApp {

    private static Scanner scanner = new Scanner(System.in);
    private static FlipFitGymOwnerService flipFitGymOwnerService;
    private static FlipFitCustomerService flipFitCustomerService;
    private static FlipFitAdminService flipFitAdminService;
    private static FlipFitPaymentService flipFitPaymentService;

    public static void main(String[] args) {

        flipFitGymOwnerService = new FlipFitGymOwnerService();
        flipFitCustomerService = new FlipFitCustomerService();
        flipFitAdminService = new FlipFitAdminService();
        flipFitPaymentService = new FlipFitPaymentService();
        while(showMainMenu() == false);
    }

    public static boolean showMainMenu() {
        /*
            creating serviceInstances
         */


        System.out.println("\nWelcome to the Flipfit App");
        System.out.println("1. Login");
        System.out.println("2. Register as Flipfit Customer");
        System.out.println("3. Register as Flipfit Gym Owner");
//        System.out.println("4. Update Password");
        System.out.println("4. Exit");

        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                registerCustomer();
                break;
            case 3:
                registerGymOwner();
                break;
//            case 4:
//                updatePassword();
//                break;
            case 4:
                System.out.println("Exiting...");
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }

    private static void login() {
        System.out.println("\nLogin");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (Admin/Customer/GymOwner): ");
        String role = scanner.nextLine();
        switch (role.toLowerCase()) {
            case "customer":
                FlipFitCustomer flipFitCustomer = flipFitCustomerService.login(email, password);
                if(flipFitCustomer != null){
                    System.out.println("Logged in successfully.");
                    showCustomerMenu(flipFitCustomer);
                }
                break;
            case "admin":
                FlipFitAdmin flipFitAdmin = flipFitAdminService.login(email, password);
                if(flipFitAdmin != null){
                    System.out.println("Logged in successfully.");
                    showAdminMenu(flipFitAdmin);
                }
                break;
            case "gymowner":
                FlipFitGymOwner flipFitGymOwner = flipFitGymOwnerService.login(email, password);
                if(flipFitGymOwner != null){
                    System.out.println("Logged in successfully.");
                    showGymOwnerMenu(flipFitGymOwner);
                }
                break;
            default:
                System.out.println("Invalid role. Please try again later.");
        }
    }

    private static void showAdminMenu(FlipFitAdmin flipFitAdmin) {
        System.out.println("\nAdmin Menu");
        FlipFitAdminClient flipFitAdminClient = new FlipFitAdminClient(flipFitGymOwnerService, flipFitCustomerService, flipFitAdminService, flipFitPaymentService, flipFitAdmin);
        while(!flipFitAdminClient.showMenu());

    }

    private static void showGymOwnerMenu(FlipFitGymOwner flipFitGymOwner) {
        System.out.println("\nGym Owner Menu");
        GymOwnerClient gymOwnerClient = new GymOwnerClient(flipFitGymOwnerService, flipFitCustomerService, flipFitAdminService, flipFitPaymentService, flipFitGymOwner);
        while(!gymOwnerClient.showMenu());

    }

    private static void showCustomerMenu(FlipFitCustomer flipFitCustomer) {
        System.out.println("\nCustomer Menu");
        CustomerClient customerClient = new CustomerClient(flipFitGymOwnerService, flipFitCustomerService, flipFitAdminService, flipFitPaymentService, flipFitCustomer);
        while(customerClient.showMenu() == false);
    }

    private static void registerCustomer() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phone = scanner.nextLine();
        flipFitCustomerService.createCustomer(username, password, email, name, phone);

    }

    private static void registerGymOwner() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Gym owner account number: ");
        String accountNumber = scanner.nextLine();
        flipFitGymOwnerService.createGymOwner(username, password, email, name, accountNumber);

    }

    private static void updatePassword() {
        System.out.println("\nUpdate Password");
    }


}
