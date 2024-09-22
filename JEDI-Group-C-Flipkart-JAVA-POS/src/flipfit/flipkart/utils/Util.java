package flipfit.flipkart.utils;

import flipfit.flipkart.constant.Constant;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    public static Connection connectToDatabase() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(Constant.DATABASE_URL, Constant.DATABASE_USER, Constant.DATABASE_PASSWORD);
        System.out.println("Connected to database");
        return con;
    }
}
