package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
        System.out.println("Customer added: " + customer);
    }

    public List<Customer> getAllBookings() {
        return customers;
    }
}
