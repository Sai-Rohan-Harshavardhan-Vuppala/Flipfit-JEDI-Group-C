package flipfit.flipkart.client;

import java.util.Scanner;

public class GymOwnerClient {
    public boolean showMenu(){
        System.out.println("Welcome to FlipFit Gym Owner Client");
        System.out.println("1. Logout");
        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                return true;
        }
        return false;
    }
}
