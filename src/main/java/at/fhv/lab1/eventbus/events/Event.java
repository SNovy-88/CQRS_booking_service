package at.fhv.lab1.eventbus.events;

public abstract class Event {
    private static int nextID = 0;
    private int id;
    private long timestamp;
    private String content;

    public Event() {
    }

    public Event(String content) {
        this.id = nextID++;
        this.timestamp = System.currentTimeMillis();
        this.content = content;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Event.nextID = nextID;
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

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", content='" + content + '\'' +
                '}';
    }
}
