package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitGym;
import flipfit.flipkart.bean.FlipFitSlot;
import flipfit.flipkart.bean.FlipFitUser;

public class FlipfitAdminService {
    public void approveUser(FlipFitUser user) {
        System.out.println("User approved");
    }

    public boolean validateGym(FlipFitGym gym) {
        System.out.println("Gym approved"+gym);
        return true;
    }

    public boolean validateSlot(FlipFitSlot slot) {
        System.out.println("Slot approved"+slot);
        return true;
    }
}
