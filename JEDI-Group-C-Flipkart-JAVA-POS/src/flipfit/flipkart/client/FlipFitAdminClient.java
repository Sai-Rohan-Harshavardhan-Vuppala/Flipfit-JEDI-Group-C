package flipfit.flipkart.client;

import java.util.Scanner;

public class FlipFitAdminClient {
    public boolean showMenu(){
        System.out.println("Welcome to FlipFit Admin Client");
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
