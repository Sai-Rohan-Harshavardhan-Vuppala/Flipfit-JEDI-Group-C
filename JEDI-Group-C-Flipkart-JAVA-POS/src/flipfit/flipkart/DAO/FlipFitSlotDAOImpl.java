package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlipFitSlotDAOImpl implements FlipFitSlotDAOInterface {
    public boolean create(int gymId, String slotDate, String startTime, String endTime, int seatsAvailable, double price, int totalSeats, String status) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "INSERT INTO FlipFitSlots (status, gymId, startTime, endTime, seatsAvailable, price, totalSeats, slotDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, status);
            stmt.setInt(2, gymId);
            stmt.setString(3, startTime);
            stmt.setString(4, endTime);
            stmt.setInt(5, seatsAvailable);
            stmt.setDouble(6, price);
            stmt.setDouble(7, totalSeats);
            stmt.setString(8, slotDate);
            int result = stmt.executeUpdate();
            con.close();
            if(result > 0){
                System.out.println("Slot created successfully");
                return true;
            }
            else{
                System.out.println("Slot creation failed");
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public List<FlipFitSlot> getByGymIdAndStatusAndDate(int gymId, String status, String slotDate) {
        List<FlipFitSlot> slots = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitSlots WHERE gymId = ? AND status = ? AND slotDate = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, gymId);
            stmt.setString(2, status);
            stmt.setString(3, slotDate);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitSlot slot = new FlipFitSlot();
                slot.setSlotId(rs.getInt("slotId"));
                slot.setGymId(rs.getInt("gymId"));
                slot.setSeatsAvailable(rs.getInt("seatsAvailable"));
                slot.setStatus(rs.getString("status"));
                slot.setStartTime(rs.getTime("startTime"));
                slot.setEndTime(rs.getTime("endTime"));
                slot.setPrice(rs.getDouble("price"));
                slot.setSlotDate(rs.getDate("slotDate"));
                slot.setTotalSeats(rs.getInt("totalSeats"));
                slots.add(slot);
            }
            con.close();
            return slots;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return slots;
    }

    public FlipFitSlot get(int slotId) {
        FlipFitSlot slot = null;
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitSlots WHERE slotId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, slotId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                slot = new FlipFitSlot();
                slot.setSlotId(rs.getInt("slotId"));
                slot.setGymId(rs.getInt("gymId"));
                slot.setSeatsAvailable(rs.getInt("seatsAvailable"));
                slot.setStatus(rs.getString("status"));
                slot.setStartTime(rs.getTime("startTime"));
                slot.setEndTime(rs.getTime("endTime"));
                slot.setPrice(rs.getDouble("price"));
                slot.setSlotDate(rs.getDate("slotDate"));
                slot.setTotalSeats(rs.getInt("totalSeats"));
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return slot;
    }

    public boolean update(int slotId, int gymId, String date, String startTime, String endTime, int seatsAvailable, double price, String status, int totalSeats) {
        boolean result = false;
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "UPDATE FlipFitSlots SET startTime=?, endTime=?, seatsAvailable=?, price=?, gymId=?, status=?, slotDate = ?, totalSeats = ? WHERE slotId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, startTime);
            stmt.setString(2, endTime);
            stmt.setInt(3, seatsAvailable);
            stmt.setDouble(4, price);
            stmt.setInt(5, gymId);
            stmt.setString(6, status);
            stmt.setString(7, date);
            stmt.setInt(8, totalSeats);
            stmt.setInt(9, slotId);
            int updateResult = stmt.executeUpdate();
            con.close();
            if(updateResult > 0){
                System.out.println("Slot updated successfully");
                result = true;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public boolean delete(int rejectedSlotId) {
        boolean result = false;
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "DELETE FROM FlipFitSlots WHERE slotId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, rejectedSlotId);
            int deleteResult = stmt.executeUpdate();
            if(deleteResult > 0){
                System.out.println("Slot deleted successfully");
                result = true;
            }
            else{
                System.out.println("Slot with slot ID " + rejectedSlotId + " does not exist");
                System.out.println("Slot deletion failed");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return result;
    }

    public List<FlipFitSlot> getByStatus(String status) {
        List<FlipFitSlot> slots = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitSlots WHERE status = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitSlot slot = new FlipFitSlot();
                slot.setSlotId(rs.getInt("slotId"));
                slot.setGymId(rs.getInt("gymId"));
                slot.setSeatsAvailable(rs.getInt("seatsAvailable"));
                slot.setStatus(rs.getString("status"));
                slot.setStartTime(rs.getTime("startTime"));
                slot.setEndTime(rs.getTime("endTime"));
                slot.setPrice(rs.getDouble("price"));
                slot.setSlotDate(rs.getDate("slotDate"));
                slots.add(slot);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return slots;
    }

    public List<FlipFitSlot> getByGymId(int gymId) {
        List<FlipFitSlot> slots = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitSlots WHERE gymId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, gymId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitSlot slot = new FlipFitSlot();
                slot.setGymId(gymId);
                slot.setSlotId(rs.getInt("slotId"));
                slot.setStartTime(rs.getTime("startTime"));
                slot.setEndTime(rs.getTime("endTime"));
                slot.setStatus(rs.getString("status"));
                slot.setPrice(rs.getDouble("price"));
                slot.setSlotDate(rs.getDate("slotDate"));
                slot.setSeatsAvailable(rs.getInt("seatsAvailable"));
                slot.setTotalSeats(rs.getInt("totalSeats"));
                slots.add(slot);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return slots;
    }
}
