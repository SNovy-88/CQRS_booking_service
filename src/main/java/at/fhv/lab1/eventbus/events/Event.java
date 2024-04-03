package at.fhv.lab1.eventbus.events;

public abstract class Event {
    private int id;
    private long timestamp;
    private String content;
    private static int nextID = 0;

    public Event() {
    }

    public Event(long timestamp, String content) {
        this.id = nextID++;
        this.timestamp = timestamp;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Event.nextID = nextID;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }
}
