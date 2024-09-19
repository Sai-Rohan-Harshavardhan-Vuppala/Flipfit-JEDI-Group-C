package flipfit.flipkart.business;

import flipfit.flipkart.bean.Customer;

public class FlipFitCustomerService {
    public Customer createCustomer(String customerName, String customerEmail, String customerPassword){
        Customer customer = new Customer("Sai Rohan", "sai.r1@flipkart.com", "sairohan", "password");
        System.out.println("Customer " + customer + " created");
        return customer;
    }
}
