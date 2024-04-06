package at.fhv.lab1.eventbus.events;

import java.time.LocalDateTime;

public abstract class Event {
    private static int nextID = 1;
    private int id;
    private LocalDateTime timestamp;
    private String content;

    public Event() {
    }

    public Event(String content) {
        this.id = nextID++;
        this.timestamp = LocalDateTime.now();
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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
