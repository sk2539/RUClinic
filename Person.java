public class Person implements Comparable<Person>{
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
    public int compareTo(Profile prof) {

    }
}