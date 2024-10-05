public class Imaging extends Appointment {
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider) {
        super(date, timeslot, patient, provider);
    }

    public Radiology getRoom() {
        return room;
    }

    public void setRoom(Radiology room) {
        this.room = room;
    }

}