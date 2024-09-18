package flipfit.flipkart.bean;

import java.util.List;

public class GymOwner extends User {
    private String  GymOwnerId;
    private List<Gym>gymList;

    public String getGymOwnerId() {
        return GymOwnerId;
    }
    public void setGymOwnerId(String gymOwnerId) {
        GymOwnerId = gymOwnerId;
    }

    public List<Gym> getGymList() {
        return gymList;
    }
    public void setGymList(List<Gym> gymList) {
        this.gymList = gymList;
    }
}
