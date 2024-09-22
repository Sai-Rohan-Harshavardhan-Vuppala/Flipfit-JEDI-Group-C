package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitAdmin;
import flipfit.flipkart.bean.FlipFitUser;
import flipfit.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlipFitAdminDAO {
    public FlipFitAdmin getByUser(FlipFitUser user){
        try{
            int userId = user.getUserId();
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM FlipFitAdmins WHERE userId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                FlipFitAdmin flipFitAdmin = new FlipFitAdmin(user);
                flipFitAdmin.setUserId(rs.getInt("userId"));
                flipFitAdmin.setAdminId(rs.getInt("adminId"));
                con.close();
                return flipFitAdmin;
            }
            else{
                System.out.println("Invalid userId");
            }
            con.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }


}
