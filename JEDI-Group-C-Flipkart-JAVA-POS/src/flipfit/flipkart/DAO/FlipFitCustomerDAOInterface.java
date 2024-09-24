package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitUser;

public interface FlipFitCustomerDAOInterface {
    public void create(int userId, String phone);
    public FlipFitCustomer getByUser(FlipFitUser user);
}
