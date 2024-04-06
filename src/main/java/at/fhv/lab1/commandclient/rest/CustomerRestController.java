package at.fhv.lab1.commandclient.rest;

import at.fhv.lab1.commandclient.command.CreateCustomerCommand;
import at.fhv.lab1.commandclient.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerRestController {
    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public void createCustomer(CreateCustomerCommand createCustomerCommand) {
        customerService.createCustomer(createCustomerCommand);
    }
}
