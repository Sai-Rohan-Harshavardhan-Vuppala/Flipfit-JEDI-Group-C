package flipfit.flipkart.client;

import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.bean.FlipFitUser;


import java.util.Scanner;

import static java.lang.System.in;

public class FlipFitAdminClient {

    public static void approveUser() {
        System.out.println("Approved User details");
    }
    public static void validateGym(){
        System.out.println("Gym validated");

    }
    public static void validateSlot(){
        System.out.println("Slot validated");

    }
    public boolean showMenu(){
        System.out.println("Welcome to FlipFit Admin Client");
        System.out.println("1. Approve user");
        System.out.println("2. Validate Gym");
        System.out.println("3. Validate Slot");
        System.out.println("4. Logout");

        Scanner sc = new Scanner(in);


        int choice = Integer.parseInt(sc.nextLine());

        switch(choice){
                    case 1:{
                        validateGym();
                        break;
                    }
                    case 2:{
                        validateSlot();
                        break;
                    }
                    default: {
                        System.out.println("Invalid choice");
                    }
        }
        return false;
    }
}
