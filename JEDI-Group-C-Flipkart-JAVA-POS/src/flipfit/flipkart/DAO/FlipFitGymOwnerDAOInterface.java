package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.bean.FlipFitUser;

import java.util.List;

public interface FlipFitGymOwnerDAOInterface {
    public void create(int userId, String accountNumber);
    public FlipFitGymOwner getByUser(FlipFitUser user);
    public void deleteByUserId(int userId);
    public List<FlipFitGymOwner> getByStatus(String status);
    public FlipFitGymOwner get(int gymOwnerId);
    public List<FlipFitGymOwner> getAllGymOwners();
}
