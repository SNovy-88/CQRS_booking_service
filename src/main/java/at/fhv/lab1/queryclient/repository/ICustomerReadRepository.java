package at.fhv.lab1.queryclient.repository;

import at.fhv.lab1.queryclient.model.CustomerProjection;

import java.util.List;

public interface ICustomerReadRepository {
    Boolean addCustomer(CustomerProjection customerProjection);

    boolean deleteQueryModels();

    List<CustomerProjection> getCustomersByName(String name);

    List<CustomerProjection> getAllCustomers();
}

