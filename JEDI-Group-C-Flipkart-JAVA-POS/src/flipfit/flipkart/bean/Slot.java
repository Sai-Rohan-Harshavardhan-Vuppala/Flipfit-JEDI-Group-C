package flipfit.flipkart.bean;

import java.sql.Time;

public class Slot {
    private String slotId;
    private String status;
    private Gym gym_name;
    private Time start_time;
    private Time end_tine;
    private int seat_available;
    //waitlist we need to add then only we will add its attributes in it


    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Gym getGym_name() {
        return gym_name;
    }

    public void setGym_name(Gym gym_name) {
        this.gym_name = gym_name;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_tine(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_tine() {
        return end_tine;
    }

    public void setEnd_tine(Time end_tine) {
        this.end_tine = end_tine;
    }

    public int getSeat_available() {
        return seat_available;
    }

    public void setSeat_available(int seat_available) {
        this.seat_available = seat_available;
    }
}
