package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitGym;

import java.util.List;

public interface FlipFitGymDAOInterface {
    public void create(String gymName, String gymCity, String gymArea, int gymOwnerId);
    public FlipFitGym get(int gymId);
    public List<FlipFitGym> getAll();
    public List<FlipFitGym> getGymByGymOwnerId(int gymOwnerId);
    public boolean update(String gymName, String gymCity, String gymArea, String gymStatus, int gymOwnerId, int gymId);
    public boolean delete(int gymId);
    public List<FlipFitGym> getByStatus(String status);
    public List<String> getAreasByCityAndStatus(String city, String status);
    public List<String> getCitiesByStatus(String status);
}
