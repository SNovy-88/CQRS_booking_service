package at.fhv.lab1.queryclient.query;

public class GetCustomerByNameQuery {
    private String name;

    public GetCustomerByNameQuery() {
        this.name = "";
    }

    public GetCustomerByNameQuery(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
