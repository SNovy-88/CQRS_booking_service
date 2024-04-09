package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.queryclient.model.CustomerProjection;

import java.util.List;

public interface ICustomerQueryService {
    Boolean addCustomerToRepository(CustomerCreatedEvent event);

    boolean deleteQueryModels();

    List<CustomerProjection> getCustomersByName(String name);

    List<CustomerProjection> getAllCustomers();
}
