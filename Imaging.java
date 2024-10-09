public class Imaging extends Appointment {
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider) {
        super(date, timeslot, patient, provider);
    }

    public void setRoom(Radiology room) { // imagingAppointment.setRoom(Radiology.CATSCAN)
        this.room = room;
    }

    public Radiology getRoom() {
        return room;
    }
}