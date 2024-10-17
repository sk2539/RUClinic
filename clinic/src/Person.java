package clinic.src;

public class Person implements Comparable<Person> {

    protected Profile profile;

    public Person() {
    }

    public Person(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public int compareTo(Person person) {
        return this.profile.compareTo(person.profile);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Person) { // Checks if the object is of type clinic.src.Appointment.
            Person person = (Person) object;
            return this.profile.equals(person.profile);
        }
        return false;
    }

    @Override
    public String toString() {
        return this.profile.toString();
    }

    // FIGURE OUT POLYMORPHISM FOR THIS
    /**
     * Method: Retrieves the clinic.src.Provider if the clinic.src.Person is a clinic.src.Provider.
     *
     * @return The clinic.src.Provider object or null if not a clinic.src.Provider
     */
    public Provider getProvider() {
        if (this instanceof Provider) {
            return (Provider) this; // Safely cast to clinic.src.Provider
        }
        return null; // Return null if the clinic.src.Person is not a clinic.src.Provider
    }

    public Location getLocation() {
        if (this instanceof Provider) {
            return ((Provider) this).getLocation();
        }
        return null;
    }
}