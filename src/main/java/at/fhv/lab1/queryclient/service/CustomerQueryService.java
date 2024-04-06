package at.fhv.lab1.queryclient.service;

import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import at.fhv.lab1.queryclient.model.CustomerProjection;
import at.fhv.lab1.queryclient.repository.CustomerReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerQueryService implements ICustomerQueryService {
    private final CustomerReadRepository customerReadRepository;

    @Autowired
    public CustomerQueryService(CustomerReadRepository customerReadRepository) {
        this.customerReadRepository = customerReadRepository;
    }

    @Override
    public Boolean addCustomerToRepository(CustomerCreatedEvent event) {
        long customerID = event.getCustomer().getCustomerID();
        String name = event.getCustomer().getName();
        String address = event.getCustomer().getAddress();
        LocalDate birthdate = event.getCustomer().getBirthdate();

        CustomerProjection customerProjection = new CustomerProjection(customerID, name, address, birthdate);
        return customerReadRepository.addCustomer(customerProjection);
    }

    @Override
    public Boolean deleteQueryModels() {
        return customerReadRepository.deleteQueryModels();
    }

    @Override
    public List<CustomerProjection> getCustomersByName(String name) {
        return customerReadRepository.getCustomersByName(name);
    }

    @Override
    public List<CustomerProjection> getAllCustomers() {
        return customerReadRepository.getAllCustomers();
    }
}
