package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Customer;

public interface ICustomerRepository {
    Boolean addCustomer(Customer customer);

    Boolean deleteAllCustomers();

    Customer getCustomerById(long customerId);
}
