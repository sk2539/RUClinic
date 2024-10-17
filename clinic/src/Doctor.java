package clinic.src;

public class Doctor extends Provider {
    private Specialty specialty;
    private String npi;

    public Doctor() {
        super();
    }

    public Doctor(Profile profile, Location location, Specialty specialty, String npi) {
        super(profile, location);
        this.specialty = specialty;
        this.npi = npi;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public String getNPI() {
        return npi;
    }

    @Override
    public int rate() {
        return this.specialty.getCharge();
    }

    public int compareTo(Doctor doc) {
        if (doc instanceof Doctor) {
            Doctor doctor = (Doctor) doc;
            return this.profile.compareTo(doctor.getProfile());
        }
        throw new ClassCastException("Cannot compare non-Doctor objects.");
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Doctor) {
            Doctor doctor = (Doctor) object;
            return this.profile.equals(doctor.getProfile()) &&
                    this.npi.equals(doctor.getNPI()) &&
                    this.specialty.equals(doctor.getSpecialty());
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}