package at.fhv.lab1.eventbus.events;

public class Event {
    private int id;
    private EventType type;
    private int customer;
    private long timestamp;
    private String content;
    private int nextID = 0;

    public Event() {
    }

    public Event(int customer, long timestamp, String content) {
        this.id = nextID++;
        this.customer = customer;
        this.timestamp = timestamp;
        this.content = content;
    }

    public Event(long timestamp, String content) {
        this.id = nextID++;
        this.customer = -1;
        this.timestamp = timestamp;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public int getCustomer() {
        return customer;
    }

    public void setCustomer(int customer) {
        this.customer = customer;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type=" + type +
                ", customer=" + customer +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }
}
