package flipfit.flipkart.bean;

public class FlipFitGymOwner extends FlipFitUser {

    private int gymOwnerId;
    private String accountNumber;

    public FlipFitGymOwner(int id, String name, String email, String username, String password, String accountNumber) {
        super(id, name, email, username, password, 2, "PENDING");
        this.accountNumber = accountNumber;
    }

    public FlipFitGymOwner() {

    }

    public FlipFitGymOwner(FlipFitUser user) {
        super(user);
    }


    public int getGymOwnerId() {
        return gymOwnerId;
    }

    public void setGymOwnerId(int gymOwnerId) {

        this.gymOwnerId = gymOwnerId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setUserFields(FlipFitUser flipFitUser) {
        this.setUserId(flipFitUser.getUserId());
        this.setName(flipFitUser.getName());
        this.setEmail(flipFitUser.getEmail());
        this.setUsername(flipFitUser.getUsername());
        this.setPassword(flipFitUser.getPassword());
        this.setStatus(flipFitUser.getStatus());

    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
