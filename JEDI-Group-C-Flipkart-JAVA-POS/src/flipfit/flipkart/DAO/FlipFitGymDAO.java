package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymDAO {
    public void create(String gymName, String gymCity, String gymArea, int gymOwnerId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "INSERT INTO FlipFitGyms (gymName, gymCity, gymArea, status, gymOwnerId) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, gymName);
            stmt.setString(2, gymCity);
            stmt.setString(3, gymArea);
            stmt.setString(4, "pending");
            stmt.setInt(5, gymOwnerId);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("Gym " + gymName + " pending at admin");
            }
            else{
                System.out.println("Gym request failed. Create a new one.");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public FlipFitGym get(int gymId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitGyms WHERE gymId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, gymId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                FlipFitGym gym = new FlipFitGym();
                gym.setGymId(gymId);
                gym.setGymName(rs.getString("gymName"));
                gym.setGymCity(rs.getString("gymCity"));
                gym.setGymArea(rs.getString("gymArea"));
                gym.setStatus(rs.getString("status"));
                gym.setGymOwnerId(rs.getInt("gymOwnerId"));
                con.close();
                return gym;
            }
            else{
                System.out.println("Gym with Gym ID " + gymId + " not found");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<FlipFitGym> getAll() {
        List<FlipFitGym> gyms = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitGyms WHERE status = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, "approved");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitGym gym = new FlipFitGym();
                gym.setGymName(rs.getString("gymName"));
                gym.setGymCity(rs.getString("gymCity"));
                gym.setGymArea(rs.getString("gymArea"));
                gym.setStatus(rs.getString("status"));
                gym.setGymOwnerId(rs.getInt("gymOwnerId"));
                gyms.add(gym);
            }
            con.close();
            if(gyms.isEmpty()){
                System.out.println("No Gyms found.");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return gyms;
    }

    public List<FlipFitGym> getGymByGymOwnerId(int gymOwnerId) {
        List<FlipFitGym> gyms = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitGyms WHERE gymOwnerId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, gymOwnerId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitGym gym = new FlipFitGym();
                gym.setGymId(rs.getInt("gymId"));
                gym.setGymName(rs.getString("gymName"));
                gym.setGymCity(rs.getString("gymCity"));
                gym.setGymArea(rs.getString("gymArea"));
                gym.setStatus(rs.getString("status"));
                gyms.add(gym);
            }
            con.close();
            if(gyms.isEmpty()){
                System.out.println("No Gyms found for Gym Owner Id: " +  gymOwnerId);
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return gyms;
    }

    public boolean update(String gymName, String gymCity, String gymArea, String gymStatus, int gymOwnerId, int gymId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "UPDATE FlipFitGyms SET gymName = ?, gymCity = ?, gymArea = ?, gymOwnerId = ?, status = ? WHERE gymId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, gymName);
            stmt.setString(2, gymCity);
            stmt.setString(3, gymArea);
            stmt.setInt(4, gymOwnerId);
            stmt.setString(5, gymStatus);
            stmt.setInt(6, gymId);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("Gym with Gym ID " + gymId + " updated");
                con.close();
                return true;
            }
            else{
                System.out.println("Gym with Gym ID" + gymId + " not updated");
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean delete(int gymId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "DELETE FROM FlipFitGyms WHERE gymId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, gymId);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("Gym with Gym ID" + gymId + " deleted");
                con.close();
                return true;
            }
            else{
                System.out.println("Gym with Gym ID" + gymId + " does not exist");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public List<FlipFitGym> getByStatus(String status) {
        List<FlipFitGym> gyms = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitGyms WHERE status = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                FlipFitGym gym = new FlipFitGym();
                gym.setGymId(rs.getInt("gymId"));
                gym.setGymName(rs.getString("gymName"));
                gym.setGymCity(rs.getString("gymCity"));
                gym.setGymOwnerId(rs.getInt("gymOwnerId"));
                gym.setStatus(rs.getString("status"));
                gym.setGymArea(rs.getString("gymArea"));
                gyms.add(gym);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return gyms;
    }

    public List<String> getAreasByCityAndStatus(String city, String status) {
        List<String> areas = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT DISTINCT gymArea FROM FlipFitGyms WHERE city = ? AND status = ? ORDER BY gymArea ASC";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, city);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                areas.add(rs.getString("gymArea"));
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return areas;
    }



    public List<String> getCitiesByStatus(String status) {
        List<String> cities = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT gymCity FROM FlipFitGyms WHERE status = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                cities.add(rs.getString("gymCity"));
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return cities;
    }

}
