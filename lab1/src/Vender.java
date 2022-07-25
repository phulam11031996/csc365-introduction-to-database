public class Vender {

    int id; // unique, automatically assigned
    String name;
    String location;

    public Vender(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    @Override
    public String toString() {
        return "- id: " + this.id +
                " name: " + this.name +
                " location: " + this.location + "\n";
    }

}
