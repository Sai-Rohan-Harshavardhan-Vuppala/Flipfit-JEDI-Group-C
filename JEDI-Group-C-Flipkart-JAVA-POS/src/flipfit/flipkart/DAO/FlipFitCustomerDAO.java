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
}
