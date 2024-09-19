package flipfit.flipkart.bean;

public class FlipFitAdmin extends FlipFitUser{

    public FlipFitAdmin(String name, String email, String username, String password) {
        super(name, email, username, password, Role.ADMIN);
    }
}
