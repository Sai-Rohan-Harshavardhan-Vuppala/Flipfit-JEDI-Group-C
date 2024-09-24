package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitUser;

public interface FlipFitUserDAOInterface {
    public void create(String username, String password, String email, String name, int roleId, String status);
    public FlipFitUser getByEmail(String email);
    public void update(String username, String password, String email, String name, String status, int roleId, int userId);
    public FlipFitUser getByUserId(int userId);
    public boolean delete(int userId);
}
