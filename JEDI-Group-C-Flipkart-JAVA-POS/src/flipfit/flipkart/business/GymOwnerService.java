package flipfit.flipkart.business;

import flipfit.flipkart.bean.GymOwner;

public class GymOwnerService {
    public GymOwner createGymOwner(){
        GymOwner gymOwner = new GymOwner("Sankalp", "sankalpg38@gmail.com", "sankalpg38", "mypassword");
        System.out.println("Gym owner" + gymOwner + "created");
        return gymOwner;
    }
}
