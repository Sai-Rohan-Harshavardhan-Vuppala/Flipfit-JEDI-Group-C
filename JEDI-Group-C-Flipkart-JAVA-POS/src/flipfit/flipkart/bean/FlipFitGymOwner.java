package flipfit.flipkart.bean;

public class FlipFitGymOwner extends FlipFitUser {

    private int gymOwnerId;
    private String accountNumber;
    static int gymOwnerIdCounter = 0;

    public FlipFitGymOwner(String name, String email, String username, String password, String accountNumber) {
        super(name, email, username, password, Role.GYM_OWNER);
        this.gymOwnerId = gymOwnerIdCounter++;
        this.accountNumber = accountNumber;
    }


    public int getGymOwnerId() {
        return gymOwnerId;
    }
}
