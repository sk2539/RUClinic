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
    /**
     * Method: Retrieves the first name of the patient.
     *
     * @return The first name of the patient
     */
    public String getFirstName() {
        return this.profile.getFirstName();
    }

    /**
     * Method: Retrieves the last name of the patient.
     *
     * @return The last name of the patient
     */
    public String getLastName() {
        return this.profile.getLastName();
    }

    /**
     * Method: Retrieves the date of birth of the patient.
     *
     * @return The date of birth of the patient
     */
    public Date getDob() {
        return this.profile.getDob();
    }

    public Location getLocation() {
        if (this instanceof Provider) {
            return ((Provider) this).getLocation();
        }
        return null;
    }
}