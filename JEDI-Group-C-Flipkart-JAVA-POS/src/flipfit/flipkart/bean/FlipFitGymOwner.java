package flipfit.flipkart.bean;

public class FlipFitGymOwner extends FlipFitUser {

    public FlipFitGymOwner(String name, String email, String username, String password) {
        super(name, email, username, password, Role.GYM_OWNER);
        this.gymOwnerId = gymOwnerIdCounter++;
    }

    private int gymOwnerId;
    static int gymOwnerIdCounter = 0;


    public int getGymOwnerId() {
        return gymOwnerId;
    }
}
