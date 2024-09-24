package flipfit.flipkart.DAO;

import flipfit.flipkart.bean.FlipFitPayment;
import flipfit.flipkart.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FlipFitPaymentDAOImpl implements FlipFitPaymentDAOInterface {
    public boolean create(String transactionId, int customerId) {
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "INSERT INTO flipFitPayments (transactionId, customerId) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, transactionId);
            stmt.setInt(2, customerId);
            int result = stmt.executeUpdate();

            if(result > 0){
                System.out.println("Payment created successfully");
                return true;
            }
            System.out.println("Payment creation failed");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public FlipFitPayment getByTransactionId(String transactionId) {
        FlipFitPayment payment = null;
        try{
            Connection con = Util.connectToDatabase();
            String queryStr = "SELECT * FROM flipFitPayments WHERE transactionId = ?";
            PreparedStatement stmt = con.prepareStatement(queryStr);
            stmt.setString(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                payment = new FlipFitPayment(transactionId, rs.getInt("paymentId"));
            }
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return payment;
    }
}
