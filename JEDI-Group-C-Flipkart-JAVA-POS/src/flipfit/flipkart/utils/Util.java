package flipfit.flipkart.utils;

import flipfit.flipkart.bean.FlipFitGymOwner;
import flipfit.flipkart.constant.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static flipfit.flipkart.helper.Helper.*;

public class Util {
    public static Connection connectToDatabase() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(Constant.DATABASE_URL, Constant.DATABASE_USER, Constant.DATABASE_PASSWORD);
        printInGreen("Connected to database");
        return con;
    }

    public static void showTable(List<String> values, String title){
//        System.out.printf("%n---------------------------------------------------------------------------------------------------------------------------------%n");
        printInYellow("\n" + title);
//        System.out.printf("---------------------------------------------------------------------------------------------------------------------------------%n");
        int valueIndex = 0;
        int columnCount = values.size() / 5;
        for(int i = 0; i < columnCount; i++){
            System.out.printf(" %-20s %-20s %-20s %-20s %-20s%n", String.valueOf(valueIndex + 1) + ". " + values.get(valueIndex), String.valueOf(valueIndex + 2) + ". " + values.get(valueIndex + 1), String.valueOf(valueIndex + 3) + ". " + values.get(valueIndex + 2), String.valueOf(valueIndex + 4) + ". " + values.get(valueIndex + 3), String.valueOf(valueIndex + 5) + ". " + values.get(valueIndex + 4));
            valueIndex++;
        }
        int rem = values.size() % 5;
        switch(rem){
            case 0:
                break;
            case 1:
                System.out.printf(" %-20s%n", String.valueOf(valueIndex + 1) + ". " + values.get(valueIndex));
                break;
            case 2:
                System.out.printf(" %-20s %-20s%n", String.valueOf(valueIndex + 1) + ". " + values.get(valueIndex), String.valueOf(valueIndex + 2) + ". " + values.get(valueIndex + 1));
                break;
            case 3:
                System.out.printf(" %-20s %-20s %-20s%n", String.valueOf(valueIndex + 1) + ". " + values.get(valueIndex), String.valueOf(valueIndex + 2) + ". " + values.get(valueIndex + 1), String.valueOf(valueIndex + 3) + ". " + values.get(valueIndex + 2));
                break;
            case 4:
                System.out.printf(" %-20s %-20s %-20s %-20s%n", String.valueOf(valueIndex + 1) + ". " + values.get(valueIndex), String.valueOf(valueIndex + 2) + ". " + values.get(valueIndex + 1), String.valueOf(valueIndex + 3) + ". " + values.get(valueIndex + 2), String.valueOf(valueIndex + 4) + ". " + values.get(valueIndex + 3));
                break;
        }
        System.out.println();
    }
}
