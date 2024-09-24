package flipfit.flipkart.DAO;

import flipfit.flipkart.utils.Util;

import java.sql.*;

public class RoleDAO {
    public void createRole(String roleName){
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "INSERT INTO FlipFitRoles VALUES(?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, roleName);
            int result = stmt.executeUpdate();
            if(result > 0){
                System.out.println("Role created successfully");
            }
        }
        catch(Exception e){
            System.out.println("Role creation failed");
            System.out.println(e);
        }
    }
}
