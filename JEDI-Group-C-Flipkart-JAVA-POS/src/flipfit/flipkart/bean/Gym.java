package flipfit.flipkart.bean;

public class Gym {
    private String gym_id;
    private GymOwner gym_owner_name;
    private String gym_name;
    private String gym_city;
    private String status;


    public String getGym_id() {
        return gym_id;
    }
    public void setGym_id(String gym_id) {
        this.gym_id = gym_id;
    }

    public GymOwner getGym_owner_name() {
        return gym_owner_name;
    }
    public void setGym_owner_name(GymOwner gym_owner_name) {
        this.gym_owner_name = gym_owner_name;
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
