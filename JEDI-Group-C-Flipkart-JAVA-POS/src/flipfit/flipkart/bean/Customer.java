package flipfit.flipkart.bean;

import java.util.List;

public class Customer  extends User {
    private String customerId;
    private String customerName;
    private String customerEmail;
    private List<Booking>bookings;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

   public List<Booking> getBookings() {
        return bookings;
   }
   public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
   }

}
