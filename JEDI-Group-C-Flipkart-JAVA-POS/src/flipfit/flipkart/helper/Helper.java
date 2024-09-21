package flipfit.flipkart.helper;

import flipfit.flipkart.DAO.FlipFitUserDAO;
import flipfit.flipkart.bean.FlipFitUser;

public class Helper {
    public static boolean checkIfUserWithEmailExists(String email){
        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        if(flipFitUserDAO.getByEmail(email) != null){
            System.out.println("User with email " + email + " already exists");
            return true;
        }
        System.out.println("User with email " + email + " does not exist");
        return false;
    }

    public static FlipFitUser verifyCredentials(String email, String enteredPassword){
        FlipFitUserDAO flipFitUserDAO = new FlipFitUserDAO();
        FlipFitUser flipFitUser = flipFitUserDAO.getByEmail(email);
        if(flipFitUser == null){
            System.out.println("Invalid email");
            return null;
        }
        String flipFitUserStatus = flipFitUser.getStatus();
//        System.out.println(flipFitUserStatus);
        if(flipFitUserStatus.equals("pending")){
            System.out.println("Registration pending at admin");
            return null;
        }
        if(flipFitUserStatus.equals("blacklisted")){
            System.out.println("Your account has been blacklisted by admin");
            return null;
        }
        if(enteredPassword.equals(flipFitUser.getPassword()) == false){
            System.out.println("Invalid password");
//            System.out.println("Original password: " + flipFitUser.getPassword());
            return null;
        }
        System.out.println("Credentials verified");
        return flipFitUser;
    }
}
