package flipfit.flipkart.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import flipfit.flipkart.bean.FlipFitUser;
import flipfit.flipkart.utils.Util;

import static flipfit.flipkart.helper.Helper.printInGreen;
import static flipfit.flipkart.helper.Helper.printInRed;

public class FlipFitUserDAOImpl implements FlipFitUserDAOInterface {



    public void create(String username, String password, String email, String name, int roleId, String status){
        try{
            Connection con = Util.connectToDatabase();
            String query = "insert into FlipFitUsers (username, password, email, name, roleId, status) values (?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, name);
            stmt.setInt(5, roleId);
            stmt.setString(6, status);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("User created successfully");
            }
            else{
                System.out.println("User creation failed");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public FlipFitUser getByEmail(String email){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitUsers WHERE email=?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            List<FlipFitUser> userList = new ArrayList<>();
            while(rs.next()){
                FlipFitUser user = new FlipFitUser();
                user.setUserId(rs.getInt("userId"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setStatus(rs.getString("status"));
                user.setRoleId(rs.getInt("roleId"));
                userList.add(user);
            }
            if(!userList.isEmpty()){
                System.out.println("User found");
                con.close();
                return userList.get(0);
            }
            System.out.println("No User found");
            con.close();
            return null;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public void update(String username, String password, String email, String name, String status, int roleId, int userId){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "UPDATE FlipFitUsers SET username = ?, password = ?, email = ?, name = ?, status = ?, roleId = ? WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, name);
            stmt.setString(5, status);
            stmt.setInt(6, roleId);
            stmt.setInt(7, userId);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("User updated successfully");
            }
            else{
                System.out.println("No user with id " + userId + "not found");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public FlipFitUser getByUserId(int userId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitUsers WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            List<FlipFitUser> userList = new ArrayList<>();
            if(rs.next()){
                System.out.println("User found");
                FlipFitUser user = new FlipFitUser();
                user.setUserId(rs.getInt("userId"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                user.setStatus(rs.getString("status"));
                user.setRoleId(rs.getInt("roleId"));
                userList.add(user);
                con.close();
                return userList.get(0);
            }
            else {
                System.out.println("No user with id " + userId + "not found");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public boolean delete(int userId){
        boolean result = false;
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "DELETE FROM FlipFitUsers WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            int queryResult = stmt.executeUpdate();
            if(queryResult > 0){
                printInGreen("User deleted successfully");
                result = true;
            }
            else{
                printInRed("No user with id " + userId + "not found");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return result;
    }
}
