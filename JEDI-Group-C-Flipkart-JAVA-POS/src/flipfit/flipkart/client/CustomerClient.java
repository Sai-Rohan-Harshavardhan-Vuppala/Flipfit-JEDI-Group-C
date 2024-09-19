package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitCustomer;

import java.util.Scanner;

public class CustomerClient {

    private FlipFitCustomer flipFitCustomer;

    public CustomerClient(FlipFitCustomer flipFitCustomer) {
        this.flipFitCustomer = flipFitCustomer;
    }


    public boolean showMenu(){
        System.out.println("Welcome to FlipFit Customer Client");
        System.out.println("1. Book slot");
        System.out.println("2. View all booked slots");
        System.out.println("3. Logout");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                System.out.println();
            case 3:
                return true;
        }
        return false;
    }
}
