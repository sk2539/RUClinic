/**
 * The Profile class represents a patient's profile, including their first name,
 * last name, and date of birth. It implements the Comparable interface to allow
 * profiles to be compared based on their attributes.
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructor: Initializes a Profile object with the specified first name,
     * last name, and date of birth.
     *
     * @param fname The first name of the patient
     * @param lname The last name of the patient
     * @param dob The date of birth of the patient
     */
    Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Method: Validates the date of birth to ensure it is not invalid, today, or a future date.
     *
     * @return true if the date of birth is valid; false otherwise
     */
    public boolean dobValid() {
        if (!this.dob.isValidDate()) {
            return false;
        } else if (this.dob.isToday()) {
            return false;
        } else if (this.dob.isFutureDate()) {
            return false;
        }
        return true;
    }

    /**
     * Method: Retrieves the first name of the patient.
     *
     * @return The first name of the patient
     */
    public String getFirstName() {
        return this.fname;
    }

    /**
     * Method: Retrieves the last name of the patient.
     *
     * @return The last name of the patient
     */
    public String getLastName() {
        return this.lname;
    }

    /**
     * Method: Retrieves the date of birth of the patient.
     *
     * @return The date of birth of the patient
     */
    public Date getDob() {
        return this.dob;
    }

    /**
     * Method: Compares this Profile object to another object for equality.
     *
     * @param patientProfile The object to be compared with this Profile
     * @return true if the profiles are equal; false otherwise
     */
    @Override
    public boolean equals(Object patientProfile) {
        if (patientProfile instanceof Profile) {
            Profile profile = (Profile) patientProfile;
            return (profile.fname.equalsIgnoreCase(this.fname)
                    && profile.lname.equalsIgnoreCase(this.lname)
                    && profile.dob.equals(this.dob));
        }
        return false;
    }

    /**
     * Method: Compares this Profile object with another Profile object for order.
     *
     * @param patientProfile The Profile object to compare to
     * @return A negative integer, zero, or a positive integer as this Profile
     *         is less than, equal to, or greater than the specified Profile
     */
    @Override
    public int compareTo(Profile patientProfile) {
        if (patientProfile.lname.compareTo(this.lname) != 0) {
            return patientProfile.lname.compareTo(this.lname);
        }
        else if (patientProfile.fname.compareTo(this.fname) != 0) {
            return patientProfile.fname.compareTo(this.fname);
        }
        else if (patientProfile.dob.compareTo(this.dob) != 0) {
            return patientProfile.dob.compareTo(this.dob);
        }
        return 0;
    }

    /**
     * Method: Returns a string representation of the Profile.
     *
     * @return A string containing the first name, last name, and date of birth
     */
    @Override
    public String toString() {
        return fname + " " + lname + " " + dob.toString();
    }

    /**
     * Main method for testing the compareTo method with various Profile instances.
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        Date date1 = new Date(2003, 11, 20);
        Profile profile1 = new Profile("John", "Doe", date1);
        Profile profile2 = new Profile("John", "Doe", date1);
        System.out.println(profile1.toString() + " compared to " + profile2.toString() + " is: " + profile1.compareTo(profile2));
        Date date2 = new Date(1997, 1, 18);
        Profile profile3 = new Profile("Jone", "Doe", date2);
        Profile profile4 = new Profile("John", "Zoe", date2);
        System.out.println(profile3.toString() + " compared to " + profile4.toString() + " is: " + profile3.compareTo(profile4));
        Date date3 = new Date(1984, 7, 24);
        Profile profile5 = new Profile("David", "Mit", date3);
        Profile profile6 = new Profile("Allen", "Sue", date3);
        System.out.println(profile5.toString() + " compared to " + profile6.toString() + " is: " + profile5.compareTo(profile6));
        Date date4 = new Date (2011, 8, 23);
        Date date5 = new Date (2012, 9, 18);
        Profile profile7 = new Profile("Minho", "Lee", date4);
        Profile profile8 = new Profile("Minho", "Lee", date5);
        System.out.println(profile7.toString() + " compared to " + profile8.toString() + " is: " + profile7.compareTo(profile8));
        Date date6 = new Date(1994, 1, 30);
        Profile profile9 = new Profile("Adam", "Sandler", date2);
        Profile profile10 = new Profile("Zack", "Gar", date2);
        System.out.println(profile9.toString() + " compared to " + profile10.toString() + " is: " + profile9.compareTo(profile10));
        Date date7 = new Date(1950, 6, 5);
        Profile profile12 = new Profile("Chris", "Bang", date7);
        Profile profile11 = new Profile("Peter", "Han", date7);
        System.out.println(profile11.toString() + " compared to " + profile12.toString() + " is: " + profile11.compareTo(profile12));
        Date date8 = new Date (2009, 8, 23);
        Date date9 = new Date (2008, 9, 18);
        Profile profile13 = new Profile("Jeff", "Brian", date8);
        Profile profile14 = new Profile("Jeff", "Brian", date9);
        System.out.println(profile13.toString() + " compared to " + profile14.toString() + " is: " + profile13.compareTo(profile14));
    }
}
