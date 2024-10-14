public class Imaging extends Appointment {
    private Radiology room;

    public Imaging(Date date, Timeslot timeslot, Person patient, Person provider, Radiology room) {
        super(date, timeslot, patient, provider);
        this.room = room;
    }

    public void setRoom(Radiology room) { // imagingAppointment.setRoom(Radiology.CATSCAN)
        this.room = room;
    }

    public Radiology getRoom() {
        return room;
    }

}