package flipfit.flipkart.bean;

import java.sql.Time;

public class Slot {
    private int slotId;
    private String status;
    private int gymId;

    public Slot(int gymId, Time start_time, Time end_tine, int seats_available) {
        this.gymId = gymId;
        this.start_time = start_time;
        this.end_tine = end_tine;
        this.seats_available = seats_available;
    }

    private Time start_time;
    private Time end_tine;
    private int seats_available;
    //waitlist we need to add then only we will add its attributes in it


    public int getSlotId() {
        return slotId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
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
        return seats_available;
    }

    public void setSeat_available(int seat_available) {
        this.seats_available = seat_available;
    }
}
