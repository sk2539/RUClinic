package clinic.src;

public class Technician extends Provider {
    private int ratePerVisit;

    public Technician() {
        super();
    }

    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    @Override
    public Profile getProfile() {
        return this.profile;
    }

    public void setRatePerVisit(int ratePerVisit) {
        this.ratePerVisit = ratePerVisit;
    }

    @Override
    public int rate() {
        return this.ratePerVisit;
    }

    public int compareTo(Technician tech) {
        if (tech instanceof Technician) {
            Technician technician = (Technician) tech;

            int profileComparison = this.getProfile().compareTo(technician.getProfile());
            if (profileComparison == 0) {
                return Integer.compare(this.ratePerVisit, technician.rate());
            }
            return profileComparison;
        }
        throw new ClassCastException("Cannot compare non-Technician objects.");
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Technician) {
            Technician technician = (Technician) object;
            return this.getProfile().equals(technician.getProfile()) &&
                    this.getLocation().equals(technician.getLocation()) &&
                    this.ratePerVisit == technician.rate();
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
