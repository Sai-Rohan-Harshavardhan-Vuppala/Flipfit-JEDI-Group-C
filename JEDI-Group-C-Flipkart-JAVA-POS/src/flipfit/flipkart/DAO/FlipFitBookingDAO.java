package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitBooking;
import flipfit.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlipFitBookingDAO {
    public boolean create(int customerId, int slotId, int paymentId, String bookingStatus){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "INSERT INTO FlipFitBookings (customerId, slotId, paymentId, status) VALUES (?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, customerId);
            stmt.setInt(2, slotId);
            stmt.setInt(3, paymentId);
            stmt.setString(4, bookingStatus);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("Booking created successfully");
                return true;
            }
            System.out.println("Booking creation failed");
            return false;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean delete(int bookingId){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "DELETE FROM FlipFitBookings WHERE bookingId = ?";
            PreparedStatement ps = con.prepareStatement(queryStr);
            ps.setInt(1, bookingId);
            int result = ps.executeUpdate();
            con.close();
            if(result > 0){
                System.out.println("Booking deleted successfully");
                return true;
            }
            System.out.println("Booking deletion failed");
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public List<FlipFitBooking> getByCustomerIdAndDate(int customerId, String date) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT b.bookingId, b.customerId, b.slotId, b.status, b.createdAt, s.startTime, s.endTime, s.slotDate FROM (SELECT * FROM FlipFitBookings WHERE customerId = ?) b LEFT JOIN FlipFitSlots as s ON b.slotId = s.slotId WHERE s.slotDate=?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, customerId);
            stmt.setString(2, date);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println("Bookings found");
                FlipFitBooking booking = new FlipFitBooking();
                booking.setBookingId(rs.getInt("bookingId"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setSlotId(rs.getInt("slotId"));
                booking.setBookingStatus(rs.getString("status"));
                booking.setCreatedAt(rs.getTimestamp("createdAt"));
                booking.setStartTime(rs.getTime("startTime"));
                booking.setEndTime(rs.getTime("endTime"));
                booking.setSlotDate(rs.getDate("slotDate"));
                bookings.add(booking);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return bookings;
    }

    public FlipFitBooking get(int bookingId) {
        FlipFitBooking booking = null;
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitBookings WHERE bookingId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                System.out.println("Booking found");
                booking = new FlipFitBooking();
                booking.setBookingId(rs.getInt("bookingId"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setSlotId(rs.getInt("slotId"));
                booking.setBookingStatus(rs.getString("status"));
                booking.setCreatedAt(rs.getTimestamp("createdAt"));
                booking.setPaymentId(rs.getInt("paymentId"));
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return booking;
    }

    public boolean update(int bookingId, int customerId, int slotId, int paymentId, String status) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "UPDATE FlipFitBookings SET customerId = ?, slotId = ?, paymentId = ?, status = ? WHERE bookingId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, customerId);
            stmt.setInt(2, slotId);
            stmt.setInt(3, paymentId);
            stmt.setString(4, status);
            stmt.setInt(5, bookingId);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("Booking updated successfully");
                return true;
            }
            System.out.println("Booking update failed");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public FlipFitBooking getfirstCreatedBookingBySlotIdAndStatus(int slotId, String status) {
        FlipFitBooking booking = null;
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitBookings WHERE slotId = ? AND status = ? ORDER BY createdAt ASC LIMIT 1";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, slotId);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                booking = new FlipFitBooking();
                booking.setBookingId(rs.getInt("bookingId"));
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setSlotId(rs.getInt("slotId"));
                booking.setBookingStatus(rs.getString("status"));
                booking.setCreatedAt(rs.getTimestamp("createdAt"));
                booking.setPaymentId(rs.getInt("paymentId"));
            }
            con.close();

        }
        catch(Exception e){
            System.out.println(e);
        }
        return booking;
    }
    public static List<FlipFitBooking> getAllBookings() {
        List<FlipFitBooking> bookings = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitBookings";
            PreparedStatement stmt = con.prepareStatement(queryStr);
//            stmt.setString(1, "approved");
//            stmt.setString(2, gymCity);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitBooking booking = new FlipFitBooking();
                booking.setCustomerId(rs.getInt("cutomerId"));
                booking.setSlotId(rs.getInt("slotId"));
                booking.setBookingStatus(rs.getString("bookingStatus"));
                booking.setPaymentId(rs.getInt("paymentId"));
                booking.setCreatedAt(rs.getTimestamp("createdAt"));
                bookings.add(booking);
            }
            con.close();
            if(bookings.isEmpty()){
                System.out.println("No Bookings found!! ");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return bookings;
    }

    public static List<FlipFitBooking> getByCustomerId(int customerId) {
        List<FlipFitBooking> bookings = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitBookings where customerId=?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, customerId);
//            stmt.setString(2, gymCity);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitBooking booking = new FlipFitBooking();
                booking.setCustomerId(rs.getInt("customerId"));
                booking.setSlotId(rs.getInt("slotId"));
                booking.setBookingStatus(rs.getString("status"));
                booking.setPaymentId(rs.getInt("paymentId"));
                booking.setCreatedAt(rs.getTimestamp("createdAt"));
//                booking.setWaitListRank(rs.getInt("waitListRank"));
                bookings.add(booking);
            }
            con.close();
            if(bookings.isEmpty()){
                System.out.println("No Bookings found!! ");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return bookings;
    }
}
