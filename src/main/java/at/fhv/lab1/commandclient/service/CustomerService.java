package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.EventPublisher;
import at.fhv.lab1.commandclient.command.CreateCustomerCommand;
import at.fhv.lab1.commandclient.model.Customer;
import at.fhv.lab1.commandclient.repository.CustomerRepository;
import at.fhv.lab1.eventbus.events.CustomerCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final EventPublisher eventPublisher;

    @Autowired
    public CustomerService(EventPublisher eventPublisher, CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Boolean createCustomer(CreateCustomerCommand createCustomerCommand) {
        String name = createCustomerCommand.getName();
        String address = createCustomerCommand.getAddress();
        LocalDate birthDate = createCustomerCommand.getBirthDate();
        Customer customer = new Customer(name, address, birthDate);

        if (customerRepository.addCustomer(customer)) {
            CustomerCreatedEvent customerCreatedEvent = new CustomerCreatedEvent(customer);
            eventPublisher.publishEvent(customerCreatedEvent);
            return true;
        } else {
            return false;
        }
    }
}
