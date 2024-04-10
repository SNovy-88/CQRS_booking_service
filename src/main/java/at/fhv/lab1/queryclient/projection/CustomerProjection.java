package at.fhv.lab1.queryclient.projection;

import java.time.LocalDate;

public class CustomerProjection {
    private long customerID;
    private String name;
    private String address;
    private LocalDate birthdate;

    public CustomerProjection() {
    }

    public CustomerProjection(long customerID, String name, String address, LocalDate birthdate) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.birthdate = birthdate;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
