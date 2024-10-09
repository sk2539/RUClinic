public class Technician extends Provider {
    private int ratePerVisit;

    public Technician() {
        super();
    }

    public Technician(Profile profile, Location location, int ratePerVisit) {
        super(profile, location);
        this.ratePerVisit = ratePerVisit;
    }

    public void setRatePerVisit(int ratePerVisit) {
        this.ratePerVisit = ratePerVisit;
    }

    @Override
    public int rate() {
        return this.ratePerVisit;
    }

    @Override
    public int compareTo(Person person) {
        if (person instanceof Technician) {
            Technician technician = (Technician) person;

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
        return super.toString() + ", Rate Per Visit: $" + ratePerVisit;
    }
}
