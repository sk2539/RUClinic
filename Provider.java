public abstract class Provider extends Person {
    private Location location;

    public Provider () {
        super();
    }

    public Provider(Profile profile, Location location) {
        super(profile);
        this.location = location;
    }

    public Location getLocation()
    {
        return this.location;
    }

    public abstract int rate();

    @Override
    public int compareTo(Person person) {
        if (person instanceof Provider) { // Checks if the object is of type Appointment.
            Provider provider = (Provider) person;
            return this.profile.compareTo(provider.profile);
        }
        throw new ClassCastException("Cannot compare non-Patient objects.");
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Provider) { // Checks if the object is of type Appointment.
            Provider provider = (Provider) object;
            return this.profile.equals(provider.profile);
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}