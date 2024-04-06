/*
 * Copyright (c) 2024 Sarah N
 *
 * Project Name:         lab1template
 * Description:
 *
 * Date of Creation/
 * Last Update:          03/04/2024
 */

package at.fhv.lab1.eventbus.events;

import at.fhv.lab1.commandclient.model.Customer;

public class CustomerCreatedEvent extends Event {

    private Customer customer;

    public CustomerCreatedEvent() {
    }

    public CustomerCreatedEvent(Customer customer) {
        super("New customer with ID " + customer.getCustomerID() + " created!");
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
