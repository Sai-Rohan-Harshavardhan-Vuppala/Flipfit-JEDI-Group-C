package flipfit.flipkart.bean;

import java.util.List;

public class GymOwner extends User {

    public GymOwner(String name, String email, String username, String password) {
        super(name, email, username, password);
        this.gymOwnerId = gymOwnerIdCounter++;
    }

    private int gymOwnerId;
    static int gymOwnerIdCounter = 0;


    public int getGymOwnerId() {
        return gymOwnerId;
    }
}
