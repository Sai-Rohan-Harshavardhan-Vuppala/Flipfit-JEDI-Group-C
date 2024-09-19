package flipfit.flipkart.business;

import flipfit.flipkart.bean.FlipFitCustomer;

public class FlipFitCustomerService {
    public FlipFitCustomer createCustomer(String customerName, String customerEmail, String customerPassword){
        FlipFitCustomer customer = new FlipFitCustomer("Sai Rohan", "sai.r1@flipkart.com", "sairohan", "password");
        System.out.println("Customer " + customer + " created");
        return customer;
    }
}
