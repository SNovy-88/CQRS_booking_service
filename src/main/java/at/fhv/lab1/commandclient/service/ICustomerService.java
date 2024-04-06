package at.fhv.lab1.commandclient.service;

import at.fhv.lab1.commandclient.command.CreateCustomerCommand;

public interface ICustomerService {
    Boolean createCustomer(CreateCustomerCommand createCustomerCommand);
}
