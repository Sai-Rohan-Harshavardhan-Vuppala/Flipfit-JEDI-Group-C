package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitSlot;

import java.util.List;

public interface FlipFitSlotDAOInterface {
    public boolean create(int gymId, String slotDate, String startTime, String endTime, int seatsAvailable, double price, int totalSeats, String status);
    public List<FlipFitSlot> getByGymIdAndStatusAndDate(int gymId, String status, String slotDate);
    public FlipFitSlot get(int slotId);
    public boolean update(int slotId, int gymId, String date, String startTime, String endTime, int seatsAvailable, double price, String status, int totalSeats);
    public boolean delete(int rejectedSlotId);
    public List<FlipFitSlot> getByStatus(String status);
    public List<FlipFitSlot> getByGymId(int gymId);
}
