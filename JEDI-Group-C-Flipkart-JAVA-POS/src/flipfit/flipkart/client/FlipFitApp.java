package flipfit.flipkart.client;
import java.util.Scanner;

public class FlipFitApp {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
        }
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
            case "admin":
                showAdminMenu();
                break;
            case "customer":
                showCustomerMenu();
                break;
            case "gymowner":
                showGymOwnerMenu();
                break;
            default:
                System.out.println("Invalid role. Please try again.");
        }
    }

    private static void showAdminMenu() {
        System.out.println("\nAdmin Menu");

    }

    private static void showGymOwnerMenu() {
        System.out.println("\nGym Owner Menu");

    }

    private static void showCustomerMenu() {
        System.out.println("\nCustomer Menu");

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
