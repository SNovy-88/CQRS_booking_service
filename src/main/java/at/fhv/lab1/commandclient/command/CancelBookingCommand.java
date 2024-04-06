package at.fhv.lab1.commandclient.command;

public class CancelBookingCommand {
    private long bookingID;

    public CancelBookingCommand() {
    }

    public CancelBookingCommand(long bookingID) {
        this.bookingID = bookingID;
    }

    public long getBookingID() {
        return bookingID;
    }

    public void setBookingID(long bookingID) {
        this.bookingID = bookingID;
    }
}
