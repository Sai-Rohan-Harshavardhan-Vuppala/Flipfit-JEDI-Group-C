package flipfit.flipkart.business;

import flipfit.flipkart.bean.Gym;
import flipfit.flipkart.bean.Slot;
import flipfit.flipkart.bean.User;

public class FlipfitAdminService {
    public void approveUser(User user) {
        System.out.println("User approved");
    }

    public boolean validateGym(Gym gym) {
        System.out.println("Gym approved"+gym);
        return true;
    }

    public boolean validateSlot(Slot slot) {
        System.out.println("Slot approved"+slot);
        return true;
    }
}
