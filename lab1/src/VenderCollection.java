import java.util.HashMap;

public class VenderCollection {

    HashMap<Integer, Vender> venders = new HashMap<Integer, Vender>();

    public VenderCollection() {
    }

    public void addVender(String name, String location) {
        int nextIdx = this.venders.size() + 1;
        Vender newVender = new Vender(nextIdx, name, location);
        this.venders.put(nextIdx, newVender);
    }

    public HashMap<Integer, Vender> getVenders() {
        return this.venders;
    }

    public void display() {
        this.venders.forEach((key, value) -> System.out.println(value));
    }

}
