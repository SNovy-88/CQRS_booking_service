package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerRepository implements ICustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    @Override
    public Boolean addCustomer(Customer customer) {
        if (customer == null) {
            return false;
        }

        customers.add(customer);
        return true;
    }

    @Override
    public Boolean deleteAllCustomers() {
        if (!customers.isEmpty() && customers != null) {
            customers.clear();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer getCustomerById(long customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerID() == customerId) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }
}
