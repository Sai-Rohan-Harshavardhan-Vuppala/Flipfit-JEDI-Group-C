package flipfit.flipkart.bean;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class FlipFitSlot {
    private int slotId;
    private String status;
    private int gymId;
    private Time startTime;
    private Time endTime;
    private int seatsAvailable;
    private double price;
    private Date slotDate;
    private int totalSeats;


    public FlipFitSlot(int gymId, Time startTime, Time endTime, int seatsAvailable, double price) {
        this.gymId = gymId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seatsAvailable = seatsAvailable;
        this.price = price;
    }

    public FlipFitSlot() {

    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
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

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTine(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSlotDate(Date slotDate) {
        this.slotDate = slotDate;
    }

    public Date getSlotDate() {
        return slotDate;
    }
}
