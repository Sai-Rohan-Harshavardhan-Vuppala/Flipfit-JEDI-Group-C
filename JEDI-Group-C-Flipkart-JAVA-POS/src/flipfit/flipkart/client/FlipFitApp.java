package flipfit.flipkart.client;
import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.business.FlipFitAdminService;
import flipfit.flipkart.business.FlipFitCustomerService;
import flipfit.flipkart.business.FlipFitGymOwnerService;

import java.util.Scanner;

public class FlipFitApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMainMenu();
    }

    public static void showMainMenu() {
        System.out.println("\nWelcome to the Flipfit App");
        System.out.println("1. Login");
        System.out.println("2. Registration as Flipfit Customer");
        System.out.println("3. Registration as Flipfit Gym Owner");
        System.out.println("4. Update Password");
        System.out.println("5. Exit");

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
            case 4:
                updatePassword();
                break;
            case 5:
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void login() {
        System.out.println("\nLogin");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (Admin/Customer/GymOwner): ");
        String role = scanner.nextLine();
        switch (role.toLowerCase()) {
            case "customer":
                FlipFitCustomerService flipFitCustomerService = new FlipFitCustomerService();
                FlipFitCustomer flipFitCustomer = flipFitCustomerService.login(username, password);
                if(flipFitCustomer != null){
                    System.out.println("Logged in successfully.");
                    showCustomerMenu(flipFitCustomer);
                }
                break;
            case "admin":
                FlipFitAdminService flipFitAdminService = new FlipFitAdminService();
                if(flipFitAdminService.login(username, password)){
                    System.out.println("Logged in successfully.");
                    showAdminMenu();
                }
                break;
            case "gymowner":
                FlipFitGymOwnerService flipFitGymOwnerService = new FlipFitGymOwnerService();
                if(flipFitGymOwnerService.login(username, password)){
                    System.out.println("Logged in successfully.");
                    showGymOwnerMenu();
                }
                break;
            default:
                System.out.println("Invalid role. Please try again later.");
        }
    }

    private static void showAdminMenu() {
        System.out.println("\nAdmin Menu");
        FlipFitAdminClient flipFitAdminClient = new FlipFitAdminClient();
        while(!flipFitAdminClient.showMenu());

    }

    private static void showGymOwnerMenu() {
        System.out.println("\nGym Owner Menu");
        GymOwnerClient gymOwnerClient = new GymOwnerClient();
        while(!gymOwnerClient.showMenu());

    }

    private static void showCustomerMenu(FlipFitCustomer flipFitCustomer) {
        System.out.println("\nCustomer Menu");
        CustomerClient customerClient = new CustomerClient(flipFitCustomer);
        while(!customerClient.showMenu());
    }

    private static void registerCustomer() {
        System.out.println("\nRegistration as Flipfit Customer");

    }

    private static void registerGymOwner() {
        System.out.println("\nRegistration as Flipfit Gym Owner");

    }

    private static void updatePassword() {
        System.out.println("\nUpdate Password");
    }


}
