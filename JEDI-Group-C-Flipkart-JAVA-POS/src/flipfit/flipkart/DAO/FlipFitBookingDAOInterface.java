package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitBooking;

import java.util.List;

public interface FlipFitBookingDAOInterface {
    public boolean create(int customerId, int slotId, int paymentId, String bookingStatus);
    public boolean delete(int bookingId);
    public List<FlipFitBooking> getByCustomerIdAndDate(int customerId, String date);
    public FlipFitBooking get(int bookingId);
    public boolean update(int bookingId, int customerId, int slotId, int paymentId, String status);
    public FlipFitBooking getfirstCreatedBookingBySlotIdAndStatus(int slotId, String status);
    public List<FlipFitBooking> getAllBookings();
    public List<FlipFitBooking> getByCustomerId(int customerId);
//    public void getByDateAndCustomerId(int customerId, String date);
}
