package at.fhv.lab1.commandclient.command;

import java.time.LocalDate;

public class CreateCustomerCommand {
    private String name;
    private String address;
    private LocalDate birthDate;

    public CreateCustomerCommand() {
    }

    public CreateCustomerCommand(String name, String address, LocalDate birthDate) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
