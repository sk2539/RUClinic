package src;

public class Patient extends Person {
    private Visit visit;

    public Patient(Visit visit) {
        this.visit = visit;
    }

    @Override
    public int compareTo(Person person) {
        if (person instanceof Patient) { // Checks if the object is of type src.Appointment.
            Patient patient = (Patient) person;
            return this.profile.compareTo(patient.profile);
        }
        throw new ClassCastException("Cannot compare non-src.Patient objects.");
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Patient) { // Checks if the object is of type src.Appointment.
            Patient patient = (Patient) object;
            return this.profile.equals(patient.profile);
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}