public abstract class Provider extends Person {
    private Location location;

    public Provider () {

    }

    public Provider(Location location) {
        this.location = location;
    }

    public Location getLocation()
    {
        return this.location;
    }

    public abstract int rate();

}