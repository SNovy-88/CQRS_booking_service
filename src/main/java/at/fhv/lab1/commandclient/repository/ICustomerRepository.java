package at.fhv.lab1.commandclient.repository;

import at.fhv.lab1.commandclient.model.Customer;

import java.util.List;

public interface ICustomerRepository {
    Boolean addCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Boolean deleteAllCustomers();
}
