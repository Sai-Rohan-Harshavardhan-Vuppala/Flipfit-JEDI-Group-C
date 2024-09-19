package flipfit.flipkart.bean;

public class Gym {
    private int gymId;

    public Gym(int gymOwnerId, String gym_name, String gym_city) {
        this.gymOwnerId = gymOwnerId;
        this.gym_name = gym_name;
        this.gym_city = gym_city;
        this.status = "pending";
    }

    private int gymOwnerId;
    private String gym_name;
    private String gym_city;
    private String status;



    public int getGymId() {
        return gymId;
    }
    public void setGym_id(int gym_id) {
        this.gymId = gym_id;
    }

    public int getGymOwnerId() {
        return gymOwnerId;
    }

    public void setGymOwnerId(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
    }

    public String getGym_name() {

        return gym_name;
    }
    public void setGym_name(String gym_name) {

        this.gym_name = gym_name;
    }

    public String getGym_city() {

        return gym_city;
    }
    public void setGym_city(String gym_city) {

        this.gym_city = gym_city;
    }

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }


}
