package flipfit.flipkart.bean;

public class FlipFitUser {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String name;
    private int roleId;
    private String status;

    public FlipFitUser() {}

    public FlipFitUser(int userId, String name, String email, String username, String password, int roleId, String status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.roleId = roleId;
        this.status = status;
        this.userId = userId;
    }

    public FlipFitUser(FlipFitUser user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.name = user.getName();
        this.roleId = user.getRoleId();
        this.status = user.getStatus();
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void printAllDetails() {
        System.out.println("username: " + getUsername());
        System.out.println("password: " + getPassword());
        System.out.println("email: " + getEmail());
        System.out.println("roleId: " + getRoleId());
        System.out.println("status: " + getStatus());
        System.out.println("userId: " + getUserId());
        System.out.println("name: " + getName());
    }
}
