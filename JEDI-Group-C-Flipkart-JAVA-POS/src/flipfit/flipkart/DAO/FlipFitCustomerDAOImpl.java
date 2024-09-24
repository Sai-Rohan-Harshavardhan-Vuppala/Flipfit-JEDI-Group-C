package flipfit.flipkart.DAO;
import java.sql.*;
import flipfit.flipkart.bean.FlipFitCustomer;
import flipfit.flipkart.bean.FlipFitUser;
import flipfit.flipkart.constant.Constant;
import flipfit.flipkart.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class FlipFitCustomerDAO {

    public void create(int userId, String phone){
        try{
            // creating the connection object
            Connection con = Util.connectToDatabase();
            String queryStr = "insert into FlipFitCustomers (userId, phone) values(?,?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            stmt.setString(2, phone);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("User created successfully");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public FlipFitCustomer getByUser(FlipFitUser user) {
        try{
            int userId = user.getUserId();
            Connection con = Util.connectToDatabase();
            String queryStr = "select * from FlipFitCustomers where userId=?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                FlipFitCustomer flipFitCustomer = new FlipFitCustomer(user);
                flipFitCustomer.setCustomerId(result.getInt("customerId"));
                flipFitCustomer.setUserId(result.getInt("userId"));
                flipFitCustomer.setPhone(result.getString("phone"));
                return flipFitCustomer;
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


    public List<FlipFitCustomer> getAllCustomers() {
        List<FlipFitCustomer> customers = new ArrayList<>();
        try {
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT u.userId, u.username, u.email, u.name, c.customerId, c.phone " +
                    "FROM FlipFitUsers u " +
                    "INNER JOIN FlipFitCustomers c ON u.userId = c.userId";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                FlipFitUser user = new FlipFitUser();
                user.setUserId(result.getInt("userId"));
                user.setUsername(result.getString("username"));
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));

                FlipFitCustomer customer = new FlipFitCustomer(user);
                customer.setCustomerId(result.getInt("customerId"));
                customer.setPhone(result.getString("phone"));

                customers.add(customer);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return customers;
    }
}
