package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.bean.FlipFitUser;
import flipfit.flipkart.helper.Helper;
import flipfit.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FlipFitGymOwnerDAO {
    public void create(int userId, String accountNumber){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "INSERT INTO FlipFitGymOwners (userId, accountNumber) VALUES (?,?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            stmt.setString(2, accountNumber);
            int result = stmt.executeUpdate();
            con.close();
            if(result > 0){
                System.out.println("Gym owner created successfully");
            }
            else{
                System.out.println("Gym owner creation failed");
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public FlipFitGymOwner getByUser(FlipFitUser user){
        try{
            int userId = user.getUserId();
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitGymOwners WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                System.out.println("Gym owner found");
                FlipFitGymOwner flipFitGymOwner = new FlipFitGymOwner(user);
                flipFitGymOwner.setUserId(rs.getInt("userId"));
                flipFitGymOwner.setAccountNumber(rs.getString("accountNumber"));
                flipFitGymOwner.setGymOwnerId(rs.getInt("gymOwnerId"));
                con.close();
                return flipFitGymOwner;
            }
            System.out.println("Gym owner not found");
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void deleteByUserId(int userId){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "DELETE FROM FlipFitGymOwners WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            int result = stmt.executeUpdate();

//            if(result > 0){
//                System.out.println("Gym owner deleted successfully");
//            }
//            else{
//                System.out.println("Gym owner deletion failed");
//            }
//            System.out.println(result);
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public List<FlipFitGymOwner> getByStatus(String status) {

        List<FlipFitGymOwner> pendingflipFitGymOwners = new ArrayList<>();
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitGymOwners LEFT JOIN FlipFitUsers ON FlipFitGymOwners.userId = FlipFitUsers.userId WHERE FlipFitUsers.status = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                FlipFitGymOwner flipFitGymOwner = new FlipFitGymOwner();
                flipFitGymOwner.setGymOwnerId(rs.getInt("FlipFitGymOwners.gymOwnerId"));
                flipFitGymOwner.setAccountNumber(rs.getString("FlipFitGymOwners.accountNumber"));
                flipFitGymOwner.setUserId(rs.getInt("FlipFitUsers.userId"));
                flipFitGymOwner.setStatus(rs.getString("FlipFitUsers.status"));
                flipFitGymOwner.setUsername(rs.getString("FlipFitUsers.username"));
                flipFitGymOwner.setPassword(rs.getString("FlipFitUsers.password"));
                flipFitGymOwner.setName(rs.getString("FlipFitUsers.name"));
                flipFitGymOwner.setEmail(rs.getString("FlipFitUsers.email"));
                flipFitGymOwner.setRoleId(rs.getInt("FlipFitUsers.roleId"));
                pendingflipFitGymOwners.add(flipFitGymOwner);
            }
            return pendingflipFitGymOwners;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return pendingflipFitGymOwners;
    }

    public FlipFitGymOwner get(int gymOwnerId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitGymOwners WHERE gymOwnerId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, gymOwnerId);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                System.out.println("Gym owner found");
                FlipFitGymOwner gymOwner = new FlipFitGymOwner();
                gymOwner.setGymOwnerId(rs.getInt("gymOwnerId"));
                gymOwner.setAccountNumber(rs.getString("accountNumber"));
                gymOwner.setUserId(rs.getInt("userId"));
                con.close();
                return gymOwner;
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public List<FlipFitGymOwner> getAllGymOwners() {
        List<FlipFitGymOwner> gymOwnersList = new ArrayList<>();

        try {
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT FlipFitGymOwners.gymOwnerId, FlipFitGymOwners.accountNumber, " +
                    "FlipFitUsers.userId, FlipFitUsers.username, FlipFitUsers.password, " +
                    "FlipFitUsers.email, FlipFitUsers.name, FlipFitUsers.roleId, FlipFitUsers.status " +
                    "FROM FlipFitGymOwners " +
                    "JOIN FlipFitUsers ON FlipFitGymOwners.userId = FlipFitUsers.userId";

            PreparedStatement stmt = con.prepareStatement(queryStr);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create a FlipFitGymOwner object
                FlipFitGymOwner gymOwner = new FlipFitGymOwner();
                gymOwner.setGymOwnerId(rs.getInt("gymOwnerId"));
                gymOwner.setAccountNumber(rs.getString("accountNumber"));

                // Setting user details from FlipFitUsers
                gymOwner.setUserId(rs.getInt("userId"));
                gymOwner.setUsername(rs.getString("username"));
                gymOwner.setPassword(rs.getString("password"));
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setName(rs.getString("name"));
                gymOwner.setRoleId(rs.getInt("roleId"));
                gymOwner.setStatus(rs.getString("status"));

                // Add the gymOwner object to the list
                gymOwnersList.add(gymOwner);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return gymOwnersList;
    }

}
