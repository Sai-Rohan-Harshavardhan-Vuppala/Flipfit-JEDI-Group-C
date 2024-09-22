package flipfit.flipkart.JDBC;
import java.sql.*;

public class DemoInsert {
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FlipFitSchema","root","yiagh2Sh!@#%");

            PreparedStatement stmt=con.prepareStatement("insert into EMPLOYEE values(?,?)");
            stmt.setInt(1,102);//1 specifies the first parameter in the query
            stmt.setString(2,"Ratan");

            int i=stmt.executeUpdate();
            System.out.println(i+" records inserted");

            con.close();

        }
        catch(Exception e){ System.out.println(e);}

    }
}






