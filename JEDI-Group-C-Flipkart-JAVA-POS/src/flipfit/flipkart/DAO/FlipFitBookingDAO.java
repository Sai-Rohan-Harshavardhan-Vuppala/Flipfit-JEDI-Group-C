package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitBooking;
import flipfit.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlipFitBookingDAO {
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
                booking.setBookingDate(rs.getDate("bookingDate"));
                booking.setWaitListRank(rs.getInt("waitListRank"));
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

    public static List<FlipFitBooking> getCustomerBookings(int customerId) {
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
                booking.setCustomerId(rs.getInt("cutomerId"));
                booking.setSlotId(rs.getInt("slotId"));
                booking.setBookingStatus(rs.getString("bookingStatus"));
                booking.setPaymentId(rs.getInt("paymentId"));
                booking.setBookingDate(rs.getDate("bookingDate"));
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
