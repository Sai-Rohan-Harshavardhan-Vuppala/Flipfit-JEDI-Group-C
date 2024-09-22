package flipfit.flipkart.bean;

public class FlipFitCustomer extends FlipFitUser {
    private int customerId;
    private String phone;

    public FlipFitCustomer(int id, String name, String email, String username, String password, String phone, String status) {
        super(id, name, email, username, password, 1, status); // Assuming roleId = 1 for customers
        this.phone = phone;
    }

    public FlipFitCustomer() {
        // Default constructor
    }

    public FlipFitCustomer(FlipFitUser user) {
        super(user.getUserId(), user.getName(), user.getEmail(), user.getUsername(), user.getPassword(), user.getRoleId(), user.getStatus());
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserFields(FlipFitUser user) {
        this.setStatus(user.getStatus());
        this.setName(user.getName());
        this.setEmail(user.getEmail());
        this.setUsername(user.getUsername());
        this.setPassword(user.getPassword());
        this.setUserId(user.getUserId());
        this.setRoleId(user.getRoleId());
    }
}
