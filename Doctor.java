public class Doctor extends Provider {
    private Specialty specialty;
    private String npi;

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
        return 0;
    }

    @Override
    public int compareTo(Person person) {
        if (person instanceof Doctor) {
            Doctor doctor = (Doctor) person;
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
        return super.toString() + ", Specialty: " + specialty + ", NPI: " + npi;
    }
}