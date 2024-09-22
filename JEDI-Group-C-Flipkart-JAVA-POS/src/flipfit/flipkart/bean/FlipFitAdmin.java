package flipfit.flipkart.bean;

public class FlipFitAdmin extends FlipFitUser{
    private int adminId;

    public FlipFitAdmin() {

    }

    public FlipFitAdmin(int id, String name, String email, String username, String password) {
        super(id, name, email, username, password, 0, "whitelisted");
    }

    public FlipFitAdmin(FlipFitUser user) {
        super(user);
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int id) {
        this.adminId = id;
    }
}
