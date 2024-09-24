package flipfit.flipkart.bean;

public class FlipFitGym {
    private int gymId;
    private int gymOwnerId;
    private String gymName;
    private String gymCity;
    private String gymArea;
    private String gymStatus;

    public FlipFitGym(int gymOwnerId, String gymName, String gymCity, String gymArea, String gymStatus) {
        this.gymOwnerId = gymOwnerId;
        this.gymName = gymName;
        this.gymCity = gymCity;
        this.gymStatus = gymStatus;
        this.gymArea = gymArea;
    }

    public FlipFitGym() {

    }


    public int getGymId() {
        return gymId;
    }
    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public int getGymOwnerId() {
        return gymOwnerId;
    }

    public void setGymOwnerId(int gymOwnerId) {
        this.gymOwnerId = gymOwnerId;
    }

    public String getGymName() {

        return gymName;
    }
    public void setGymName(String gymName) {

        this.gymName = gymName;
    }

    public String getGymCity() {

        return gymCity;
    }
    public void setGymCity(String gymCity) {

        this.gymCity = gymCity;
    }

    public String getGymStatus() {

        return gymStatus;
    }

    public void setGymStatus(String status) {

        this.gymStatus = status;
    }

    public String getGymArea() {
        return gymArea;
    }

    public void setGymArea(String gymArea) {
        this.gymArea = gymArea;
    }


}
