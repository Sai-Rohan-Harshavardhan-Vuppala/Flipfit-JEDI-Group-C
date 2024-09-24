package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitAdmin;
import flipfit.flipkart.bean.FlipFitUser;

public interface FlipFitAdminDAOInterface {
    public FlipFitAdmin getByUser(FlipFitUser user);
}
