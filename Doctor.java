public class Doctor extends Provider {
    private Specialty specialty;
    private String npi;

    public Doctor (Specialty specialty, String npi) {
        this.specialty = specialty;
        this.npi = npi;
    }

    @Override
    public int rate() {
        return 0;
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}