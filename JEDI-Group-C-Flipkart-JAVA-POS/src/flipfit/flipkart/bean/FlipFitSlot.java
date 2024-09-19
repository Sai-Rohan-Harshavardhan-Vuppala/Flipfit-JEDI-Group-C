package flipfit.flipkart.bean;

import java.time.LocalTime;

public class FlipFitSlot {
    private int slotId;
    private String status;
    private int gymId;
    private LocalTime startTime;
    private LocalTime endTime;
    private int seats_available;
    private double price;

    public FlipFitSlot(int gymId, LocalTime startTime, LocalTime endTime, int seats_available, double price) {
        this.gymId = gymId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats_available = seats_available;
        this.price = price;
    }
    //waitlist we need to add then only we will add its attributes in it

    public double getPrice() {
        return price;
    }

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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStart_tine(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getSeat_available() {
        return seats_available;
    }

    public void setSeat_available(int seats_available) {
        this.seats_available = seats_available;
    }
}
