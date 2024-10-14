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
        if (object instanceof Person) { // Checks if the object is of type Appointment.
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
     * Method: Retrieves the Provider if the Person is a Provider.
     *
     * @return The Provider object or null if not a Provider
     */
    public Provider getProvider() {
        if (this instanceof Provider) {
            return (Provider) this; // Safely cast to Provider
        }
        return null; // Return null if the Person is not a Provider
    }

    public Location getLocation() {
        if (this instanceof Provider) {
            return ((Provider) this).getLocation();
        }
        return null;
    }
}