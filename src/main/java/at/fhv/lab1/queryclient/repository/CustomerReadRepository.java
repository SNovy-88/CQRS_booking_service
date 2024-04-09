package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.model.CustomerProjection;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerReadRepository implements ICustomerReadRepository {
    List<CustomerProjection> customers = new ArrayList<>();


    @Override
    public Boolean addCustomer(CustomerProjection customerProjection) {
        customers.add(customerProjection);

        return true;
    }

    @Override
    public boolean deleteQueryModels() {
        if (!customers.isEmpty() && customers != null) {
            customers.clear();

            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CustomerProjection> getCustomersByName(String name) {
        List<CustomerProjection> customersByName = new ArrayList<>();
        for (CustomerProjection customer : customers) {
            if (customer.getName().contains(name)) {
                customersByName.add(customer);
            }
        }

        return customersByName;
    }

    @Override
    public List<CustomerProjection> getAllCustomers() {
        return customers;
    }
}
